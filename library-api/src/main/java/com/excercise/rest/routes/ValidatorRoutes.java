package com.excercise.rest.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ValidatorRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        validator().type("json:library-api").withUri("json-validator:{{library-api-schema.json}}");
        validator().type("json:book-repo-service-api").withUri("json-validator:{{book-repo-service-schema.json}}");

        from("direct:validate-library-api")
                .routeId("validate-library-api-route")

                .log("library api message  Received: ${body}")
                .inputTypeWithValidate("json:library-api")
                .log("Library API JSON  Validation: SUCCESS");

        from("direct:validate-book-repo-service-api")
                .routeId("book-repo-service-api-route")
                .log("Book Repo Service JSON  Received: ${body}")
                .inputTypeWithValidate("json:book-repo-service-api")
                .log("Book Repo Service JSON  Validation: SUCCESS");
    }
}
