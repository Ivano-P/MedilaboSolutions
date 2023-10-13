package com.medilabosolutions.msgestionpatient.exceptions;

/**
 * Exception class representing the scenario when a patient is not found in the system.
 * This exception extends the {@link RuntimeException} and provides custom error messages
 * to indicate the absence of a patient based on the provided criteria or information.
 *
 * @author Ivano
 */
public class PatientNotFoundExcetion extends RuntimeException{

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message which provides more information about the cause of the exception.
     */
    public PatientNotFoundExcetion(String message){
        super(message);
    }

    /**
     * Constructs a new exception with a default error message indicating that no patient was found.
     */
    public PatientNotFoundExcetion(){
        super("Aucun patient trouvé avec ces informations");
    }

}
