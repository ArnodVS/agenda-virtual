package com.avs.demo.agenda.model.repo;

import com.avs.demo.agenda.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactDao extends JpaRepository <Contact, Integer> {
}
