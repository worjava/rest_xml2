package com.shop.soap_server.controller;

import com.shop.soap_server.service.PurchaseUserServiceCRUD;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase")
@Tag(name = "PurchaseXMLController", description = "Контроллер для управления CRUD покупками в формате XML")
public class PurchaseXMLController {

    private final PurchaseUserServiceCRUD service;


    public PurchaseXMLController(PurchaseUserServiceCRUD service) {
        this.service = service;

    }

    @PostMapping("/save")
    @Operation(summary = "Создать покупку", description = "Добавляет новую покупку из XML")
    @ApiResponse(responseCode = "201", description = "Покупка добавлена", content = @Content)
    public ResponseEntity<String> createPurchase(@RequestBody String xml) {
        service.createPurchase(xml);
        return ResponseEntity.status(HttpStatus.CREATED).body("Покупка добавлена");
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Обновить покупку", description = "Обновляет существующую покупку по ID из XML")
    @ApiResponse(responseCode = "200", description = "Покупка обновлена", content = @Content)
    public ResponseEntity<String> updatePurchase(@PathVariable Long id, @RequestBody String xml) {
        service.updatePurchase(id, xml);
        return ResponseEntity.ok("Покупка обновлена");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить покупку", description = "Удаляет покупку по указанному ID")
    @ApiResponse(responseCode = "200", description = "Покупка удалена", content = @Content)
    public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
        service.deletePurchase(id);
        return ResponseEntity.ok("Покупка удалена");
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Найти покупку по ID", description = "Возвращает покупку по указанному ID")
    @ApiResponse(responseCode = "200", description = "Покупка найдена", content = @Content(mediaType = "application/xml"))
    public ResponseEntity<?> getPurchaseById(@PathVariable Long id) {
        String purchase = service.getPurchaseById(id);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Получить все покупки", description = "Возвращает список всех покупок в формате XML")
    @ApiResponse(responseCode = "200", description = "Список покупок", content = @Content(mediaType = "application/xml"))
    public ResponseEntity<?> getAllPurchases() {
        Object purchases = service.getAllPurchases();
        return new ResponseEntity<>(purchases, HttpStatus.OK);

    }
}