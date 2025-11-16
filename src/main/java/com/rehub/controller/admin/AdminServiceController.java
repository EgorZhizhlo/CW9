package com.rehub.controller.admin;

import com.rehub.model.ProgramService;
import com.rehub.service.ProgramServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/services")
public class AdminServiceController {

    private final ProgramServiceService serviceService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", serviceService.findAll());
        return "admin/services/list";
    }

    // -------- CREATE --------

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("service", new ProgramService());
        return "admin/services/form";
    }

    @PostMapping("/create")
    public String createSubmit(ProgramService service) {
        serviceService.save(service);
        return "redirect:/admin/services";
    }

    // -------- UPDATE --------

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        ProgramService service = serviceService.findById(id);
        if (service == null) {
            return "redirect:/admin/services";
        }
        model.addAttribute("service", service);
        return "admin/services/form";
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(@PathVariable Long id, ProgramService updated) {
        ProgramService existing = serviceService.findById(id);
        if (existing == null) {
            return "redirect:/admin/services";
        }

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDuration(updated.getDuration());
        existing.setPrice(updated.getPrice());

        serviceService.save(existing);

        return "redirect:/admin/services";
    }

    // -------- DELETE --------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        serviceService.delete(id);
        return "redirect:/admin/services";
    }
}
