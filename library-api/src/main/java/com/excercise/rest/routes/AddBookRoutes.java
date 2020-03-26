package com.excercise.rest.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.excercise.rest.model.Book;

import java.awt.*;

@Component
public class AddBookRoutes extends RouteBuilder {

    JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Book.class);
    @Override
    public void configure() throws Exception {
        onException(Exception.class).handled(false);
        from("direct:add-book").routeId("add-book-route")
                .setHeader(Exchange.HTTP_METHOD,simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE,simple("application/json"))
                .marshal(jsonDataFormat)
                .to("direct:validate-library-api")
                .to("direct:library-api-To-book-repo")
                .to("direct:validate-book-repo-service-api")
                .to("http://localhost:8085/book-repo/addBook");

    }
}
