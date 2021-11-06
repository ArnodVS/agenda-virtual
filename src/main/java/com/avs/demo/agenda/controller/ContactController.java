package com.avs.demo.agenda.controller;

import com.avs.demo.agenda.model.Contact;
import com.avs.demo.agenda.model.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("contact")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/list")
    public String getAll(Model model){
        List<Contact> contacts = contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "list";
    }

    @GetMapping("/form")
    public String create(Model model){
        model.addAttribute("title","Registrar nuevo contacto");
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping("/form")
    public String save(@Validated Contact contact, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("title", "Registrar nuevo contacto");
            model.addAttribute("contact", contact);
            return "form";
        }
        String flashMessages = (contact.getId() != null)? "Contacto editado con éxito!" : "Contacto creado con éxito!";
        contactService.save(contact);
        status.setComplete();
        flash.addFlashAttribute("successMsg", flashMessages);
        return "redirect:/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model){
        Contact contact = contactService.getById(id);
        model.addAttribute("title","Editar contacto");
        model.addAttribute("contact", contact);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Validated Contact contact,
                         BindingResult result, Model model){
        Contact contactDB = contactService.getById(id);
        if(result.hasErrors()){
            model.addAttribute("title","Actualizar contacto");
            model.addAttribute("contact", contact);
            return "form";
        }
        contactDB.setName(contact.getName());
        contactDB.setPhone(contact.getPhone());
        contactDB.setEmail(contact.getEmail());
        contactDB.setDateBirth(contact.getDateBirth());
        contactService.save(contactDB);
        return "redirect:/list";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes flash){
        Contact contact = contactService.getById(id);
        contactService.delete(contact);
        flash.addFlashAttribute("successMsg","El contacto ha sido eliminado.");
        return "redirect:/list";
    }

}
