package com.project.chinazess.controller;

import com.project.chinazess.models.Count;
import com.project.chinazess.models.Presents;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.PresentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class PresentsController {

    private PresentsService presentsService;
    private CountService countService;

    @GetMapping("/allPresentsByDay")
    public String allSalaryByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", presentsService.findAllPresentsByToday());
        return "all/all_presents";
    }

    @GetMapping("/add_present")
    public String addPresent() {
        return "add/add_presents";
    }

    @PostMapping("/add_present")
    public String addPresentPost(@RequestParam Long present, @RequestParam(required = false) String description) {
        Presents presents = new Presents(present, description);
        Count count = countService.getCountById(1L);
        presents.setCount(count);
        presentsService.addPresent(presents);

        return "redirect:/";

    }

    @PostMapping("/deletePresent")
    public String deletePresent(Long id) {
        presentsService.deletePresent(presentsService.getPresentById(id));
        return "redirect:/allPresentsByDay";
    }

    @GetMapping("/editPresent/{id}") // гет шаблон для редагування
    public String presentEdit(@PathVariable(value = "id") Long id, Model model) {
        if (presentsService.getPresentById(id) == null) {
            return "redirect:/allPresentsByDay";
        }
        model.addAttribute("dayList", presentsService.getPresentById(id));

        return "edit/present_edit";
    }

    @PostMapping("/editPresent") // post релізація
    public String presentUpdate(Long id, @RequestParam Long present,
                                @RequestParam String description) {

        Presents pres = presentsService.getPresentById(id);
        pres.setPresents(present);
        pres.setDescription(description);
        presentsService.updatePresent(pres);
        return "redirect:/allPresentsByDay";
    }
}
