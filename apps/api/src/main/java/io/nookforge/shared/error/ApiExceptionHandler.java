/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.error;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  ResponseEntity<Object> handleUnexpected(Exception exception, WebRequest request) {
    return handleExceptionInternal(
        exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception exception,
      Object body,
      HttpHeaders headers,
      HttpStatusCode statusCode,
      WebRequest request) {
    var safeProblem = safeProblem(exception, statusCode);
    return super.handleExceptionInternal(exception, safeProblem, headers, statusCode, request);
  }

  private ProblemDetail safeProblem(Exception exception, HttpStatusCode statusCode) {
    if (exception instanceof MethodArgumentNotValidException) {
      return problem(
          HttpStatus.BAD_REQUEST,
          "validation_failed",
          "Request validation failed",
          "The request does not match the required contract.");
    }
    if (exception instanceof NoResourceFoundException) {
      return problem(
          HttpStatus.NOT_FOUND,
          "not_found",
          "Resource not found",
          "The requested resource is not available.");
    }
    if (statusCode.is4xxClientError()) {
      return problem(
          statusCode,
          "request_rejected",
          "Request rejected",
          "The request does not match the required HTTP contract.");
    }
    return problem(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "internal_error",
        "Request failed",
        "The request could not be completed.");
  }

  private ProblemDetail problem(HttpStatusCode status, String code, String title, String detail) {
    var problem = ProblemDetail.forStatusAndDetail(status, detail);
    problem.setTitle(title);
    problem.setType(URI.create("urn:nookforge:error:" + code));
    problem.setProperty("code", code);
    return problem;
  }
}
