package fr.formation.departement_accepted;

import fr.formation.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Artist service.
 */
@Service
public class DepartementAcceptedService {

    private DepartementAcceptedRepository departementAcceptedRepository;

    /**
     * Instantiates a new DepartementAccepted service.
     *
     * @param departementAcceptedRepository the artist repository
     */

    @Autowired
    public DepartementAcceptedService(DepartementAcceptedRepository departementAcceptedRepository) {
        this.departementAcceptedRepository = departementAcceptedRepository;
    }

    public List<Artist> getArtistByDepartement(int code){
        List<DepartementAccepted> listDepartementAccepted = departementAcceptedRepository.findByCode(code);
        List<Artist> artistIds = listDepartementAccepted
                .stream()
                .map(da -> { return da.getArtist();})
                .collect(Collectors.toList());

        return artistIds;
    }

    public void addNewDepartementAcceptedService (int code, Artist artist){
        DepartementAccepted departementAccepted = new DepartementAccepted();
        departementAccepted.setArtist(artist);
        departementAccepted.setCode(code);

        departementAcceptedRepository.save(departementAccepted);
    }
}
