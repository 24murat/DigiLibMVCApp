package com.fatykhov.digilib.controllers;

import com.fatykhov.digilib.services.BooksService;
import com.fatykhov.digilib.services.PeopleService;
//import com.fatykhov.digilib.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fatykhov.digilib.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BooksService bookService;

//    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
//        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{personId}")
    public String show(@PathVariable("personId") int personId, Model model) {
        model.addAttribute("person", peopleService.findOne(personId));
        model.addAttribute("personBooks", peopleService.showBookList(personId));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(Model model, @PathVariable("personId") int personId) {
        model.addAttribute("person", peopleService.findOne(personId));
        return "people/edit";
    }

    @PatchMapping("/{personId}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("personId") int personId) {
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.update(personId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{personId}")
    public String delete(@PathVariable("personId") int personId) {
        peopleService.delete(personId);
        return "redirect:/people";
    }
}
