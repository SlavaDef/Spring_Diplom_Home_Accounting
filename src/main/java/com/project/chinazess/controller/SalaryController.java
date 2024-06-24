package com.project.chinazess.controller;

import com.project.chinazess.models.Count;
import com.project.chinazess.models.Salary;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class SalaryController {

    private SalaryService salaryService;
    private CountService countService;


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

    @PostMapping("/deleteSalaryByWeek")
    public String deleteSalaryByWeek(Long id) {
        salaryService.deleteSalary(salaryService.getSalaryById(id));
        return "redirect:/allSalaryByWeek";
    }

    @PostMapping("/deleteSalaryByMonth")
    public String deleteSalaryByMonth(Long id) {
        salaryService.deleteSalary(salaryService.getSalaryById(id));
        return "redirect:/allSalaryByMonth";
    }

    @PostMapping("/deleteSalaryByYear")
    public String deleteSalaryByYear(Long id) {
        salaryService.deleteSalary(salaryService.getSalaryById(id));
        return "redirect:/allSalaryByYear";
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

    @GetMapping("/editSalaryByWeek/{id}") // гет шаблон для редагування
    public String salaryByWeekEdit(@PathVariable(value = "id") Long id, Model model) {
        if (salaryService.getSalaryById(id) == null) {
            return "redirect:/allSalaryByWeek";
        }
        model.addAttribute("dayList", salaryService.getSalaryById(id));

        return "edit/salary_by_week_edit";
    }

    @PostMapping("/editSalaryByWeek") // post релізація
    public String salaryByWeekUpdate(Long id, @RequestParam Long salary,
                                    @RequestParam String description) {

        Salary sal = salaryService.getSalaryById(id);
        sal.setSalary(salary);
        sal.setDescription(description);
        salaryService.updateSalary(sal);
        return "redirect:/allSalaryByWeek";
    }

    @GetMapping("/editSalaryByMonth/{id}")
    public String salaryByMonthEdit(@PathVariable(value = "id") Long id, Model model) {
        if (salaryService.getSalaryById(id) == null) {
            return "redirect:/allSalaryByMonth";
        }
        model.addAttribute("monthList", salaryService.getSalaryById(id));

        return "edit/salary_by_month_edit";
    }

    @PostMapping("/editSalaryByMonth")
    public String salaryByMonthUpdate(Long id, @RequestParam Long salary,
                                     @RequestParam String description) {

        Salary sal = salaryService.getSalaryById(id);
        sal.setSalary(salary);
        sal.setDescription(description);
        salaryService.updateSalary(sal);
        return "redirect:/allSalaryByMonth";
    }

    @GetMapping("/editSalaryByYear/{id}")
    public String salaryByYearEdit(@PathVariable(value = "id") Long id, Model model) {
        if (salaryService.getSalaryById(id) == null) {
            return "redirect:/allSalaryByYear";
        }
        model.addAttribute("yearList", salaryService.getSalaryById(id));

        return "edit/salary_by_year_edit";
    }

    @PostMapping("/editSalaryByYear")
    public String salaryByYearUpdate(Long id, @RequestParam Long salary,
                                      @RequestParam String description) {

        Salary sal = salaryService.getSalaryById(id);
        sal.setSalary(salary);
        sal.setDescription(description);
        salaryService.updateSalary(sal);
        return "redirect:/allSalaryByYear";
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
                test, PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", salaryList);
        model.addAttribute("count", salaryService.getListOfSalaryPages());
        return "all/all_salary";
    }

    @GetMapping("/allSalaryByWeek")
    public String getAllSalaryByWeek(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Salary> salaryList =
                salaryService.salariesByWeek(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.with(DayOfWeek.MONDAY);
        LocalDate endDate = beginDate.plusDays(6);

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("weekList", salaryList);
        model.addAttribute("count", salaryService.getListOfSalaryPages());
        return "all_by_week/all_sal_by_week";
    }

    @GetMapping("/allSalaryByMonth")
    public String getAllSalaryByMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Salary> salaryList =
                salaryService.salariesByMonth(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate first = LocalDate.now();
        LocalDate beginDate =
                LocalDate.of(first.getYear(), first.getMonthValue(), 1);

        LocalDate endDate = LocalDate.of(first.getYear(), first.getMonthValue(), first.lengthOfMonth());
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("monthList", salaryList);
        model.addAttribute("count", salaryService.getListOfSalaryPages());
        return "all_by_month/all_sal_by_month";
    }

    @GetMapping("/allSalaryByYear")
    public String getAllSalaryByYear(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Salary> salaryList =
                salaryService.salariesByYear(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate beginDate =
                LocalDate.of(LocalDate.now().getYear(), 1, 1);


        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("yearList", salaryList);
        model.addAttribute("count", salaryService.getListOfSalaryPages());
        return "all_by_year/all_sal_by_year";
    }

}
