package com.example.demo.admin.controller;

import com.example.demo.admin.model.Admin;
import com.example.demo.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // View Admin Dashboard & Management Panel
    @GetMapping
    public String viewAdminDashboard(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("admin", new Admin());
        return "admin-management";
    }

    // Create or Update Admin Action
    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    // Load Admin Data into form for Editing
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Admin> admin = adminService.getAdminById(id);
        if (admin.isPresent()) {
            model.addAttribute("admin", admin.get());
            model.addAttribute("admins", adminService.getAllAdmins());
            return "admin-management";
        }
        return "redirect:/admins";
    }

    // Delete Admin Action
    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdminById(id);
        return "redirect:/admins";
    }
}