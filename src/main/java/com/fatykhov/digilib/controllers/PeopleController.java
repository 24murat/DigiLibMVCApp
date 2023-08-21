package com.fatykhov.digilib.controllers;

import com.fatykhov.digilib.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.fatykhov.digilib.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{personId}")
    public String show(@PathVariable("personId") int personId, Model model) {
        model.addAttribute("person", personDAO.show(personId));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(Model model, @PathVariable("personId") int personId) {
        model.addAttribute("person", personDAO.show(personId));
        return "people/edit";
    }

    @PatchMapping("/{personId}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("personId") int personId) {
        personDAO.update(personId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{personId}")
    public String delete(@PathVariable("personId") int personId) {
        personDAO.delete(personId);
        return "redirect:/people";
    }
}
