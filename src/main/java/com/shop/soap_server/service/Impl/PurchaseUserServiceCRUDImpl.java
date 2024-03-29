package com.shop.soap_server.service.Impl;

import com.shop.soap_server.model.PurchaseInfo;


import com.shop.soap_server.model.User;

import com.shop.soap_server.model.dto.UserPurchaseDto;
import com.shop.soap_server.model.wrapperXML.PurchasesUsersWrapper;
import com.shop.soap_server.repository.PurchaseInfoRepository;

import com.shop.soap_server.repository.UserRepository;
import com.shop.soap_server.service.PurchaseUserServiceCRUD;

import com.shop.soap_server.service.XMLPurchaseUserConverter;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBException;
import java.util.Collections;
import java.util.List;

@Service
public class PurchaseUserServiceCRUDImpl implements PurchaseUserServiceCRUD {

    private final PurchaseInfoRepository purchaseInfoRepository;

    private final UserRepository userRepository;

    private final XMLPurchaseUserConverter xmlConverter;

    public PurchaseUserServiceCRUDImpl(PurchaseInfoRepository purchaseInfoRepository, UserRepository userRepository, XMLPurchaseUserConverter xmlConverter) {
        this.purchaseInfoRepository = purchaseInfoRepository;
        this.userRepository = userRepository;
        this.xmlConverter = xmlConverter;
    }

    public void createPurchase(String purchaseXml) {
        User user = xmlConverter.convertTo_User(purchaseXml);
        userRepository.save(user);

        PurchaseInfo purchaseInfo = xmlConverter.convertToPurchaseInfo(purchaseXml);
        purchaseInfo.setUser(user);
        purchaseInfoRepository.save(purchaseInfo);


    }

    @Override
    public void updatePurchase(Long id, String purchaseXml) {
        PurchaseInfo existingPurchase = purchaseInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Покупка с id " + id + " не найдена"));

        PurchaseInfo updatedData = xmlConverter.convertToPurchaseInfo(purchaseXml);
        User updatedUser = xmlConverter.convertTo_User(purchaseXml);

        User user = existingPurchase.getUser();
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAge(updatedUser.getAge());
        userRepository.save(user);


        existingPurchase.setCount(updatedData.getCount());
        existingPurchase.setPurchaseItem(updatedData.getPurchaseItem());
        existingPurchase.setAmount(updatedData.getAmount());
        existingPurchase.setPurchaseDate(updatedData.getPurchaseDate());
        purchaseInfoRepository.save(existingPurchase);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseInfoRepository.deleteById(id);

    }

    @Override
    public String getAllPurchases() {
        List<PurchaseInfo> purchases = purchaseInfoRepository.findAll();

        PurchasesUsersWrapper purchasesUsersWrapper = new PurchasesUsersWrapper(
                purchases);
        try {
            return xmlConverter.convertToXML(purchasesUsersWrapper);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPurchaseById(Long id) {
        PurchaseInfo purchaseIno = purchaseInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Покупка с id " + id + " не найдена"));

        PurchasesUsersWrapper usersWrapper = new PurchasesUsersWrapper(purchaseIno);

        try {
            return xmlConverter.convertToXML(usersWrapper);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


}