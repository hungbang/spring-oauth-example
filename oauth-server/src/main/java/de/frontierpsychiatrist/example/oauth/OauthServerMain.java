package de.frontierpsychiatrist.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.h2.server.web.WebServlet;

import javax.sql.DataSource;

/**
 * @author Moritz Schulze
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class OauthServerMain {

    /**
     * Main data source containing the credentials.
     * In this is example this is the DB from the resource server.
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(OauthServerMain.class, args);
    }

}
