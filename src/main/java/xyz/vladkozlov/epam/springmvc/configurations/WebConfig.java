package xyz.vladkozlov.epam.springmvc.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.vladkozlov.epam.springmvc.converters.PdfHttpMessageConverter;
import xyz.vladkozlov.epam.springmvc.converters.PdfHttpMessageConverterImpl;

import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("xyz.vladkozlov.epam")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
         converters.add(userToPdfConverter());
         converters.add(jsonConverter());
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.TEXT_HTML)
                .parameterName("type")
                .favorParameter(true)
                .ignoreUnknownPathExtensions(false)
                .ignoreAcceptHeader(false)
                .useJaf(true);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.freeMarker();
        registry.enableContentNegotiation(
//                new PdfHttpMessageConverterImpl()
                new PdfReportViewGenerator()
        );
    }

    @Bean
    PdfHttpMessageConverter userToPdfConverter() {
        return new PdfHttpMessageConverterImpl();
    }

    @Bean
    MappingJackson2HttpMessageConverter jsonConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
