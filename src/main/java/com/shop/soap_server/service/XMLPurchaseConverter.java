package com.shop.soap_server.service;

import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.Purchases;

public interface XMLPurchaseConverter {

    PurchaseInfo convertToPurchaseInfo(String purchaseXml);

    String convertToXML(Purchases info);

}
