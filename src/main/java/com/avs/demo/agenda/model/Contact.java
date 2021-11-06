package com.avs.demo.agenda.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="contacts")
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @PrePersist
    void prePersistLocalDate(){
        dateRegister = LocalDateTime.now();
    }
}
