package com.medilabosolutions.msgestionpatient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "prenom")
    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @Column(name = "nom")
    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @Column(name = "date_de_naissance")
    @NotEmpty(message ="La date de naissance est obligatoire")
    String dateDeNaissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    @NotNull(message ="Le genre est obligatoire")
    Genre genre;

    @Column(name = "adresse_postale")
    String adressePostale;

    @Column(name = "numero_de_telephone")
    String numeroDeTelephone;
}

