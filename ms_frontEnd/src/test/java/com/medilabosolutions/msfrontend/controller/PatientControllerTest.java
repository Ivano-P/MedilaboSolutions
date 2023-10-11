package com.medilabosolutions.msfrontend.controller;

import com.medilabosolutions.msfrontend.beans.PatientBean;
import com.medilabosolutions.msfrontend.beans.PatientForSelectionBean;
import com.medilabosolutions.msfrontend.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    @Mock
    Model model;


    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void testGoToHomePage() throws Exception {
        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void testListPatients() throws Exception{
        //Arrange
        PatientForSelectionBean simpleMockPatient1 = new PatientForSelectionBean("1",
                "mock", "patient1", "1901-01-01");
        PatientForSelectionBean simpleMockPatient2 = new PatientForSelectionBean("2",
                "mock", "patient2", "1902-02-02");

        List<PatientForSelectionBean> simpleMockPatientsList = List.of(simpleMockPatient1, simpleMockPatient2);

        when(patientService.findAllPatients()).thenReturn(simpleMockPatientsList);


        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("patientsList"))
                .andExpect(model().attribute("patients", simpleMockPatientsList));

        verify(patientService, times(1)).findAllPatients();
    }

    @Test
    void testPatientInformation() throws Exception{
        //Arrange
        PatientBean mockPatient1 = new PatientBean("1",
                "mock", "patient1", "1901-01-01", "F", null,
                null);

        when(patientService.findPatientById(mockPatient1.getId())).thenReturn(mockPatient1);

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/informations")
                .param("patientId", mockPatient1.getId()))
                .andExpect(status().isOk()).andExpect(view().name("patientInfo"))
                .andExpect(model().attribute("patient", mockPatient1));

        verify(patientService, times(1)).findPatientById(anyString());
    }

    @Test
    void testGoToUpdatePatientInfoPage() throws Exception{
        //Arrange
        PatientBean mockPatient1 = new PatientBean("1",
                "mock", "patient1", "1901-01-01", "F", null,
                null);

        when(patientService.findPatientById(mockPatient1.getId())).thenReturn(mockPatient1);

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/updateInfo")
                        .param("patientId", mockPatient1.getId()))
                .andExpect(status().isOk()).andExpect(view().name("updatePatientInfo"))
                .andExpect(model().attribute("patient", mockPatient1));
        verify(patientService, times(1)).findPatientById(anyString());
    }

    @Test
    void testUpdatePatientInfo() throws Exception {
        // Arrange
        PatientBean mockPatient = new PatientBean("1", "mock", "patient1", "1901-01-01", "F", null, null);

        // Assuming no validation errors
        doNothing().when(patientService).updatePatient(any(PatientBean.class));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/updatePatient")
                        .flashAttr("patientBean", mockPatient)) // This will bind the mockPatient object to the form
                .andExpect(status().is3xxRedirection())  // Expecting a redirect status
                .andExpect(redirectedUrl("/informations?patientId=" + mockPatient.getId()));  // Expecting a redirect to the patient's information page

        verify(patientService, times(1)).updatePatient(ArgumentMatchers.any(PatientBean.class));
    }

    @Test
    void testGoToAddPatientPage() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/addPatient"))
                .andExpect(status().isOk())  // Expecting an OK status
                .andExpect(view().name("addPatientInfo"))  // Expecting the view name to be "addPatientInfo"
                .andExpect(model().attributeExists("patientBean"));  // Expecting the model to have an attribute named "patientBean"
    }

    @Test
    void testAddPatient() throws Exception {
        // Arrange
        PatientBean mockPatient = new PatientBean("1", "mock", "patient1", "1901-01-01", "F", null, null);

        // Assuming no validation errors
        doNothing().when(patientService).addPatient(any(PatientBean.class));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/addPatient")
                        .flashAttr("patientBean", mockPatient)) // This will bind the mockPatient object to the form
                .andExpect(status().is3xxRedirection())  // Expecting a redirect status
                .andExpect(redirectedUrl("/patients"));  // Expecting a redirect to the patients list page

        verify(patientService, times(1)).addPatient(ArgumentMatchers.any(PatientBean.class));
    }



}
