package com.kahveciefendi.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hikuley on 22.09.2017.
 */

@Controller
public class HomeController {


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
