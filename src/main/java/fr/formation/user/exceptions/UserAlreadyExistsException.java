package fr.formation.user.exceptions;

public class UserAlreadyExistsException extends InvalidException{
    public UserAlreadyExistsException(){
        super("Le login saisi existe déjà");
    }
}
