package dk.gustav.server.controllers;

import dk.gustav.server.DTO.response.CandidateCreateDTO;
import dk.gustav.server.exception.ResourceNotFoundException;
import dk.gustav.server.models.Candidate;
import dk.gustav.server.models.PoliticalParty;
import dk.gustav.server.repositories.CandidateRepo;
import dk.gustav.server.repositories.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidatesController {
    @Autowired
    CandidateRepo candidates;

    @Autowired
    PoliticalPartyRepo politicalParties;

    @GetMapping("/candidates")
    public Iterable<Candidate> getCandidates() {
        return candidates.findAll();
    }

    @GetMapping("/candidates/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidates.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object by "+ id + " does not exist"));
    }

    @GetMapping("/candidates/parties/{id}")
    public Iterable<Candidate> getCandidatesFromParty(@PathVariable Long id) {
        return candidates.findCandidatesByPoliticalPartyId(id);
    }

    @PostMapping("/candidates/{politicalPartyId}")
    public CandidateCreateDTO addCandidate(@PathVariable Long politicalPartyId, @RequestBody Candidate newCandidate)  {
        return politicalParties.findById(politicalPartyId).map(party -> {
            newCandidate.setId(null);
            newCandidate.setPoliticalParty(party);
            Candidate addedCandidate = candidates.save(newCandidate);
            return new CandidateCreateDTO(addedCandidate);
        })
                .orElse(new CandidateCreateDTO("No Political Party found by id: " + politicalPartyId));
    }

    @PutMapping("/candidates/{id}")
    public String updateCandidate(@PathVariable Long id, @RequestBody Candidate updateCandidate) {
        if (candidates.existsById(id)) {
            updateCandidate.setId(id);
            candidates.save(updateCandidate);
            return "Candidate was updated";
        } else {
            return "Candidate not created";
        }
    }
    @PatchMapping("/candidates/{candidateId}/parties/{politicalPartyId}")
    public CandidateCreateDTO patchCandidate(@PathVariable Long candidateId, @PathVariable Long politicalPartyId , @RequestBody Candidate patchedCandidate) {
        PoliticalParty chosenParty = politicalParties.findById(politicalPartyId).get();

        return candidates.findById(candidateId).map(candidateFound -> {
            if (patchedCandidate.getFirstName() != null) candidateFound.setFirstName(patchedCandidate.getFirstName());
            if (patchedCandidate.getLastName() != null) candidateFound.setLastName(patchedCandidate.getLastName());
            if (patchedCandidate.getPhoneNumber() != 0) candidateFound.setPhoneNumber(patchedCandidate.getPhoneNumber());
            if (patchedCandidate.getEmail() != null) candidateFound.setEmail(patchedCandidate.getEmail());
            if (!chosenParty.getId().equals(candidateFound.getPoliticalParty().getId())) candidateFound.setPoliticalParty(chosenParty);
            Candidate savedCandidate = candidates.save(candidateFound);
            return new CandidateCreateDTO(savedCandidate);
        }).orElse(new CandidateCreateDTO("Candidate was not updated"));
    }

    @DeleteMapping("/candidates/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidates.deleteById(id);
    }
}

