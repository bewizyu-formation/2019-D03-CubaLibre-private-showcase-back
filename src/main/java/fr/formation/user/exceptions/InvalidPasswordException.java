package fr.formation.user.exceptions;

public class InvalidPasswordException extends InvalidException {
    public InvalidPasswordException(){
        super("Le mot de passe doit contenir au moins 8 " +
                "caractères dont une minuscule, une majuscule et un chiffre");
    }
}
