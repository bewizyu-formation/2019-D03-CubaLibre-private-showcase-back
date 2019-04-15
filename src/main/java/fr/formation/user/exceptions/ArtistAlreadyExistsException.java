package fr.formation.user.exceptions;

public class ArtistAlreadyExistsException extends InvalidException {
    public ArtistAlreadyExistsException(){
        super("Le nom d'artiste saisi existe déjà");
    }
}
