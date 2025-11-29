package com.brigid.idp.guard

import com.brigid.idp.exceptions.ApiException
import com.brigid.idp.exceptions.DatabaseApiException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class ExceptionHandler {
    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(exception: Exception): ApiErrorResponse {
        logger.error(exception.message, exception)
        return ApiErrorResponse(
            message = exception.localizedMessage ?: exception.message ?: "An unexpected error occurred",
            details = null
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun onHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ApiErrorResponse {
        logger.error(exception.message, exception)
        val cause = exception.cause

        if (cause is MismatchedInputException) {
            val path = cause.path
            val fieldName = path.lastOrNull()?.fieldName
            return ApiErrorResponse(
                message = "Invalid input for field: $fieldName",
                details = null
            )
        } else if (cause is JsonParseException) {
            return ApiErrorResponse(
                message = "JSON parsing error: ${cause.message}",
                details = null
            )
        }

        return ApiErrorResponse(
            message = "Validation failed for one or more fields.",
            details = null
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun onMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ApiErrorResponse {
        logger.error(exception.message, exception)
        val errors: Map<String, String> = exception
            .bindingResult
            .allErrors
            .associateBy({ (it as FieldError).field }, { it.defaultMessage ?: "" })

        return ApiErrorResponse(
            message = "Validation failed for one or more fields",
            details = errors
        )
    }

    @ExceptionHandler(DatabaseApiException::class)
    fun onDatabaseException(exception: DatabaseApiException): ResponseEntity<ApiErrorResponse> {
        logger.error(exception.message, exception)
        return ResponseEntity.status(exception.status).body(
            ApiErrorResponse(
                message = exception.localizedMessage ?: exception.message ?: "A database error occurred",
                details = null
            )
        )
    }

    @ExceptionHandler(ApiException::class)
    fun onApiException(exception: ApiException): ResponseEntity<ApiErrorResponse> {
        logger.error(exception.message, exception)
        return ResponseEntity.status(exception.status).body(
            ApiErrorResponse(
                message = exception.localizedMessage ?: exception.message ?: "A API error occurred",
                details = null
            )
        )
    }
}

data class ApiErrorResponse(
    val message: String,
    val details: Map<String, Any>?
)