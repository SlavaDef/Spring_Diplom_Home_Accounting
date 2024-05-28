package com.project.chinazess.controller;

import com.project.chinazess.models.*;
import com.project.chinazess.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
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


    @GetMapping("/allBonusesByDay")
    public String getBon(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int limit, Model model
    ) {
        List<Bonus> bonusList = bonusService.bonusById(
                id, PageRequest.of(page, limit));
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("dayList", bonusList);
        model.addAttribute("count", getListOfPages());
        return "all/all_bonuses";
    }

    // ?page=2


    private long getPageCount() { // метод рахує скільки треба сторінок
        long totalCount = bonusService.count();
        return (totalCount / 6 + ((totalCount % 6 > 0) ? 1 : 0));
    }

    private List<Integer> getListOfPages() {

        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < getPageCount(); i++) {
            numb.add(i);
        }
        return numb;
    }


}
