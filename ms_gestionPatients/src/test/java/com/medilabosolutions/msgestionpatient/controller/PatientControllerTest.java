package com.medilabosolutions.msgestionpatient.controller;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void testListOfAllPatients() throws Exception {
        // Arrange
        PatientForSelectionDTO mockDTO = new PatientForSelectionDTO(1, "John", "Doe", "1990-01-01");
        List<PatientForSelectionDTO> mockDTOList = List.of(mockDTO);
        when(patientService.convertPatientsToPatientsDTO(anyList())).thenReturn(mockDTOList);

        // Act & Assert
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).findAllPatients();
    }

    @Test
    void getPatientInfo() throws Exception {
        // Arrange
        Patient mockPatient = new Patient(1, "John", "Doe", "1990-01-01", null, null, null);
        when(patientService.findPatientById(1)).thenReturn(mockPatient);

        // Act & Assert
        mockMvc.perform(get("/info").param("patientId", "1"))
                .andExpect(status().isOk());
        verify(patientService, times(1)).findPatientById(1);
    }

    @Test
    void testUpdatePatient() {
        // Arrange
        PatientBean mockPatientBean = new PatientBean(1, "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        doNothing().when(patientService).updatePatient(mockPatientBean);

        // Act
        patientController.updatePatient(mockPatientBean);

        // Assert
        verify(patientService, times(1)).updatePatient(mockPatientBean);
    }

    @Test
    void testAddPatient() throws NoSuchFieldException {
        // Arrange
        PatientBean mockPatientBean = new PatientBean(1, "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        doNothing().when(patientService).addPatient(mockPatientBean);

        // Act
        patientController.addPatient(mockPatientBean);

        // Assert
        verify(patientService, times(1)).addPatient(mockPatientBean);
    }

}
