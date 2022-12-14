package com.capstone.dentalclinic.demo.controller;

import com.capstone.dentalclinic.demo.services.ConfirmationTokenService;
import com.capstone.dentalclinic.demo.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/token")
public class Token {

    private final EmployeeService employeeService;

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token, Model model) {

        String result = employeeService.confirmTokens(token);

        if(result.equalsIgnoreCase("token/AlreadyConfirmedToken")) {
            model.addAttribute("errorMessage", confirmationTokenService.getConfirmedAt(token));
            return result;
        }
        return result;
    }
}
