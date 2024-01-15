package com.example.libraryProject.Controller.Manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }
}
