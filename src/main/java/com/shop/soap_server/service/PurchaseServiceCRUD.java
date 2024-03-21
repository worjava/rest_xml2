package com.shop.soap_server.service;

import com.shop.soap_server.model.PurchaseInfo;

import java.util.List;

public interface PurchaseServiceCRUD {

    void createPurchase(String purchaseXml);

    void updatePurchase(Long id, String purchaseXml);

    void deletePurchase(Long id);

     String getAllPurchases();

    String getPurchaseById(Long id);


}
