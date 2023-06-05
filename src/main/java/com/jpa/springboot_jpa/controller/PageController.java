package com.jpa.springboot_jpa.controller;

import com.jpa.springboot_jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }
}
