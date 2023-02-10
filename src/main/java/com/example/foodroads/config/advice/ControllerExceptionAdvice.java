package com.example.foodroads.config.advice;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.common.exception.model.FoodRoadsBaseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.foodroads.common.exception.ErrorCode.*;
import static java.util.stream.Collectors.joining;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BadRequest
     * Spring Validation
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ApiResponse<Object> handleBadRequest(BindException exception) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(joining("\n"));
        log.error("BindException: {}", errorMessage);
        return ApiResponse.error(E400_INVALID, errorMessage);
    }

    /**
     * 400 BadRequest
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ApiResponse<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E400_INVALID);
    }

    /**
     * 400 BadRequest
     * RequestParam 필수 필드가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ApiResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E400_MISSING_PARAMETER, String.format("필수 파라미터 (%s)를 입력해주세요", exception.getParameterName()));
    }

    /**
     * 400 BadRequest
     * RequestPart 필수 Path Variable 가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPathVariableException.class)
    private ApiResponse<Object> handleMissingPathVariableException(MissingPathVariableException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E400_MISSING_PARAMETER, String.format("Path (%s)를 입력해주세요", exception.getVariableName()));
    }

    /**
     * 400 BadRequest
     * 잘못된 타입이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    private ApiResponse<Object> handleTypeMismatchException(TypeMismatchException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E400_INVALID, String.format("%s (%s)", E400_INVALID.getMessage(), exception.getValue()));
    }

    /**
     * 400 BadRequest
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        InvalidFormatException.class,
        ServletRequestBindingException.class
    })
    private ApiResponse<Object> handleInvalidFormatException(Exception exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E400_INVALID);
    }

    /**
     * 405 Method Not Allowed
     * 지원하지 않은 HTTP method 호출 할 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ApiResponse<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E405_METHOD_NOT_ALLOWED);
    }

    /**
     * 406 Not Acceptable
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    private ApiResponse<Object> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E406_NOT_ACCEPTABLE);
    }

    /**
     * 415 UnSupported Media Type
     * 지원하지 않는 미디어 타입인 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeException.class)
    private ApiResponse<Object> handleHttpMediaTypeException(HttpMediaTypeException exception) {
        log.warn(exception.getMessage());
        return ApiResponse.error(E415_UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * ThreeDollars Custom Exception
     */
    @ExceptionHandler(FoodRoadsBaseException.class)
    private ApiResponse<Object> handleBaseException(FoodRoadsBaseException exception) {

        log.error(exception.getMessage(), exception);
        return ApiResponse.error(exception.getErrorCode());
    }

    /**
     * 500 Internal Server
     */
    @ExceptionHandler(Throwable.class)
    private ApiResponse<Object> handleThrowable(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);

        if (throwable.getCause() instanceof FoodRoadsBaseException baseException) {
            return ApiResponse.error(baseException.getErrorCode());
        }

        return ApiResponse.error(E500_INTERNAL_SERVER);
    }

}
