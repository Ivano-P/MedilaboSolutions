package com.medilabosolutions.ms_gestionrisque.service;

import com.medilabosolutions.ms_gestionrisque.implimentations.RiskLevelServiceImpl;
import com.medilabosolutions.ms_gestionrisque.model.RiskLevel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskLevelServiceImplTest {
    private final List<String> triggerTerms = Arrays.asList("term1", "term2", "term3");
    RiskLevelServiceImpl riskLevelService = new RiskLevelServiceImpl(null, triggerTerms);


    @Test
    void testAnticipateRiskMaleOverThirty() {
        //Arrange
        List<String> personHistory = Arrays.asList("This is term1", "This is term2", "This is term3");
        String dateOfBirth = "1990-01-15";
        String gender = "M";
        //Act
        String result = riskLevelService.anticipateRisk(personHistory, dateOfBirth, gender);
        //Assert
        assertEquals(RiskLevel.BORDERLINE.getDescription(), result);
    }

    @Test
    void testAnticipateRiskMaleUnderThirthy() {
        //Arrange
        List<String> personHistory = Arrays.asList("This is term1", "This is term2", "This is term3");
        String dateOfBirth = "2000-01-15";
        String gender = "M";
        //Act
        String result = riskLevelService.anticipateRisk(personHistory, dateOfBirth, gender);
        //Assert
        assertEquals(RiskLevel.IN_DANGER.getDescription(), result);
    }

    @Test
    void testConvertDobStringToLocalDate() {
        //Arrange
        String dateOfBirth = "1990-01-01";
        //Act
        LocalDate result = riskLevelService.convertDobStringToLocalDate(dateOfBirth);
        //Assert
        assertEquals(LocalDate.of(1990, 1, 1), result);
    }

    @Test
    void testCalculateAge() {
        //Arrange
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        //Act
        int age = riskLevelService.calculateAge(dateOfBirth);
        //Assert
        assertEquals(LocalDate.now().getYear() - 1990, age);
    }

    @Test
    void testCountTermsOccurrences() {
        //Arrange
        List<String> personHistory = Arrays.asList("This is term1", "This is term2", "This is term3");
        //Act
        int count = riskLevelService.countTermsOccurrences(triggerTerms, personHistory);
        //Assert
        assertEquals(3, count);
    }

}
