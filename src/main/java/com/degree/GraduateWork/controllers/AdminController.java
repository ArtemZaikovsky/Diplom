package com.degree.GraduateWork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String getAdminPage() {
        return "admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRequest(@PathVariable("id") Long id) {
        return "redirect:/admin";
    }

}
