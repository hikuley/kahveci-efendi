package com.kahveciefendi.controller.view;

import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hikuley on 22.09.2017.
 */

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CustomerService customerService;


    @GetMapping(value = "/")
    public String home(Model model) {
        if (customerService.hasSession()) {
            Customer customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
            model.addAttribute("content", "orderView");
            return "index";
        } else {
            return "redirect:" + "/login";
        }
    }

    @GetMapping(value = "/history")
    public String historyView(Model model) {
        if (customerService.hasSession()) {
            Customer customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
            model.addAttribute("content", "historyView");
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

    @GetMapping(value = "/logout")
    public String logout() {

        log.debug("The customer logged out.");

        customerService.logout();
        return "redirect:" + "/login";
    }


}
