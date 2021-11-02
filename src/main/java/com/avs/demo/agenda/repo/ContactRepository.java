package com.avs.demo.agenda.repo;

import com.avs.demo.agenda.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact, Integer> {
}
