package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository repository;

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult,  Model model) {
        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }
        repository.save(formOffer);

        return "redirect:/pizze/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "/offers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "/offers/create-or-edit";
        }
        repository.save(formOffer);
        return "redirect:/pizze/" + formOffer.getPizza().getId();
    }

    
}
