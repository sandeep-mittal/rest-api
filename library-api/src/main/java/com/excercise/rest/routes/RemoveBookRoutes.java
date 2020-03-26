package com.excercise.rest.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RemoveBookRoutes  extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class).handled(false);
        from("direct:remove-book").routeId("remove-book-route")
                .setHeader(Exchange.HTTP_METHOD,simple("GET"))
                .setHeader(Exchange.CONTENT_TYPE,simple("application/json"))
                .to("http://localhost:8085/book-repo/removeBook");

    }


}
