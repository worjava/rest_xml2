package com.shop.soap_server.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import java.util.*;

/**
 * Обработчик ошибок валидации XML-данных.
 */
@Tag(name = "ValidationErrorHandler", description = "Обработчик ошибок валидации XML-данных")
public class ValidationErrorHandler implements ErrorHandler {

    @Schema(description = "Карта ошибок, где ключ - номер строки, значение - сообщение об ошибке")
    private final Map<Integer, String> errorMap = new LinkedHashMap<>();

    /**
     * Получить ошибки валидации.
     *
     * @return Множество строк с сообщениями об ошибках.
     */
    public Set<String> getError() {
        return new LinkedHashSet<>(errorMap.values());
    }

    /**
     * Форматирование сообщения об ошибке на основе исключения.
     *
     * @param exception Исключение SAXParseException.
     * @return Строка с сообщением об ошибке.
     */
    private String formatErrorMessage(SAXParseException exception) {
        String message = exception.getMessage();
        int lineNumber = exception.getLineNumber();

        // Отображение ошибок на конкретные поля данных
        Map<String, String> errorFieldMapping = new HashMap<>();
        errorFieldMapping.put("is not facet-valid with respect to pattern '[A-Za-zА-Яа-я\\- ]+' for type 'NameType'", lineNumber == 3 ? "Фамилии" : "Имени");
        errorFieldMapping.put("is not a valid value for 'integer'", lineNumber == 6 ? "Количество товара" : "Возраст");
        errorFieldMapping.put("is not facet-valid with respect to enumeration '[TELEVISION, SMARTPHONE, JUICER, HEADPHONES, KEYBOARD]'", "Покупки");
        errorFieldMapping.put("is not a valid value for 'decimal'", "Сумма");
        errorFieldMapping.put("is not a valid value for 'date'", "Дата покупки");

        // Поиск соответствия ошибки конкретному полю данных
        for (Map.Entry<String, String> entry : errorFieldMapping.entrySet()) {
            if (message.contains(entry.getKey())) {
                return "Некорректное значение в поле '" + entry.getValue() + "'";
            }
        }
        return "Некорректные данные: " + message;
    }

    /**
     * Обработка предупреждения валидации.
     *
     * @param exception Исключение SAXParseException.
     */
    @Override
    public void warning(SAXParseException exception) {
        errorMap.putIfAbsent(exception.getLineNumber(), formatErrorMessage(exception));
    }

    /**
     * Обработка ошибки валидации.
     *
     * @param exception Исключение SAXParseException.
     */
    @Override
    public void error(SAXParseException exception) {
        errorMap.putIfAbsent(exception.getLineNumber(), formatErrorMessage(exception));
    }

    /**
     * Обработка фатальной ошибки валидации.
     *
     * @param exception Исключение SAXParseException.
     */
    @Override
    public void fatalError(SAXParseException exception) {
        errorMap.putIfAbsent(exception.getLineNumber(), formatErrorMessage(exception));
    }
}
