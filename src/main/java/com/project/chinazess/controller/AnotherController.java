package com.project.chinazess.controller;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Count;
import com.project.chinazess.service.AnotherService;
import com.project.chinazess.service.CountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AnotherController {


   private AnotherService anotherService;
   private CountService countService;

    @GetMapping("/add_another")
    public String addAnotherPresent() {
        return "add/add_another";
    }

    @PostMapping("/add_another")
    public String addAnotherPost(@RequestParam Long another, @RequestParam(required = false) String description) {
        Another a = new Another(another, description);
        Count count = countService.getCountById(1L);
        a.setCount(count);
        anotherService.addAnother(a);

        return "redirect:/";

    }

    @PostMapping("/deleteAnother")
    public String deleteAnother(Long id) {
        anotherService.deleteAnother(anotherService.getAnotherById(id));
        return "redirect:/allAnotherByDay";
    }

    @GetMapping("/editAnother/{id}") // гет шаблон для редагування
    public String anotherEdit(@PathVariable(value = "id") Long id, Model model) {
        if (anotherService.getAnotherById(id) == null) {
            return "redirect:/allAnotherByDay";
        }
        model.addAttribute("dayList", anotherService.getAnotherById(id));

        return "edit/another_edit";
    }

    @PostMapping("/editAnother") // post релізація
    public String anotherUpdate(Long id, @RequestParam Long another,
                              @RequestParam String description) {

        Another en = anotherService.getAnotherById(id);

        en.setAnother(another);
        en.setDescription(description);
        anotherService.updateBonus(en);
        return "redirect:/allAnotherByDay";
    }
}
