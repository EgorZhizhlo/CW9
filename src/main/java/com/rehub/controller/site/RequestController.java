package com.rehub.controller.site;

import com.rehub.model.Request;
import com.rehub.model.Request.Status;
import com.rehub.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/request")
    public String create(@RequestParam String name,
                         @RequestParam String phone,
                         @RequestParam(required = false) String message) {

        Request request = Request.builder()
                .name(name)
                .phone(phone)
                .message(message)
                .createdAt(LocalDateTime.now())
                .status(Status.NEW)
                .build();

        requestService.save(request);

        return "redirect:/success";
    }
}
