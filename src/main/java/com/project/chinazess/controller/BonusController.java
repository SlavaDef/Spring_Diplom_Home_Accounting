package com.project.chinazess.controller;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@AllArgsConstructor
@org.springframework.stereotype.Controller
public class BonusController {

    CountService countService;
    BonusService bonusService;

    @GetMapping("/allBonusesByDay")
    public String allBonusByDay(Model model) {

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", bonusService.findAllBonusesByToday());
        return "all/all_bonuses";
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

    @PostMapping("/deleteBonus")
    public String deleteBonus(Long id) {
        bonusService.deleteBonus(bonusService.getBonusById(id));
        return "redirect:/allBonusesByDay";
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


}
