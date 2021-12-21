package dk.gustav.server.repositories;

import dk.gustav.server.models.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepo extends JpaRepository<PoliticalParty, Long> {

}
