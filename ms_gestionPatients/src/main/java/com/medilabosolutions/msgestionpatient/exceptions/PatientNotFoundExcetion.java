package com.medilabosolutions.msgestionpatient.exceptions;

public class PatientNotFoundExcetion extends RuntimeException{

    public PatientNotFoundExcetion(String message){
        super(message);
    }

    public PatientNotFoundExcetion(){
        super("Aucun patient trouvé avec ces informations");
    }

}
