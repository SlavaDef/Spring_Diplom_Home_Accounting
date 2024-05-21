package com.project.chinazess.controller;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@org.springframework.stereotype.Controller
public class AddController {


    SalaryService salaryService;
    CountService countService;
    BonusService bonusService;
    PresentsService presentsService;
    AnotherService anotherService;


    @GetMapping("/add")
    public String add() {
        return "add/add";
    }

    @GetMapping("/add_salary")
    public String addSalary() {
        return "add/add_salary";
    }

    @PostMapping("/add_salary")
    public String addSalaryPost(@RequestParam Long salary, @RequestParam(required = false) String description) {
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
        return "add/add_bonus";
    }

    @PostMapping("/add_bonus")
    public String addBonusPost(@RequestParam Long bonus, @RequestParam(required = false) String description) {
        Bonus bon = new Bonus(bonus, description);
        Count count = countService.getCountById(1L);
        bon.setCount(count);
        bonusService.addBonus(bon);

        return "redirect:/";

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

    @PostMapping("/deleteBonus")
    public String deleteBonus(Long id) {
        bonusService.deleteBonus(bonusService.getBonusById(id));
        return "redirect:/allBonusesByDay";
    }

    @GetMapping("/editBonus/{id}") // гет шаблон для редагування
    public String RemiEdit(@PathVariable(value = "id") Long id, Model model) {
        if (bonusService.getBonusById(id) == null) {
            return "redirect:/allBonusesByDay";
        }
        model.addAttribute("dayList", bonusService.getBonusById(id));

        return "edit/bonus_edit";
    }

    @PostMapping("/editBonus") // post релізація
    public String bonusUpdate(Long id, @RequestParam Long bonus,
                             @RequestParam String description) {

        Bonus bon = bonusService.getBonusById(id);
        bon.setBonus(bonus);
        bon.setDescription(description);
        bonusService.updateBonus(bon);
        return "redirect:/allBonusesByDay";
    }


}
