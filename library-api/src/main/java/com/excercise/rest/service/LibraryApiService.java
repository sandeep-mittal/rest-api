package com.excercise.rest.service;

import com.excercise.rest.dto.Reply;
import com.excercise.rest.exception.LibraryApiException;
import com.networknt.schema.ValidationMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import springfox.documentation.spring.web.json.Json;
import com.excercise.rest.model.Book;
import java.util.List;


@Service
public class LibraryApiService {

    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    private CamelContext camelContext;
    Logger  log = LoggerFactory.getLogger(LibraryApiService.class);

    public String processAddBook(Book book) throws LibraryApiException {
        final Exchange requestExchange= ExchangeBuilder.anExchange(camelContext).withBody(book).withPattern(ExchangePattern.InOut).build();
        log.info("Sending exchange message to end point");
        final Exchange responseExchange= producerTemplate.send("direct:add-book",requestExchange);
        log.info("Received response from Exchange");
        return getResponse(responseExchange);
    }

    public String getResponse(final Exchange exchange) throws LibraryApiException{
        Exception exception = exchange.getException();
        if( ObjectUtils.isEmpty(exception))
        {
            return exchange.getMessage().getBody(String.class);
        }else if (exception instanceof JsonValidationException){

            log.error("Error while validating json request message");
            JsonValidationException validationException = (JsonValidationException)exception;
            int errorSize= validationException.getNumberOfErrors();
            log.error(validationException.getErrors().iterator().next().getMessage());
            ValidationMessage validationErrorMessage = validationException.getErrors().iterator().next();

            String errorMessage = validationErrorMessage.getMessage();

            if (errorMessage!=null && errorMessage.startsWith("$.") && errorMessage.length()>2){
                errorMessage = errorMessage.substring(2);
            }else {
                errorMessage = "Invalid Json Format";

            }
            throw new LibraryApiException(errorMessage);
        }



        else {
            log.error("Some other error occured "+exception.getMessage());
            throw new LibraryApiException(exception.getMessage());
        }

    }


    public String processRemoveBook(int bookId) throws LibraryApiException {
        final Exchange requestExchange= ExchangeBuilder.anExchange(camelContext).withHeader(Exchange.HTTP_QUERY,"bookId="+bookId).withPattern(ExchangePattern.InOut).build();
        log.info("Sending exchange message to end point");
        final Exchange responseExchange= producerTemplate.send("direct:remove-book",requestExchange);
        log.info("Received response from Exchange");
        return getResponse(responseExchange);
    }
}
