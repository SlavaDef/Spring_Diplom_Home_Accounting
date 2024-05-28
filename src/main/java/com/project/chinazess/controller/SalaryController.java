package com.project.chinazess.controller;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Count;
import com.project.chinazess.models.Salary;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class SalaryController {

    private SalaryService salaryService;
    private CountService countService;


    //   @GetMapping("/allSalaryByDay")
    public String allSalaryByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
     //   model.addAttribute("dayList", salaryService.findAllSalaryByToday());
        return "all/all_salary";
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

        return "redirect:/";

    }

    @PostMapping("/deleteSalary")
    public String deleteSalary(Long id) {
        salaryService.deleteSalary(salaryService.getSalaryById(id));
        return "redirect:/allSalaryByDay";
    }

    @GetMapping("/editSalary/{id}") // гет шаблон для редагування
    public String salaryEdit(@PathVariable(value = "id") Long id, Model model) {
        if (salaryService.getSalaryById(id) == null) {
            return "redirect:/allSalaryByDay";
        }
        model.addAttribute("dayList", salaryService.getSalaryById(id));

        return "edit/salary_edit";
    }

    @PostMapping("/editSalary") // post релізація
    public String salaryUpdate(Long id, @RequestParam Long salary,
                               @RequestParam String description) {


        Salary sal = salaryService.getSalaryById(id);
        sal.setSalary(salary);
        sal.setDescription(description);
        salaryService.updateSalary(sal);
        return "redirect:/allSalaryByDay";
    }

    @GetMapping("/allSalaryByDay")
    public String getAllSalaryWithPageable(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {
        date = LocalDate.now();
        LocalDate test = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        List<Salary> salaryList = salaryService.allPageableSalaryByDate(
                test, PageRequest.of(page, limit));

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", salaryList);
        model.addAttribute("count", getListOfSalaryPages());
        return "all/all_salary";
    }


    private List<Integer> getListOfSalaryPages() {
        long totalCount = salaryService.count();
        long res = (totalCount / 6 + ((totalCount % 6 > 0) ? 1 : 0));
        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < res; i++) {
            numb.add(i);
        }
        return numb;
    }
}
