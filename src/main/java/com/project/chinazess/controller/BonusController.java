package com.project.chinazess.controller;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


@AllArgsConstructor
@org.springframework.stereotype.Controller
public class BonusController {

    CountService countService;
    BonusService bonusService;


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

    @PostMapping("/deleteBonus")
    public String deleteBonus(Long id) {
        bonusService.deleteBonus(bonusService.getBonusById(id));
        return "redirect:/allBonusesByDay";
    }

    @PostMapping("/deleteBonusByWeek")
    public String deleteBonusByWeek(Long id) {
        bonusService.deleteBonus(bonusService.getBonusById(id));
        return "redirect:/allBonusesByWeek";
    }

    @GetMapping("/editBonus/{id}") // гет шаблон для редагування
    public String bonusEdit(@PathVariable(value = "id") Long id, Model model) {
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

    @GetMapping("/editBonusByWeek/{id}") // гет шаблон для редагування
    public String bonusByWeekEdit(@PathVariable(value = "id") Long id, Model model) {
        if (bonusService.getBonusById(id) == null) {
            return "redirect:/allBonusesByWeek";
        }
        model.addAttribute("dayList", bonusService.getBonusById(id));

        return "edit/bonus_by_week_edit";
    }

    @PostMapping("/editBonusByWeek") // post релізація
    public String bonusByWeekUpdate(Long id, @RequestParam Long bonus,
                              @RequestParam String description) {

        Bonus bon = bonusService.getBonusById(id);
        bon.setBonus(bonus);
        bon.setDescription(description);
        bonusService.updateBonus(bon);
        return "redirect:/allBonusesByWeek";
    }


    @GetMapping("/allBonusesByDay")
    public String getBonByToday(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {
        date = LocalDate.now();
        LocalDate test = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        List<Bonus> bonusList = bonusService.bonusByDate(
                test, PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", bonusList);
        model.addAttribute("count", bonusService.getListOfBonusPages());
        return "all/all_bonuses";
    }

    @GetMapping("/allBonusesByWeek")
    public String getBonByWeek(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {

        List<Bonus> bonusList =
                bonusService.bonusByWeek(PageRequest.of(page, limit, Sort.Direction.DESC, "id"));

        LocalDate beginDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDate endDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("weekList", bonusList);
        model.addAttribute("count", bonusService.getListOfBonusPages());
        return "all_by_week/all_bon_by_week";
    }

    // ?page=2


}
