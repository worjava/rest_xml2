package com.shop.soap_server.service;

public interface PurchaseUserServiceCRUD {

    void createPurchase(String purchaseXml);

    void updatePurchase(Long id, String purchaseXml);

    void deletePurchase(Long id);

     String getAllPurchases();

    String getPurchaseById(Long id);


}
