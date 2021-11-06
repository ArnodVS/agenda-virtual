package com.avs.demo.agenda.model.services;

import com.avs.demo.agenda.model.Contact;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface IContactService {

   List<Contact> getAll();

   Contact getById(Integer id);

   void save(Contact contact);

   void delete(Contact contact);
}
