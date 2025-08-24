package com.atcorp.eventmanagement.util.annotationProcessor;

import com.atcorp.eventmanagement.annotations.ValidSeatMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Set;

public class ValidateSeatMap implements ConstraintValidator<ValidSeatMap, JsonNode> {

    @Value("classpath:SeatMap.json")
    Resource seatMapSchema;

    @Override
    public void initialize(ValidSeatMap constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(JsonNode s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = schemaFactory.getSchema(new ObjectMapper().readTree(seatMapSchema.getInputStream()));
            Set<ValidationMessage> errors = schema.validate(s);
            return errors.isEmpty();
        } catch (IOException e) {
            return false;
        }
    }
}
