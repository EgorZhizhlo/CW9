package com.rehub.controller.admin;

import com.rehub.dto.AppointmentDto;
import com.rehub.model.Appointment;
import com.rehub.model.User;
import com.rehub.service.AppointmentService;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/appointments")
public class AdminAppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "admin/appointments/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {

        List<User> doctors = userService.findByRole(User.Role.DOCTOR);
        List<User> users = userService.findByRole(User.Role.USER);

        model.addAttribute("doctors", doctors);
        model.addAttribute("users", users);

        model.addAttribute("appointment", new Appointment());

        model.addAttribute("action", "/admin/appointments/create");

        return "admin/appointments/form";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long userId,
                         @RequestParam Long doctorId,
                         @RequestParam String appointmentDateTime,
                         @RequestParam(required = false) String message) {

        AppointmentDto dto = AppointmentDto.builder()
                .userId(userId)
                .doctorId(doctorId)
                .appointmentDateTime(LocalDateTime.parse(appointmentDateTime))
                .message(message)
                .build();

        appointmentService.create(dto);

        return "redirect:/admin/appointments";
    }


    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Appointment ap = appointmentService.findById(id)
                .orElseThrow();

        List<User> doctors = userService.findByRole(User.Role.DOCTOR);
        List<User> users = userService.findByRole(User.Role.USER);

        model.addAttribute("appointment", ap);
        model.addAttribute("doctors", doctors);
        model.addAttribute("users", users);

        model.addAttribute("action", "/admin/appointments/update/" + id);

        return "admin/appointments/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam Long userId,
                         @RequestParam Long doctorId,
                         @RequestParam String appointmentDateTime,
                         @RequestParam(required = false) String message) {

        AppointmentDto dto = AppointmentDto.builder()
                .userId(userId)
                .doctorId(doctorId)
                .appointmentDateTime(LocalDateTime.parse(appointmentDateTime))
                .message(message)
                .build();

        appointmentService.update(id, dto);

        return "redirect:/admin/appointments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/admin/appointments";
    }
}
