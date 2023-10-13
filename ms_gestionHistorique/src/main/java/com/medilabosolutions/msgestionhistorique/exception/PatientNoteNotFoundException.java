package com.medilabosolutions.msgestionhistorique.exception;

/**
 * Custom exception to indicate that a patient's note was not found.
 * This exception is thrown when an operation attempts to access a patient's note
 * that doesn't exist or is not available.
 *
 * @author Ivano
 */
public class PatientNoteNotFoundException extends RuntimeException{

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public PatientNoteNotFoundException(String message){
        super(message);
    }

    /**
     * Constructs a new exception with a default detail message.
     * The default message indicates that no history was found for the patient.
     */
    public PatientNoteNotFoundException(){
        super("Aucun historique trouvé pour ce patient");
    }

}
