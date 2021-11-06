package com.avs.demo.agenda.model.services.imp;

import com.avs.demo.agenda.model.Contact;
import com.avs.demo.agenda.model.repo.IContactDao;
import com.avs.demo.agenda.model.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImp implements IContactService {

    @Autowired
    private IContactDao contactDao;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getAll() {
        return (List<Contact>) contactDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact getById(Integer id) {
        return contactDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Contact contact) {
        contactDao.save(contact);
    }

    @Override
    @Transactional
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }
}
