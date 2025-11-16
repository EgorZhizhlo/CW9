package com.rehub.controller.site;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {

    @GetMapping("/about")
    public String about(Model model) {

        model.addAttribute("fullName", "Мацнев Владислав Русланович");
        model.addAttribute("group", "ДЦПУП23-1");

        model.addAttribute("bio",
                "Студент направления прикладного программирования, специализируюсь на Java, Spring Boot "
                        + "и разработке современных веб-приложений. Интересуюсь архитектурой сервисов, "
                        + "интерфейсным дизайном и автоматизацией бизнес-процессов. Постоянно совершенствую навыки "
                        + "в backend-разработке и создании удобных цифровых решений.");

        model.addAttribute("skills", new String[]{
                "Java, Spring Boot, Spring Security",
                "HTML, CSS, Bootstrap, Thymeleaf",
                "PostgreSQL, Docker, REST API",
                "Проектирование админ-панелей",
                "UI/UX и структурирование пользовательского опыта"
        });

        model.addAttribute("quote",
                "«Хороший код — это тот, который делает сложное простым.»");

        return "site/about";
    }
}
