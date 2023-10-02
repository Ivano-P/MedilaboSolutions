package com.medilabosolutions.msgestionpatient.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private String id;

    @NotEmpty(message ="Le prénom est obligatoire")
    String prenom;

    @NotEmpty(message ="Le nom est obligatoire")
    String nom;

    @NotNull(message ="La date de naissance est obligatoire")
    LocalDate dateDeNaissance;

    @NotNull(message ="Le genre est obligatoire")
    Genre genre;

    String adressePostale;

    String numeroDeTelephone;
}

