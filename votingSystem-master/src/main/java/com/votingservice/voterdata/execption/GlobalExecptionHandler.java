package com.votingservice.voterdata.execption;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
    public class GlobalExecptionHandler  extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExecptionHandler.class);

        public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,
                                                                         HttpStatus status ,WebRequest request) {
            LOGGER.info("Inside execption handling methord");
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDate.now());
            body.put("status", status.value());

            List<String> errors = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.toList());

            body.put("errors", errors);

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        }

    @ResponseBody
    @ExceptionHandler({CandidateNotFoundException.class,TokenExpiredExecption.class,VoterNotFound.class,AlreadyVotedExecption.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundHandler(Exception  ex) {


        return ex.getMessage();
    }


    }

