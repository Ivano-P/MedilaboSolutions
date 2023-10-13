package com.medilabosolutions.msgestionhistorique.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents the notes associated with a specific patient.
 * This model class maps to the "patientNotes" collection in the MongoDB database.
 * Each instance of this class corresponds to a document in the "patientNotes" collection.
 * The class contains fields such as the unique ID, patient's name, and a list of notes.
 *
 * @author Ivano
 */
@Document(collection = "patientNotes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNotes {

    /** The unique ID of the patient notes document. */
    @Id
    private String id;

    /** The name of the patient associated with the notes.
     *  This field is mandatory and cannot be empty.
     */
    @NotEmpty(message = "Le nom du patient doit être présent")
    String patient;

    /** A list of notes associated with the patient. */
    List<String> note;
}
