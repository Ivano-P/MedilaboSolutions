package com.medilabosolutions.msgestionhistorique.controller;

import com.medilabosolutions.msgestionhistorique.model.PatientNotes;
import com.medilabosolutions.msgestionhistorique.service.PatientNotesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PatientNotesControllerTest {

    @InjectMocks
    PatientNotesController patientNotesController;

    @Mock
    PatientNotesService patientNotesService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientNotesController).build();
    }

    @Test
    void testGetAllPatientNotes() throws Exception {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        List<PatientNotes> mockNotesList = List.of(mockNote);
        when(patientNotesService.findAllPatientNotes()).thenReturn(mockNotesList);

        // Act & Assert
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());

        verify(patientNotesService, times(1)).findAllPatientNotes();
    }

    @Test
    void testFindPatientNoteById() throws Exception {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesService.findPatientNotesById("1")).thenReturn(mockNote);

        // Act & Assert
        mockMvc.perform(get("/noteById")
                .param("patientId", mockNote.getId()))
                .andExpect(status().isOk());

        verify(patientNotesService, times(1)).findPatientNotesById("1");
    }

    @Test
    void testFindPatientNoteByPatientName() throws Exception {
        // Arrange
        PatientNotes mockNote = new PatientNotes("1", "John", List.of("Note1", "Note2"));
        when(patientNotesService.findPatientNotesByPatientName("John")).thenReturn(mockNote);

        // Act & Assert
        mockMvc.perform(get("/noteByName")
                        .param("patientName", mockNote.getPatient()))
                        .andExpect(status().isOk());

        verify(patientNotesService, times(1)).findPatientNotesByPatientName("John");
    }

    @Test
    void testUpdatePatientNotesById() throws Exception {
        // Arrange
        String patientId = "1";
        String note = "New Note";
        doNothing().when(patientNotesService).updatePatientNotesById(patientId, note);

        // Act & Assert
        mockMvc.perform(post("/updateNotes")
                        .param("patientId", patientId)
                        .param("note", note))
                .andExpect(status().isOk());

        verify(patientNotesService, times(1)).updatePatientNotesById(patientId, note);
    }
}
