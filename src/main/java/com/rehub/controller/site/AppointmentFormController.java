package com.rehub.controller.site;

import com.rehub.dto.AppointmentDto;
import com.rehub.model.User;
import com.rehub.service.AppointmentService;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentFormController {

    private final UserService userService;
    private final AppointmentService appointmentService;

    @GetMapping("/appointments/new")
    public String createForm(Model model) {
        List<User> doctors = userService.findByRole(User.Role.DOCTOR);
        model.addAttribute("doctors", doctors);
        return "site/appointment_create";
    }

    @PostMapping("/appointments/create")
    public String create(@RequestParam Long doctorId,
                         @RequestParam String appointmentDateTime,
                         @RequestParam(required = false) String message,
                         Authentication auth) {

        if (auth == null)
            return "redirect:/login";

        User user = userService.findByUsername(auth.getName()).orElseThrow();

        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(appointmentDateTime);
        } catch (DateTimeParseException ex) {
            return "redirect:/appointments/new?error=date";
        }

        AppointmentDto dto = AppointmentDto.builder()
                .userId(user.getId())
                .doctorId(doctorId)
                .appointmentDateTime(dt)
                .message(message)
                .build();

        appointmentService.create(dto);

        return "redirect:/my/appointments";
    }
}
