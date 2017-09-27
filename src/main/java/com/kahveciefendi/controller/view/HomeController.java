package com.kahveciefendi.controller.view;

import com.kahveciefendi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hikuley on 22.09.2017.
 */

@Controller
public class HomeController {


    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/")
    public String home(Model model) {
        if (customerService.hasSession()) {
            return "index";
        } else {
            return "redirect:" + "/login";
        }
    }

    @GetMapping(value = "/login")
    public String login(Model model) {

        if (customerService.hasSession()) {
            return "redirect:" + "/";
        }

        model.addAttribute("siteTitle", "Kahveci Efendi Online Sipariş");
        return "login";
    }

}
