package com.medilabosolutions.msgestionhistorique.controller;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientNotesControllerTest {

    @InjectMocks
    PatientNotesController patientNotesController;

    @Mock
    PatientNotesService patientNotesService;

    @Test
    void testGetAllPatientNotes() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        List<PatientNotes> mockNotesList = List.of(mockNote);
        when(patientNotesService.findAllPatientNotes()).thenReturn(mockNotesList);

        // Act
        List<PatientNotes> result = patientNotesController.getAllPatientNotes();

        // Assert
        assertThat(result).isEqualTo(mockNotesList);
        verify(patientNotesService, times(1)).findAllPatientNotes();
    }

    @Test
    void testFindPatientNoteById() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesService.findPatientNotesById("1")).thenReturn(mockNote);

        // Act
        PatientNotes result = patientNotesController.findPatientNoteById("1");

        // Assert
        assertThat(result).isEqualTo(mockNote);
        verify(patientNotesService, times(1)).findPatientNotesById("1");
    }

    @Test
    void testFindPatientNoteByPatientName() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesService.findPatientNotesByPatientName("John")).thenReturn(mockNote);

        // Act
        PatientNotes result = patientNotesController.findPatientNoteByPatientName("John");

        // Assert
        assertThat(result).isEqualTo(mockNote);
        verify(patientNotesService, times(1)).findPatientNotesByPatientName("John");
    }

    @Test
    void testUpdatePatientNotesById() {
        // Arrange
        String note = "New Note";
        doNothing().when(patientNotesService).updatePatientNotesById("1", note);

        // Act
        patientNotesController.updatePatientNotesById("1", note);

        // Assert
        verify(patientNotesService, times(1)).updatePatientNotesById("1", note);
    }
}
