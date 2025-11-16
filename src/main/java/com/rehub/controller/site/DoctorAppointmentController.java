package com.rehub.controller.site;

import com.rehub.model.Appointment;
import com.rehub.model.User;
import com.rehub.service.AppointmentService;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorAppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @GetMapping("/doctor/patients")
    public String patients(Authentication auth, Model model) {

        User user = userService.findByUsername(auth.getName()).orElseThrow();

        if (user.getRole() != User.Role.DOCTOR) {
            return "redirect:/";
        }

        List<Appointment> list = appointmentService.findByDoctorId(user.getId());
        model.addAttribute("appointments", list);

        return "site/doctor_patients";
    }
}
