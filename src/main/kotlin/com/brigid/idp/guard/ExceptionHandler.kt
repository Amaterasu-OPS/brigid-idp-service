package com.brigid.idp.guard

import com.brigid.idp.exceptions.ApiException
import com.brigid.idp.exceptions.DatabaseApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(exception: Exception): ApiErrorResponse {
        return ApiErrorResponse(
            message = exception.localizedMessage ?: exception.message ?: "An unexpected error occurred.",
            details = null
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun onMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ApiErrorResponse {
        val errors: Map<String, String> = exception
            .bindingResult
            .allErrors
            .associateBy({ (it as FieldError).field }, { it.defaultMessage ?: "" })

        return ApiErrorResponse(
            message = "Validation failed for one or more fields.",
            details = errors
        )
    }

    @ExceptionHandler(DatabaseApiException::class)
    fun onDatabaseException(exception: DatabaseApiException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity.status(exception.status).body(
            ApiErrorResponse(
                message = exception.localizedMessage ?: exception.message ?: "A database error occurred.",
                details = null
            )
        )
    }

    @ExceptionHandler(ApiException::class)
    fun onApiException(exception: ApiException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity.status(exception.status).body(
            ApiErrorResponse(
                message = exception.localizedMessage ?: exception.message ?: "A API error occurred.",
                details = null
            )
        )
    }
}

data class ApiErrorResponse(
    val message: String,
    val details: Map<String, Any>?
)