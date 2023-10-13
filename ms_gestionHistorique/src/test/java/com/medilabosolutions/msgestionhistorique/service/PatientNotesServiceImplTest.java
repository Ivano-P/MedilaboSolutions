package com.medilabosolutions.msgestionhistorique.service;

import com.medilabosolutions.msgestionhistorique.exception.PatientNoteNotFoundException;
import com.medilabosolutions.msgestionhistorique.implimentations.PatientNotesServiceImpl;
import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.repository.PatientNotesRepositoy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientNotesServiceImplTest {

    @InjectMocks
    PatientNotesServiceImpl patientNotesService;

    @Mock
    private PatientNotesRepositoy patientNotesRepositoy;

    @Test
    void testFindAllPatientNotes() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        List<PatientNotes> mockList = List.of(mockNote);
        when(patientNotesRepositoy.findAll()).thenReturn(mockList);

        // Act
        List<PatientNotes> result = patientNotesService.findAllPatientNotes();

        // Assert
        assertThat(result).isEqualTo(mockList);
        verify(patientNotesRepositoy, times(1)).findAll();
    }

    @Test
    void testFindPatientNotesById() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesRepositoy.findById("1")).thenReturn(Optional.of(mockNote));

        // Act
        PatientNotes result = patientNotesService.findPatientNotesById("1");

        // Assert
        assertThat(result).isEqualTo(mockNote);
        verify(patientNotesRepositoy, times(1)).findById("1");
    }

    @Test
    void testFindPatientNotesByIdNotFound() {
        // Arrange
        when(patientNotesRepositoy.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(PatientNoteNotFoundException.class)
                .isThrownBy(() -> patientNotesService.findPatientNotesById("1"));
        verify(patientNotesRepositoy, times(1)).findById("1");
    }

    @Test
    void testFindPatientNotesByPatientName() {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesRepositoy.findByPatient("John")).thenReturn(Optional.of(mockNote));

        // Act
        PatientNotes result = patientNotesService.findPatientNotesByPatientName("John");

        // Assert
        assertThat(result).isEqualTo(mockNote);
        verify(patientNotesRepositoy, times(1)).findByPatient("John");
    }

    @Test
    void testFindPatientNotesByPatientNameNotFound() {
        // Arrange
        when(patientNotesRepositoy.findByPatient("John")).thenReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(PatientNoteNotFoundException.class)
                .isThrownBy(() -> patientNotesService.findPatientNotesByPatientName("John"));
        verify(patientNotesRepositoy, times(1)).findByPatient("John");
    }


}
