package com.kahveciefendi.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hikuley on 22.09.2017.
 */

@Controller
public class HomeController {


    @GetMapping(value = "/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("siteTitle", "Kahveci Efendi Online Sipariş");
        return "login";
    }

}
