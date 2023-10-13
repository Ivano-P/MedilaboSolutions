package com.medilabosolutions.ms_gestionrisque.util;

import java.util.Arrays;
import java.util.List;

public class TriggerTermsDefaultUtil {
    private static final List<String> TERMS = Arrays.asList(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Anormal",
            "Cholestérol",
            "Vertiges",
            "Rechute",
            "Réaction",
            "Anticorps"
    );

    private TriggerTermsDefaultUtil() {
        // Private constructor to prevent instantiation
    }

    public static List<String> getTerms() {
        return TERMS;
    }
}
