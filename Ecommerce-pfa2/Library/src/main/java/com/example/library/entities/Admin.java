package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    @Lob
    private String image;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)// #LAZY si je charge les donnees d'utilisateur de la bdd il ne va pas charger les donnes des roles d'une maniere automatique
    @JoinTable(name = "admins_roles",joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "admin_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Collection<Role> Roles=new ArrayList<>();
}
