package com.shop.soap_server.service.Impl;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.shop.soap_server.exception.ValidationErrorHandler;
import com.shop.soap_server.exception.ValidationException;
import com.shop.soap_server.service.XMLValidatorService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.*;


@Service
public class XMLValidatorServiceImpl implements XMLValidatorService {

    private static final String XSD_PATH = "purchase.xsd";


    @Override
    public boolean isValid(String xmlData) {
        ValidationErrorHandler errorHandler = new ValidationErrorHandler();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new InputSource(new StringReader(xmlData)));

            File xsdFile = new ClassPathResource(XSD_PATH).getFile();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(errorHandler);
            validator.validate(new StreamSource(new StringReader(xmlData)));


            String firstName = document.getElementsByTagName("firstName").item(0).getTextContent().trim();
            String lastName = document.getElementsByTagName("lastName").item(0).getTextContent().trim();
            if ("null".equalsIgnoreCase(firstName) || firstName.isEmpty() ||
                    "null".equalsIgnoreCase(lastName) || lastName.isEmpty()) {
                throw new ValidationException("Ошибка валидациии");
            }
            if (!errorHandler.getError().isEmpty()) {
                throw new ValidationException("Ошибка валидации", errorHandler.getError());
            }


            return false;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Ошибка конфигурации парсера XML", e);
        } catch (IOException e) {
            throw new RuntimeException("Сервер не отвечает либо ошибка чтения файла", e);
        } catch (SAXException e) {
            throw new RuntimeException("Ошибка валидации XML", e);
        }

    }
}