package com.project.chinazess.controller;

import com.project.chinazess.models.Salary;
import com.project.chinazess.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SalaryController {

    SalaryService salaryService;

    @GetMapping("/add_salary")
    public String addSalary() {
        return "salary_add";
    }

    @PostMapping("/add_salary")
    public String addSalaryPost(@RequestParam Long salary, @RequestParam String description, Model model) {
        Salary sal = new Salary(salary, description);
        salaryService.addSalary(sal);
        model.addAttribute( "count", salaryService.getSalaryCount());
        model.addAttribute( "today", salaryService.getSalaryCount());
        return "index";

    }

}
