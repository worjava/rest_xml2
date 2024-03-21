package com.shop.soap_server.service;

import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.dto.ProductInfoDto;
import com.shop.soap_server.model.dto.TopBuyerDto;

import java.util.List;

public interface PurchaseInfoService {

    List<PurchaseInfo> findPurchasesLastWeek();

    ProductInfoDto findMostPurchasedItemLastMonth();

    ProductInfoDto getTopItemPurchased18YearsOlds();

    TopBuyerDto findTopBuyerOverLastSixMonths();


}



