package com.shop.soap_server.service;

import com.shop.soap_server.model.PurchaseInfo;

import com.shop.soap_server.model.User;

import javax.xml.bind.JAXBException;


public interface XMLPurchaseUserConverter {

    PurchaseInfo convertToPurchaseInfo(String purchaseXml);

    User convertTo_User(String userXML);

    <T> String convertToXML( T object) throws JAXBException;


}
