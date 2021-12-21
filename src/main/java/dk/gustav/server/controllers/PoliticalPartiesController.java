package dk.gustav.server.controllers;

import dk.gustav.server.exception.ResourceNotFoundException;
import dk.gustav.server.models.PoliticalParty;
import dk.gustav.server.repositories.PoliticalPartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PoliticalPartiesController {

    @Autowired
    PoliticalPartyRepo politicalParties;

    @GetMapping("/parties")
    public Iterable<PoliticalParty> getParties() {
        return politicalParties.findAll();
    }

    @GetMapping("/parties/{id}")
    public PoliticalParty getParty(@PathVariable Long id) {
        return politicalParties.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object by "+ id + " does not exist"));
    }

    @PostMapping("/parties")
    public PoliticalParty addParty(@RequestBody PoliticalParty newPoliticalParty)  {
        newPoliticalParty.setId(null);
        return politicalParties.save(newPoliticalParty);
    }

    @PutMapping("/parties/{id}")
    public String updateParty(@PathVariable Long id, @RequestBody PoliticalParty updatedObjectOne) {
        if (politicalParties.existsById(id)) {
            updatedObjectOne.setId(id);
            politicalParties.save(updatedObjectOne);
            return "Party was updated";
        } else {
            return "Party not created";
        }
    }

    @PatchMapping("/parties/{id}")
    public String patchPoliticalParty(@PathVariable Long id, @RequestBody PoliticalParty patchedParty) {
        return politicalParties.findById(id).map(foundParty -> {
            if (patchedParty.getPartyName() != null) foundParty.setPartyName(patchedParty.getPartyName());
            if (patchedParty.getWebAddress() != null) foundParty.setWebAddress(patchedParty.getWebAddress());

            politicalParties.save(foundParty);
            return "Party updated";
        }).orElse("Party not found");
    }


    @DeleteMapping("/parties/{id}")
    public void deletePoliticalParty(@PathVariable Long id) {
        politicalParties.deleteById(id);
    }
}
