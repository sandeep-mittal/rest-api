package com.excercise.rest.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LibraryApiToBookRepoTransformer extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        transformer().fromType("json:library-api")
                .toType("json:book-repo-service-api")
                .withUri("jolt:{{library-api-to-book-repo-transformation.path}}?inputType=JsonString&outputType=JsonString&contentCache=true");

        // Transform library api to Book repo
        from("direct:library-api-To-book-repo")
                .routeId("library-api-To-book-repo-route")
                .inputType("json:library-api")
                .log("Library API JSON  Received: ${body}")
                .to("direct:transform-to-book-repo");

      // transformed to book-repo
        from("direct:transform-to-book-repo")
                .routeId("transform-to-book-repo-route")
                .inputType("json:book-repo-service-api")
                .log("Book API JSON  Transformed: ${body}");

    }
}
