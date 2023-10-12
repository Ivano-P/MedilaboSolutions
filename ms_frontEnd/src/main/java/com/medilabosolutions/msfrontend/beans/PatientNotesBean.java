package com.medilabosolutions.msfrontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNotesBean {

    private String id;

    String patient;

    List<String> note;
}
