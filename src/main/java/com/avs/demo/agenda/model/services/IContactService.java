package com.avs.demo.agenda.model.services;

import com.avs.demo.agenda.model.Contact;

import java.util.List;

public interface IContactService {

   List<Contact> getAll();

   Contact getById(Integer id);

   void save(Contact contact);

   void delete(Contact contact);
}
