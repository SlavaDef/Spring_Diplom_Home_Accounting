package com.project.chinazess.controller;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@AllArgsConstructor
@org.springframework.stereotype.Controller
public class AddController {
    private static final Logger LOGGER = LogManager.getLogger(AddController.class);

    SalaryService salaryService;
    CountService countService;
    BonusService bonusService;
    PresentsService presentsService;
    AnotherService anotherService;


    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute( "today", countService.getCountByDay());
        model.addAttribute( "week", countService.getCount());
        model.addAttribute( "month", bonusService.findBonusByToday());
        model.addAttribute( "count", countService.getCount());
       // model.addAttribute( "byMonth", bonusService.findBonusByDate_Month(2));
       // model.addAttribute( "countbyid", countService.getCountById2(1L));
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
    public String addSalaryPost(@RequestParam Long salary, @RequestParam String description) {
        Salary sal = new Salary(salary, description);
        Count count = countService.getCountById2(1L);
        sal.setCount(count);
        salaryService.addSalary(sal);

      //  count.setSalaries(salaryService.getAllSalaries());
      //  countService.updateCount(count);

        return "redirect:/";

    }

    @GetMapping("/add_bonus")
    public String addBonus() {
        return "add_bonus";
    }

    @PostMapping("/add_bonus")
    public String addBonusPost(@RequestParam Long bonus, @RequestParam String description) {
        Bonus bon = new Bonus(bonus, description);
        Count count = countService.getCountById(1L);
        bon.setCount(count);
        bonusService.addBonus(bon);

        return "redirect:/";

    }

    @GetMapping("/add_present")
    public String addPresent() {
        return "add_presents";
    }

    @PostMapping("/add_present")
    public String addPresentPost(@RequestParam Long present, @RequestParam String description) {
        Presents presents = new Presents(present, description);
        Count count = countService.getCountById(1L);
        presents.setCount(count);
        presentsService.addPresent(presents);

        return "redirect:/";

    }

    @GetMapping("/add_another")
    public String addAnotherPresent() {
        return "add_another";
    }

    @PostMapping("/add_another")
    public String addAnotherPost(@RequestParam Long another, @RequestParam String description) {
        Another a = new Another(another, description);
        Count count = countService.getCountById(1L);
        a.setCount(count);
        anotherService.addAnother(a);

        return "redirect:/";

    }

    @GetMapping("/all_incomes")
    public String allIncomes(Model model) {
        model.addAttribute( "sal", salaryService.getSalaryCount());
        model.addAttribute( "bon", bonusService.getBonusCount());
        model.addAttribute( "pres", presentsService.getPresentCount());
        model.addAttribute( "anot", anotherService.findAnotherByToday());
        model.addAttribute( "all_count", countService.getCount());
        model.addAttribute( "byMonth", bonusService.findBonusByToday());
        model.addAttribute( "bonByYear", bonusService.findBonusByYear());
        return "all_incomes";
    }



}
