package com.medilabosolutions.msgestionrisque.controller;

import com.medilabosolutions.msgestionrisque.service.RiskLevelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class RiskLevelControllerTest {

    @InjectMocks
    private RiskLevelController riskLevelController;

    @Mock
    private RiskLevelService riskLevelService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(riskLevelController).build();
    }
    @Test
    void testEvaluateRiskLevel() throws Exception {
        //Arrange
        int patientId = 1;
        String mockRisk = "None";

        //Act
        when(riskLevelService.getRiskLevel(patientId)).thenReturn(mockRisk);

        //Assert
        mockMvc.perform(get("/evaluate")
                        .param("patientId", String.valueOf(patientId)))
                .andExpect(status().isOk());

    }
}
