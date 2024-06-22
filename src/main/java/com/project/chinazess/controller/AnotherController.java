package com.project.chinazess.controller;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Count;
import com.project.chinazess.service.AnotherService;
import com.project.chinazess.service.CountService;
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
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/deleteAnotherByWeek")
    public String deleteAnotherByWeek(Long id) {
        anotherService.deleteAnother(anotherService.getAnotherById(id));
        return "redirect:/allAnotherByWeek";
    }

    @PostMapping("/deleteAnotherByMonth")
    public String deleteAnotherByMoth(Long id) {
        anotherService.deleteAnother(anotherService.getAnotherById(id));
        return "redirect:/allAnotherByMonth";
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
        anotherService.updateAnother(en);
        return "redirect:/allAnotherByDay";
    }

    @GetMapping("/editAnotherByWeek/{id}")
    public String anotherByWeekEdit(@PathVariable(value = "id") Long id, Model model) {
        if (anotherService.getAnotherById(id) == null) {
            return "redirect:/allAnotherByWeek";
        }
        model.addAttribute("weekList", anotherService.getAnotherById(id));

        return "edit/another_by_week_edit";
    }

    @PostMapping("/editAnotherByWeek")
    public String anotherByWeekUpdate(Long id, @RequestParam Long another,
                                      @RequestParam String description) {
        Another en = anotherService.getAnotherById(id);
        en.setAnother(another);
        en.setDescription(description);
        anotherService.updateAnother(en);
        return "redirect:/allAnotherByWeek";

    }

    @GetMapping("/editAnotherByMonth/{id}")
    public String anotherByMonthEdit(@PathVariable(value = "id") Long id, Model model) {
        if (anotherService.getAnotherById(id) == null) {
            return "redirect:/allAnotherByMonth";
        }
        model.addAttribute("monthList", anotherService.getAnotherById(id));

        return "edit/another_by_month_edit";
    }

    @PostMapping("/editAnotherByMonth")
    public String anotherByMonthUpdate(Long id, @RequestParam Long another,
                                      @RequestParam String description) {
        Another en = anotherService.getAnotherById(id);
        en.setAnother(another);
        en.setDescription(description);
        anotherService.updateAnother(en);
        return "redirect:/allAnotherByMonth";

    }

    @GetMapping("/allAnotherByDay")
    public String getAllAnotherWithPageable(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {
        date = LocalDate.now();
        LocalDate test = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        List<Another> anotherList = anotherService.allPageableAnotherByDate(
                test, PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", anotherList);
        model.addAttribute("count", getListOfAnotherPages());
        return "all/all_another";
    }

    @GetMapping("/allAnotherByWeek")
    public String getAllAnotherByWeek(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Another> anotherList =
                anotherService.anothersByWeek(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.with(DayOfWeek.MONDAY);
        LocalDate endDate = beginDate.plusDays(6);

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("weekList", anotherList);
        model.addAttribute("count", getListOfAnotherPages());
        return "all_by_week/all_another_by_week";
    }

    @GetMapping("/allAnotherByMonth")
    public String getAllAnotherByMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Another> anotherList =
                anotherService.anothersByMonth(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate first = LocalDate.now();
        LocalDate beginDate =
                LocalDate.of(first.getYear(), first.getMonthValue(), 1);


        LocalDate endDate = LocalDate.of(first.getYear(), first.getMonthValue(), first.lengthOfMonth());

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("monthList", anotherList);
        model.addAttribute("count", getListOfAnotherPages());
        return "all_by_month/all_another_by_month";
    }


    private List<Integer> getListOfAnotherPages() {
        long res = (anotherService.count() / 6 + ((anotherService.count() % 6 > 0) ? 1 : 0));
        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < res; i++) {
            numb.add(i);
        }
        return numb;
    }
}
