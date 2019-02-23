package com.walterwhites.library.webservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "books")
    public DefaultWsdl11Definition defaultWsdlBooksDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BooksPort");
        wsdl11Definition.setLocationUri("/ws/books");
        wsdl11Definition.setTargetNamespace("library.io.github.walterwhites");
        wsdl11Definition.setSchema(booksSchema());
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema booksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("books.xsd"));
    }

    @Bean(name = "loans")
    public DefaultWsdl11Definition defaultWsdlLoansDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("LoansPort");
        wsdl11Definition.setLocationUri("/ws/loans");
        wsdl11Definition.setTargetNamespace("library.io.github.walterwhites.loans");
        wsdl11Definition.setSchema(loansSchema());
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema loansSchema() {
        return new SimpleXsdSchema(new ClassPathResource("loans.xsd"));
    }
}