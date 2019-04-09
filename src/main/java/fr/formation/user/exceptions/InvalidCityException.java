package fr.formation.user.exceptions;

public class InvalidCityException extends InvalidException {
    public InvalidCityException(){
        super("La ville choisie n'existe pas");
    }
}
