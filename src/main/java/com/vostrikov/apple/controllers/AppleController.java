package com.vostrikov.apple.controllers;

import com.vostrikov.apple.models.Apple;
import com.vostrikov.apple.models.ColorType;
import com.vostrikov.apple.repo.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AppleController {

    @Autowired
    private AppleRepository appleRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Apple");
        Iterable<Apple> apples = appleRepository.findAll();
        model.addAttribute("apples", apples);
        return "index";
    }

    @GetMapping("/new")
    public String appleNew(Model model) {
        model.addAttribute("title", "Apple");
        Apple apple = new Apple();
        model.addAttribute("colorTypes", ColorType.values());
        return "new";
    }

    @PostMapping("/new")
    public String applePostNew( @RequestParam Float weight,
                                @RequestParam ColorType color,
                                @RequestParam String sort,
                                @RequestParam Boolean sour, Model model) {
        Apple apple = new Apple(weight, color, sort, sour);
        appleRepository.save(apple);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String appleShow(@PathVariable(value = "id") Long id, Model model) {
        if(!appleRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Apple> apple = appleRepository.findById(id);
        ArrayList<Apple> res = new ArrayList<>();
        apple.ifPresent(res::add);
        model.addAttribute("apple", res);
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String appleEdit(@PathVariable(value = "id") Long id, Model model) {
        if(!appleRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<Apple> apple = appleRepository.findById(id);
        ArrayList<Apple> res = new ArrayList<>();
        apple.ifPresent(res::add);
        model.addAttribute("apple", res);
        model.addAttribute("colorTypes", ColorType.values());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String applePostEdit(@PathVariable(value = "id") Long id,
                                @RequestParam Float weight,
                                @RequestParam ColorType color,
                                @RequestParam String sort,
                                @RequestParam Boolean sour, @Valid Apple apple,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "edit";
        }

        //Apple apple = appleRepository.findById(id).orElseThrow();
        apple.setWeight(weight);
        apple.setColor(color);
        apple.setSort(sort);
        apple.setSour(sour);
        appleRepository.save(apple);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String applePostDelete(@PathVariable(value = "id") Long id, Model model) {
        Apple apple = appleRepository.findById(id).orElseThrow();
        appleRepository.delete(apple);
        return "redirect:/";
    }
}
