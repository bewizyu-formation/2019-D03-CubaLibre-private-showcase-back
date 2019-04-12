package fr.formation.geo.services;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Departement service.
 */
public interface DepartementService {

    /**
     * Gets departement.
     *
     * @param nom the nom
     * @return the departement
     */
    List<LinkedHashMap> getDepartements(final String nom) throws UnsupportedEncodingException;


    /**
     * Gets departement by code.
     *
     * @param code the code
     * @return the departement by code
     */
    List<LinkedHashMap> getDepartementByCode(final String code) throws UnsupportedEncodingException;


}
