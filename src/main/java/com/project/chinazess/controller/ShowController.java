package com.project.chinazess.controller;

import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
public class ShowController {

    SalaryService salaryService;
    CountService countService;
    BonusService bonusService;
    PresentsService presentsService;
    AnotherService anotherService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("today", countService.getCountByDay());
        model.addAttribute("week", countService.getCountByWeek());
        model.addAttribute("month", countService.getCountByMonth());
        model.addAttribute("count", countService.getCount());
        return "index";
    }

    @GetMapping("/all_incomes")
    public String allIncomes(Model model) {
        model.addAttribute("salByDay", salaryService.findSalaryByToday());
        model.addAttribute("bonByDay", bonusService.findBonusByToday());
        model.addAttribute("presByDay", presentsService.findPresentsByToday());
        model.addAttribute("anotByDay", anotherService.findAnotherByToday());
        model.addAttribute("countByDay", countService.getCountByDay());

        model.addAttribute("salByWeek", salaryService.findSalaryByWeek());
        model.addAttribute("bonByWeek", bonusService.findBonusByWeek());
        model.addAttribute("presByWeek", presentsService.findPresentsByWeek());
        model.addAttribute("anotByWeek", anotherService.findAnotherByWeek());
        model.addAttribute("countByWeek", countService.getCountByWeek());

        model.addAttribute("salByMonth", salaryService.findSalaryByMonth());
        model.addAttribute("bonByMonth", bonusService.findBonusByMonth());
        model.addAttribute("presByMonth", presentsService.findPresentsByMonth());
        model.addAttribute("anotByMonth", anotherService.findByAnotherMonth());
        model.addAttribute("countByMonth", countService.getCountByMonth());

        model.addAttribute("salByYear", salaryService.findSalaryByYear());
        model.addAttribute("bonByYear", bonusService.findBonusByYear());
        model.addAttribute("presByYear", presentsService.findPresentsByYear());
        model.addAttribute("anotByYear", anotherService.findAnotherByYear());
        model.addAttribute("countByYear", countService.getCountByYear());

        return "all/all_incomes";
    }

    @GetMapping("/add")
    public String add() {
        return "add/add";
    }

    @GetMapping("/allBonusesByDay")
    public String allBonusByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", bonusService.findAllBonusesByToday());
        return "all/all_bonuses";
    }

    @GetMapping("/allAnotherByDay")
    public String allAnotherByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", anotherService.findAllAnothersByToday());
        return "all/all_another";
    }

    @GetMapping("/allSalaryByDay")
    public String allSalaryByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", salaryService.findAllSalaryByToday());
        return "all/all_salary";
    }

}
