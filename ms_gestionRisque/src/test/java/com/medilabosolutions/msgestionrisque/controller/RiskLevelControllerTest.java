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
        List<String> mockHistory = Arrays.asList("note1", "note2");
        String mockDob = "1990-01-01";
        String mockGender = "M";
        String mockRisk = "None";

        //Act
        when(riskLevelService.anticipateRisk(mockHistory, mockDob, mockGender)).thenReturn(mockRisk);

        //Assert
        mockMvc.perform(get("/risk")
                        .param("history", mockHistory.toArray(new String[0]))
                        .param("dateOfBirth", mockDob)
                        .param("gender", mockGender))
                .andExpect(status().isOk())
                .andExpect(content().string(mockRisk));
    }
}
