package com.acadiasoft.simplehttpapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {
    @GetMapping("/secure.html")
    public String getSecurePage(HttpServletRequest request, HttpServletResponse response) {
        if (request.isRequestedSessionIdValid()) {
            return "secure";
        } else {
            response.setStatus(302);
            response.setHeader("Location", "/login.html");

            return "redirect:/login.html";
        }
    }
}
