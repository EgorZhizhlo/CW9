package com.rehub.controller.site;

import com.rehub.dto.AppointmentDto;
import com.rehub.model.Appointment;
import com.rehub.model.User;
import com.rehub.service.AppointmentService;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyAppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping("/my/appointments")
    public String myAppointments(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName()).orElseThrow();
        List<Appointment> list = appointmentService.findByUserId(user.getId());
        model.addAttribute("appointments", list);
        return "site/my_appointments";
    }

    @GetMapping("/my/appointments/{id}/edit")
    public String editForm(@PathVariable Long id, Authentication auth, Model model) {
        Appointment ap = appointmentService.findById(id).orElseThrow();
        User user = userService.findByUsername(auth.getName()).orElseThrow();

        if (!ap.getUser().getId().equals(user.getId())) {
            return "redirect:/my/appointments";
        }

        model.addAttribute("appointment", ap);
        return "site/appointment_form";
    }

    @PostMapping("/my/appointments/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String appointmentDateTime,
                         @RequestParam(required = false) String message,
                         Authentication auth) {

        Appointment ap = appointmentService.findById(id).orElseThrow();
        User user = userService.findByUsername(auth.getName()).orElseThrow();

        if (!ap.getUser().getId().equals(user.getId())) {
            return "redirect:/my/appointments";
        }

        LocalDateTime dt = LocalDateTime.parse(appointmentDateTime);

        AppointmentDto dto = AppointmentDto.builder()
                .appointmentDateTime(dt)
                .message(message)
                .build();

        appointmentService.update(id, dto);

        return "redirect:/my/appointments";
    }

    @PostMapping("/my/appointments/{id}/delete")
    public String delete(@PathVariable Long id, Authentication auth) {

        Appointment ap = appointmentService.findById(id).orElseThrow();
        User user = userService.findByUsername(auth.getName()).orElseThrow();

        if (!ap.getUser().getId().equals(user.getId())) {
            return "redirect:/my/appointments";
        }

        appointmentService.delete(id);
        return "redirect:/my/appointments";
    }
}
