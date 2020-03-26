package com.excercise.rest.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookRepoToLibraryApiTransformer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        transformer().fromType("json:book-repo-service-api")
                .toType("json:library-api")
                .withUri("jolt:{{book-repo-api-to-library-api-transformation.path}}?inputType=JsonString&outputType=JsonString&contentCache=true");

        // Transform  Book repo to library api
        from("direct:book-repo-to-library-api")
                .routeId("book-repo-to-library-api-route")
                .inputType("json:book-repo-service-api")
                .log("BOOK REPO JSON  Received: ${body}")
                .to("direct:transform-to-library-api");

        // transformed to library-api
        from("direct:transform-to-library-api")
                .routeId("transform-to-library-api-route")
                .inputType("json:library-api")
                .log("Library API JSON  Transformed: ${body}");

    }
}
