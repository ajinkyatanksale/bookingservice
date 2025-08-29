package com.atcorp.eventmanagement.util.interceptor;

import com.atcorp.eventmanagement.util.jwt.JwtHelper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServiceAuthInterceptor implements ClientHttpRequestInterceptor {

    private JwtHelper jwtHelper;

    public ServiceAuthInterceptor (JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String jwtToken = jwtHelper.generateToken();
        request.getHeaders().add("Authorization", "Bearer " + jwtToken);

        return execution.execute(request, body);
    }
}
