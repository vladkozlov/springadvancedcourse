package xyz.vladkozlov.epam.springmvc.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreeMarkerConfig {

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/views/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        Properties freemarkerProps = new Properties();
        freemarkerProps.put("template_exception_handler", "rethrow");
        freeMarkerConfigurer.setFreemarkerSettings(freemarkerProps);
        return freeMarkerConfigurer;
    }

}
