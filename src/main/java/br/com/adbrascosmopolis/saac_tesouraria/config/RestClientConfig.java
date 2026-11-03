package br.com.adbrascosmopolis.saac_tesouraria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://10.0.0.76:8081/api/sync")
                .requestInterceptor((request, body, execution) -> {
                    var attributes = RequestContextHolder.getRequestAttributes();

                    if (!(attributes instanceof ServletRequestAttributes servletAttributes)) {
                        throw new IllegalStateException(
                                "Não foi possível obter a requisição HTTP atual para encaminhar o token."
                        );
                    }

                    String authorization = servletAttributes
                            .getRequest()
                            .getHeader(HttpHeaders.AUTHORIZATION);

                    if (authorization == null || authorization.isBlank()) {
                        throw new IllegalStateException(
                                "A requisição recebida não possui o cabeçalho Authorization."
                        );
                    }

                    request.getHeaders().set(HttpHeaders.AUTHORIZATION, authorization);

                    return execution.execute(request, body);
                })
                .build();
    }
}
