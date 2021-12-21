package dk.gustav.server.repositories;

import dk.gustav.server.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate,Long> {

    Iterable<Candidate> findCandidatesByPoliticalPartyId(Long id);
}
