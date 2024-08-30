package com.example.ToDoList2.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
        /*
        Coerenza: Tutti gli errori sono gestiti in un modo uniforme.
        Chiarezza: I messaggi di errore sono chiari e facili da capire per chi usa l'API.
        Facilità di Debug: Gli sviluppatori possono identificare e risolvere i problemi più rapidamente grazie ai messaggi di errore dettagliati.
         */
    }
}
