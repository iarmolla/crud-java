package com.example.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class User {
    @Getter @Setter @Column(name = "id") @Id
    private int id;

    @Getter @Setter @Column(name = "nombre")
    private String name;

    @Getter @Setter @Column(name = "apellido")
    private String lastname;

    @Getter @Setter @Column(name = "email")
    private String mail;

    @Getter @Setter @Column(name = "contraseña")
    private String password;
}
