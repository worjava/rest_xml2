package com.shop.soap_server.service.Impl;

import com.shop.soap_server.model.PurchaseInfo;

import com.shop.soap_server.model.Purchases;
import com.shop.soap_server.repository.PurchaseInfoRepository;

import com.shop.soap_server.service.PurchaseServiceCRUD;

import com.shop.soap_server.service.XMLPurchaseConverter;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class PurchaseServiceCRUDImpl implements PurchaseServiceCRUD {

    private final PurchaseInfoRepository purchaseInfoRepository;

    private final XMLPurchaseConverter xmlConverter;


    public PurchaseServiceCRUDImpl(PurchaseInfoRepository purchaseInfoRepository, XMLPurchaseConverter xmlConverter) {
        this.purchaseInfoRepository = purchaseInfoRepository;
        this.xmlConverter = xmlConverter;
    }

    public void createPurchase(String purchaseXml) {
        PurchaseInfo purchaseInfo = xmlConverter.convertToPurchaseInfo(purchaseXml);
        purchaseInfoRepository.save(purchaseInfo);

    }

    @Override
    public void updatePurchase(Long id, String purchaseXml) {
        PurchaseInfo findPurchase = purchaseInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Покупка с id " + id + " не найдена"));

        PurchaseInfo update = xmlConverter.convertToPurchaseInfo(purchaseXml);

        PurchaseInfo updatedPurchase = new PurchaseInfo(
                update.getFirstName(),
                update.getLastName(),
                update.getAge(),
                update.getCount(),
                update.getPurchaseItem(),
                update.getAmount(),
                update.getPurchaseDate()
        );
        updatedPurchase.setId(findPurchase.getId());

        purchaseInfoRepository.save(updatedPurchase);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseInfoRepository.deleteById(id);

    }

    @Override
    public String getAllPurchases() {
        List<PurchaseInfo> purchaseInfoList = purchaseInfoRepository.findAll();

        Purchases purchases = new Purchases();
        purchases.setPurchaseInfoList(purchaseInfoList);
        purchases.getPurchaseInfoList().forEach(System.out::println);
        return xmlConverter.convertToXML(purchases);
    }


    @Override
    public String getPurchaseById(Long id) {
        PurchaseInfo purchaseInfo = purchaseInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Покупка с id " + id + " не найдена"));
        Purchases purchasesWrapper = new Purchases();
        purchasesWrapper.setPurchaseInfoList(Collections.singletonList(purchaseInfo)); // Используем Arrays.asList для создания списка с одним элементом


        return xmlConverter.convertToXML(purchasesWrapper);
    }


}