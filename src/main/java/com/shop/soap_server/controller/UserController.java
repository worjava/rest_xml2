package com.shop.soap_server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Tag(name = "UserController", description = "Контроллер для просмотра покупками страницей пользователя")
public class UserController {

    @GetMapping("/home")
    String userPanel() {
        return "/user/home_user";


    }
}
