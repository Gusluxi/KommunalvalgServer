package dk.gustav.server.DTO.response;

import dk.gustav.server.models.Candidate;

public class CandidateCreateDTO {

    public Candidate candidate;
    public boolean failed;
    public String errorMessage;

    public CandidateCreateDTO(Candidate candidate) {
        this.candidate = candidate;
    }

    public CandidateCreateDTO(String errorMessage) {
        this.errorMessage = errorMessage;
        this.failed = true;
    }
}
