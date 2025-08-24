package com.atcorp.eventmanagement.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vladmihalcea.hibernate.type.range.Range;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.w3c.dom.ranges.RangeException.BAD_BOUNDARYPOINTS_ERR;

public class RangeModule extends SimpleModule {

    public RangeModule() {
        super("RangeModule");
        @SuppressWarnings("unchecked")
        Class<Range<?>> raw = (Class) Range.class; // align generics with serializers below
        addSerializer(raw, new LdtRangeSerializer());
        addDeserializer(raw, new LdtRangeDeserializer());
    }

    static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    static class LdtRangeSerializer extends JsonSerializer<Range<?>> {
        @Override
        public void serialize(Range<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            if (value.hasLowerBound()) {
                Object lb = value.lower();
                gen.writeStringField("lower", (lb instanceof LocalDateTime)
                        ? ((LocalDateTime) lb).format(FMT)
                        : String.valueOf(lb));
                gen.writeBooleanField("lowerInclusive", value.isLowerBoundClosed());
            }
            if (value.hasUpperBound()) {
                Object ub = value.upper();
                gen.writeStringField("upper", (ub instanceof LocalDateTime)
                        ? ((LocalDateTime) ub).format(FMT)
                        : String.valueOf(ub));
                gen.writeBooleanField("upperInclusive", value.isUpperBoundClosed());
            }
            gen.writeEndObject();
        }
    }

    static class LdtRangeDeserializer extends JsonDeserializer<Range<?>> {
        @Override
        public Range<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, RangeException {
            JsonNode n = p.getCodec().readTree(p);

            LocalDateTime lower = n.hasNonNull("lower") ? LocalDateTime.parse(n.get("lower").asText(), FMT) : null;
            LocalDateTime upper = n.hasNonNull("upper") ? LocalDateTime.parse(n.get("upper").asText(), FMT) : null;

            if (lower != null && upper != null) {
                if (lower.isAfter(upper)) {
                    throw new RangeException(BAD_BOUNDARYPOINTS_ERR, "Start time is later than end time");
                }
            }

            boolean lowerInc = !n.has("lowerInclusive") || n.get("lowerInclusive").asBoolean(); // default true
            boolean upperInc = n.has("upperInclusive") && n.get("upperInclusive").asBoolean();  // default false

            if (lower != null && upper != null) {
                if (lowerInc && upperInc) return Range.closed(lower, upper);
                if (lowerInc) return Range.closedOpen(lower, upper);
                if (upperInc) return Range.openClosed(lower, upper);
                return Range.open(lower, upper);
            } else if (lower != null) {
                return lowerInc ? Range.closedInfinite(lower) : Range.openInfinite(lower);
            } else if (upper != null) {
                return upperInc ? Range.infiniteClosed(upper) : Range.infiniteOpen(upper);
            }
            return Range.emptyRange(LocalDateTime.class);
        }
    }
}


