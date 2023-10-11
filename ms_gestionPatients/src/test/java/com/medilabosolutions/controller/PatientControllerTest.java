package com.medilabosolutions.controller;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.controller.PatientController;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.model.Genre;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    @Test
    void testListOfAllPatients() {
        // Arrange

        PatientForSelectionDTO mockDTO = new PatientForSelectionDTO("1", "John", "Doe", "1990-01-01");
        Patient mockPatient = new Patient("1", "John", "Doe", "1990-01-01", Genre.M, null, null);
        List<PatientForSelectionDTO> mockDTOList = List.of(mockDTO);
        List<Patient> mockePatientList = List.of(mockPatient);
        when(patientService.findAllPatients()).thenReturn(mockePatientList);
        when(patientService.convertPatientsToPatientsDTO(mockePatientList)).thenReturn(mockDTOList);

        // Act
        List<PatientForSelectionDTO> result = patientController.listOfAllPatients();

        // Assert
        assertEquals(mockDTOList, result);
        verify(patientService, times(1)).findAllPatients();
        verify(patientService, times(1)).convertPatientsToPatientsDTO(mockePatientList);
    }

    @Test
    void testGetUpdatePage() {
        // Arrange
        Patient mockPatient = new Patient();
        mockPatient.setId("1");
        when(patientService.findPatientById("1")).thenReturn(mockPatient);

        // Act
        Patient result = patientController.getUpdatePage("1");

        // Assert
        assertEquals(mockPatient, result);
        verify(patientService, times(1)).findPatientById("1");
    }

    @Test
    void testUpdatePatient() {
        // Arrange
        PatientBean mockPatientBean = new PatientBean("1", "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        doNothing().when(patientService).updatePatient(mockPatientBean);

        // Act
        patientController.updatePatient(mockPatientBean);

        // Assert
        verify(patientService, times(1)).updatePatient(mockPatientBean);
    }

    @Test
    void testAddPatient() {
        // Arrange
        PatientBean mockPatientBean = new PatientBean("1", "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        doNothing().when(patientService).addPatient(mockPatientBean);

        // Act
        patientController.addPatient(mockPatientBean);

        // Assert
        verify(patientService, times(1)).addPatient(mockPatientBean);
    }

}
