package br.com.adbrascosmopolis.saac_tesouraria.config; // ajuste para o seu pacote

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/fotos-membros/**")
//                .addResourceLocations("file:///D:/Uploads/Fotos-Membros/");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")   // permite qualquer origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fotos/**")
                .addResourceLocations("file:/D:/Uploads/Fotos-Membros/");

        // Atas PDF
        registry.addResourceHandler("/atas/**")
                .addResourceLocations("file:/D:/Uploads/Atas/");
    }
}
