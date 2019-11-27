package ru.javawebinar.topjavaGraduation.web.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjavaGraduation.repository.CrudUserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class JspUserController {
    @Autowired
    private CrudUserRepository userRepository;

    @GetMapping
    public String getAll(HttpServletRequest request, Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
