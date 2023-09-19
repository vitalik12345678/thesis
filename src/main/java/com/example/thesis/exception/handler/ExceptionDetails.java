package com.example.thesis.exception.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;

import java.util.List;

@Getter
@Setter
public class ExceptionDetails extends ProblemDetail {

    private List<String> messages;

}
