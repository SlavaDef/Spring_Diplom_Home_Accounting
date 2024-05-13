package com.project.chinazess.controller;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Count;
import com.project.chinazess.models.Presents;
import com.project.chinazess.models.Salary;
import com.project.chinazess.service.BonusService;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.PresentsService;
import com.project.chinazess.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {

    SalaryService salaryService;
    CountService countService;
    BonusService bonusService;
    PresentsService presentsService;


    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute( "count", countService.getCount());
        model.addAttribute( "today", salaryService.getSalaryCount());// toDo
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/add_salary")
    public String addSalary() {
        return "add_salary";
    }

    @PostMapping("/add_salary")
    public String addSalaryPost(@RequestParam Long salary, @RequestParam String description, Model model) {
        Salary sal = new Salary(salary, description);
        Count count = countService.getCountById(1L);
        sal.setCount(count);
        salaryService.addSalary(sal);

        model.addAttribute("count", countService.getCount());
        model.addAttribute("today", countService.getCount());
        return "index";

    }

    @GetMapping("/add_bonus")
    public String addBonus() {
        return "add_bonus";
    }

    @PostMapping("/add_bonus")
    public String addBonusPost(@RequestParam Long bonus, @RequestParam String description, Model model) {
        Bonus bon = new Bonus(bonus, description);
        Count count = countService.getCountById(1L);
        bon.setCount(count);
        bonusService.addBonus(bon);

        model.addAttribute("count", countService.getCount());;
        model.addAttribute("today", countService.getCount());
        return "index";

    }

    @GetMapping("/add_present")
    public String addPresent() {
        return "add_presents";
    }

    @PostMapping("/add_present")
    public String addPresentPost(@RequestParam Long present, @RequestParam String description, Model model) {
        Presents presents = new Presents(present, description);
        Count count = countService.getCountById(1L);
        presents.setCount(count);
        presentsService.addPresent(presents);

        model.addAttribute("count", countService.getCount());;
        model.addAttribute("today", countService.getCount());
        return "index";

    }

}
