package com.shop.soap_server.service;

import javax.xml.parsers.ParserConfigurationException;

public interface XMLValidatorService {

    boolean isValid(String xmlData) throws ParserConfigurationException;


    }
