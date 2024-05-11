package com.project.chinazess.controller;

import com.project.chinazess.service.CountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class CountController {

    private CountService countService;

   // @GetMapping("/")
   // public Long index() {
   //     return countService.getCount();
  //  }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute( "count", countService.getCount());
        return "index";
    }

}
