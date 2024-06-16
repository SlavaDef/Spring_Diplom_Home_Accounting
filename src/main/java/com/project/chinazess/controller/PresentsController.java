package com.project.chinazess.controller;

import com.project.chinazess.models.Count;
import com.project.chinazess.models.Presents;
import com.project.chinazess.service.CountService;
import com.project.chinazess.service.PresentsService;
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
public class PresentsController {

    private PresentsService presentsService;
    private CountService countService;

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
    public String deletePresentByDay(Long id) {
        presentsService.deletePresent(presentsService.getPresentById(id));
        return "redirect:/allPresentsByDay";
    }

    @PostMapping("/deletePresentByWeek")
    public String deletePresentByWeek(Long id) {
        presentsService.deletePresent(presentsService.getPresentById(id));
        return "redirect:/allPresentsByWeek";
    }

    @PostMapping("/deletePresentByMonth")
    public String deletePresentByMonth(Long id) {
        presentsService.deletePresent(presentsService.getPresentById(id));
        return "redirect:/allPresentsByMonth";
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

    @GetMapping("/editPresentByWeek/{id}")
    public String presentByWeekEdit(@PathVariable(value = "id") Long id, Model model) {
        if (presentsService.getPresentById(id) == null) {
            return "redirect:/allPresentsByWeek";
        }
        model.addAttribute("weekList", presentsService.getPresentById(id));

        return "edit/present_by_week_edit";
    }

    @PostMapping("/editPresentByWeek")
    public String presentByWeekUpdate(Long id, @RequestParam Long present,
                                    @RequestParam String description) {

        Presents pre = presentsService.getPresentById(id);
        pre.setPresents(present);
        pre.setDescription(description);
        presentsService.updatePresent(pre);
        return "redirect:/allPresentsByWeek";
    }

    @GetMapping("/editPresentByMonth/{id}")
    public String presentByMonthEdit(@PathVariable(value = "id") Long id, Model model) {
        if (presentsService.getPresentById(id) == null) {
            return "redirect:/allPresentsByMonth";
        }
        model.addAttribute("monthList", presentsService.getPresentById(id));

        return "edit/present_by_month_edit";
    }

    @PostMapping("/editPresentByMonth")
    public String presentByMonthUpdate(Long id, @RequestParam Long present,
                                    @RequestParam String description) {

        Presents pre = presentsService.getPresentById(id);
        pre.setPresents(present);
        pre.setDescription(description);
        presentsService.updatePresent(pre);
        return "redirect:/allPresentsByMonth";
    }

    @GetMapping("/allPresentsByDay")
    public String getAllPresentsWithPageable(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {
        date = LocalDate.now();
        LocalDate test = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        List<Presents> salaryList = presentsService.allPageablePresentsByDate(
                test, PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", salaryList);
        model.addAttribute("count", presentsService.getListOfPresentsPages());
        return "all/all_presents";
    }

    @GetMapping("/allPresentsByWeek")
    public String getPresentsByWeek(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Presents> presentsList =
                presentsService.presentByWeek(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.with(DayOfWeek.MONDAY);
        LocalDate endDate = beginDate.plusDays(6);

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("weekList", presentsList);
        model.addAttribute("count", presentsService.getListOfPresentsPages());
        return "all_by_week/all_pres_by_week";
    }

    @GetMapping("/allPresentsByMonth")
    public String getPresentsByMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Presents> presentsList =
                presentsService.presentsByMonth(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));


        LocalDate first = LocalDate.now();
        LocalDate beginDate =
                LocalDate.of(first.getYear(), first.getMonthValue(), 1);


        LocalDate endDate = LocalDate.of(first.getYear(), first.getMonthValue(), first.lengthOfMonth());

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("monthList", presentsList);
        model.addAttribute("count", presentsService.getListOfPresentsPages());
        return "all_by_month/all_pres_by_month";
    }
}
