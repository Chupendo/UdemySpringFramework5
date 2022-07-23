package com.breaklimit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("usuario")
public class IndexController {
    @GetMapping(value={"/","home","index"})
    public String home(ModelAndView model){
        return "home";
    }
}
