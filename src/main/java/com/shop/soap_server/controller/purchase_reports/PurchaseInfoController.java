package com.shop.soap_server.controller.purchase_reports;

import com.shop.soap_server.model.dto.ProductInfoDto;
import com.shop.soap_server.model.dto.TopBuyerDto;
import com.shop.soap_server.service.PurchaseInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
@Tag(name = "PurchaseInfoController", description = "Контроллер для управления отчетами о покупках")
public class PurchaseInfoController {

    private final PurchaseInfoService purchaseInfoService;

    public PurchaseInfoController(PurchaseInfoService purchaseInfoService) {
        this.purchaseInfoService = purchaseInfoService;
    }

    @Operation(summary = "Получить информацию о покупках за последнюю неделю")
    @GetMapping("/last-week")
    public String getLastWeekPurchases(Model model) {
        model.addAttribute("lastWeekPurchases", purchaseInfoService.findPurchasesLastWeek());
        return "/purchaseInfo/lastWeekPurchase";
    }

    @Operation(summary = "Найти наиболее популярный товар за последний месяц")
    @GetMapping("/popular")
    public String findMostPurchasedItemLastMonth(Model model) {
        ProductInfoDto productInfo = purchaseInfoService.findMostPurchasedItemLastMonth();
        model.addAttribute("product", productInfo);
        return "/purchaseInfo/popularItem";
    }

    @Operation(summary = "Найти самого активного покупателя за последние шесть месяцев")
    @GetMapping("/top")
    public String findTopBuyerOverLastSixMonths(Model model) {
        TopBuyerDto topBuyerData = purchaseInfoService.findTopBuyerOverLastSixMonths();
        model.addAttribute("topBuyer", topBuyerData);
        return "/purchaseInfo/topByer";
    }

    @Operation(summary = "Получить информацию о популярных товарах, приобретенных лицами старше 18 лет")
    @GetMapping("/18years")
    public String getTopItemsPurchaseBy18YearsOld(Model model) {
        ProductInfoDto productInfoDto = purchaseInfoService.getTopItemPurchased18YearsOlds();
        model.addAttribute("topItem", productInfoDto);
        return "/purchaseInfo/topItems18Years";
    }
}
