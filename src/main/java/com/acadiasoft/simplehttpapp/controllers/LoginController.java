package com.acadiasoft.simplehttpapp.controllers;

import com.acadiasoft.simplehttpapp.models.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private final Map<String, String> loginCredentials = new HashMap<>();

    @GetMapping("/login.html")
    public String getLogin() {
        return "login";
    }

    @PostMapping(path = "/login.html", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String handleLogin(@ModelAttribute(name = "loginForm") Login login, RedirectAttributes model,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.fillLoginCredentials();
        String givenUsername = login.getUsername();
        String givenPassword = login.getPassword();

        if (loginCredentials.get(givenUsername) != null && loginCredentials.get(givenUsername).equals(givenPassword)) {
            session.invalidate();
            HttpSession newSession = request.getSession(true);

            model.addFlashAttribute("username", givenUsername);

            response.setStatus(302);
            response.setHeader("Location", "/secure");
            response.setHeader("Set-Cookie", "sessionId=" + newSession.getId());

            return "redirect:/secure.html";
        } else {
            model.addFlashAttribute("error", "Username and/or Password Incorrect");

            response.setStatus(401);

            return "redirect:/login.html";
        }
    }

    private void fillLoginCredentials() {
        loginCredentials.put("admin", "admin123");
        loginCredentials.put("user", "user123");
        loginCredentials.put("george123", "password");
    }
}
