package com.medilabosolutions.msgestionhistorique.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "patientNotes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNotes {

    @Id
    private String id;

    @NotEmpty(message = "Le nom du patient doit être présent")
    String patient;

    List<String> note;
}
