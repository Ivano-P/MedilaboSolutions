package com.medilabosolutions.msgestionpatient.service;

import com.medilabosolutions.msgestionpatient.beans.PatientBean;
import com.medilabosolutions.msgestionpatient.dto.PatientForSelectionDTO;
import com.medilabosolutions.msgestionpatient.exceptions.PatientNotFoundExcetion;
import com.medilabosolutions.msgestionpatient.implimentations.PatientServiceImpl;
import com.medilabosolutions.msgestionpatient.model.Genre;
import com.medilabosolutions.msgestionpatient.model.Patient;
import com.medilabosolutions.msgestionpatient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    void testFindAllPatients() {
        // Arrange
        Patient mockPatient = new Patient();
        List<Patient> mockList = List.of(mockPatient);
        when(patientRepository.findAll()).thenReturn(mockList);

        // Act
        List<Patient> result = patientService.findAllPatients();

        // Assert
        assertEquals(mockList, result);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testConvertPatientsToPatientsDTO() {
        // Arrange
        Patient mockPatient = new Patient("1", "John", "Doe", "1990-01-01", Genre.M, "123 St", "1234567890");
        List<Patient> mockList = List.of(mockPatient);

        // Act
        List<PatientForSelectionDTO> result = patientService.convertPatientsToPatientsDTO(mockList);

        // Assert
        assertEquals(1, result.size());
        assertEquals(mockPatient.getId(), result.get(0).getId());
        assertEquals(mockPatient.getPrenom(), result.get(0).getPrenom());
        assertEquals(mockPatient.getNom(), result.get(0).getNom());
        assertEquals(mockPatient.getDateDeNaissance(), result.get(0).getDateDeNaissance());
    }

    @Test
    void testFindPatientById() {
        // Arrange
        Patient mockPatient = new Patient("1", "John", "Doe", "1990-01-01", Genre.M, "123 St", "1234567890");
        when(patientRepository.findById("1")).thenReturn(Optional.of(mockPatient));

        // Act
        Patient result = patientService.findPatientById("1");

        // Assert
        assertEquals(mockPatient, result);
        verify(patientRepository, times(1)).findById("1");
    }

    @Test
    void testFindPatientByIdNotFound() {
        // Arrange
        when(patientRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PatientNotFoundExcetion.class, () -> patientService.findPatientById("1"));
        verify(patientRepository, times(1)).findById("1");
    }

    @Test
    void testUpdatePatient() {
        // Arrange
        PatientBean mockPatientBean = new PatientBean("1", "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        Patient mockPatient = new Patient("1", "John", "Doe", "1990-01-01", Genre.M, "123 St", "1234567890");
        when(patientRepository.findById("1")).thenReturn(Optional.of(mockPatient));

        // Act
        patientService.updatePatient(mockPatientBean);

        // Assert
        verify(patientRepository, times(1)).save(mockPatient);
    }

    @Test
    void testAddPatient() {
        // Arrange
        PatientBean mockPatientBean = new PatientBean("1", "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
        Patient mockPatient = new Patient("1", "John", "Doe", "1990-01-01", Genre.M, "123 St", "1234567890");

        // Act
        patientService.addPatient(mockPatientBean);

        // Assert
        verify(patientRepository, times(1)).insert(any(Patient.class));
    }
}
