package com.medilabosolutions.msgestionrisque.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientNotesBean {

    private int id;

    String patient;

    List<String> note;
}
