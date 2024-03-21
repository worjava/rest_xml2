package com.shop.soap_server.controller;


import com.shop.soap_server.service.PurchaseServiceCRUD;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/api/admin")
@Tag(name = "AdminController", description = "Административный контроллер упралвения покупками и панели администратора")
public class AdminController {

    private final PurchaseServiceCRUD purchaseServiceCRUD;

    public AdminController(PurchaseServiceCRUD purchaseServiceCRUD) {
        this.purchaseServiceCRUD = purchaseServiceCRUD;
    }

    @GetMapping(value = "/all")
    public String getAllPurchases() {
        return "admin/purchases_admin";
    }

    @GetMapping("/home")
    public String adminPanel() {
        return "admin/home_admin";
    }

    @GetMapping("/add")
    String addPurchase() {

        return "admin/add";
    }

    @GetMapping("/update/{id}")
    public String updatePurchase(@PathVariable Long id, Model model) {
        String XML = purchaseServiceCRUD.getPurchaseById(id);
        model.addAttribute("XML",XML);
        return "admin/update";

    }


}

