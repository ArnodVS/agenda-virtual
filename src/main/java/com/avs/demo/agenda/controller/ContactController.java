package com.avs.demo.agenda.controller;

import com.avs.demo.agenda.model.Contact;
import com.avs.demo.agenda.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("")
    public String read(Model model){
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/form")
    public String register(Model model){
        model.addAttribute("title","Registrar nuevo contacto");
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping("/form")
    public String create(@Validated Contact contact, BindingResult result,
                         Model model, RedirectAttributes flash){
        if(result.hasErrors()){
            model.addAttribute("contact", contact);
            return "form";
        }
        contactRepository.save(contact);
        flash.addFlashAttribute("successMsg", "El contacto ha sido creado correctamente!");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model){
        Contact contact = contactRepository.getById(id);
        model.addAttribute("title","Editar contacto");
        model.addAttribute("contact", contact);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Validated Contact contact,
                         BindingResult result, Model model, RedirectAttributes flash){
        Contact contactDB = contactRepository.getById(id);
        if(result.hasErrors()){
            model.addAttribute("contact", contact);
            return "form";
        }
        // service
        contactDB.setName(contact.getName());
        contactDB.setPhone(contact.getPhone());
        contactDB.setEmail(contact.getEmail());
        contactDB.setDateBirth(contact.getDateBirth());

        contactRepository.save(contactDB);
        flash.addFlashAttribute("successMsg", "El contacto ha sido actualizado correctamente!");
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes flash){
        Contact contact = contactRepository.getById(id);
        contactRepository.delete(contact);
        flash.addFlashAttribute("successMsg","El contacto ha sido eliminado.");
        return "redirect:/";
    }

}
