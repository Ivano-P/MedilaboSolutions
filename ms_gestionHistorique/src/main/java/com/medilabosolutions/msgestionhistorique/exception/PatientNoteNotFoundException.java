package com.medilabosolutions.msgestionhistorique.exception;

public class PatientNoteNotFoundException extends RuntimeException{

    public PatientNoteNotFoundException(String message){
        super(message);
    }

    public PatientNoteNotFoundException(){
        super("Aucun historique trouvé pour ce patient");
    }

}
