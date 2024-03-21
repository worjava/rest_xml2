package com.shop.soap_server.exception.advice;

import com.shop.soap_server.exception.ValidationException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Обработчик глобальных исключений.
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    /**
     * Обработка исключения ValidationException.
     *
     * @param ex Исключение ValidationException.
     * @return ResponseEntity с информацией об ошибке в JSON-формате.
     */
    @ExceptionHandler(ValidationException.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Ошибка валидации");
        body.put("Проверьте введенные данные", ex.getValidationError());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    /**
     * Обработка исключения SAXParseException.
     *
     * @param ex Исключение SAXParseException.
     * @return ResponseEntity с информацией об ошибке в JSON-формате.
     */
    @ExceptionHandler(SAXParseException.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Ошибка валидации XML",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Object> handleSAXParseException(SAXParseException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Ошибка валидации XML");
        body.put("Проверьте введенные данные", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    /**
     * Обработка исключения HttpMediaTypeNotAcceptableException.
     *
     * @return Сообщение о неподдерживаемом типе данных.
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }


        @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка: " + ex.getMessage());
        }
    }


