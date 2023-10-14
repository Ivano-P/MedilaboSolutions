package com.medilabosolutions.msfrontend.service;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.implementations.PatientServiceImpl;
import com.medilabosolutions.msfrontend.proxies.MsGatewayProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    MsGatewayProxy msGestionPatientProxy;

    private PatientBean mockPatient;

    @BeforeEach
    void setup(){
        mockPatient = new PatientBean(1, "John", "Doe", "1990-01-01", "M", "123 St", "1234567890");
    }

    @Test
    void testFindAllPatients() {
        // Arrange
        PatientForSelectionBean mockSimplifiedPatient = new PatientForSelectionBean(1, "John", "Doe", "1990-01-01");
        List<PatientForSelectionBean> mockList = List.of(mockSimplifiedPatient);
        when(msGestionPatientProxy.listOfPatients()).thenReturn(mockList);

        // Act
        List<PatientForSelectionBean> result = patientService.findAllPatients();

        // Assert
        assertEquals(mockList, result);
        verify(msGestionPatientProxy, times(1)).listOfPatients();
    }

    @Test
    void testFindPatientById() {
        // Arrange
        when(msGestionPatientProxy.getPatientInfo(1)).thenReturn(mockPatient);

        // Act
        PatientBean result = patientService.findPatientById(1);

        // Assert
        assertEquals(mockPatient, result);
        verify(msGestionPatientProxy, times(1)).getPatientInfo(1);
    }

    @Test
    void testAddPatient() {
        // Arrange
        doNothing().when(msGestionPatientProxy).addPatient(mockPatient);

        // Act
        patientService.addPatient(mockPatient);

        // Assert
        verify(msGestionPatientProxy, times(1)).addPatient(mockPatient);
    }

    @Test
    void testUpdatePatient() {
        // Arrange
        doNothing().when(msGestionPatientProxy).updatePatient(mockPatient);

        // Act
        patientService.updatePatient(mockPatient);

        // Assert
        verify(msGestionPatientProxy, times(1)).updatePatient(mockPatient);
    }


}
