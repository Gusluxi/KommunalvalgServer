package dk.gustav.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "political_parties")
public class PoliticalParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String partyName;

    @Column
    private String abbreviation;

    @Column
    private String webAddress;

    @Column
    private String partyImage;

    @Column
    private int partyVotes;

    @JsonIgnore
    @OneToMany(mappedBy = "politicalParty", fetch = FetchType.LAZY)
    private Set<Candidate> candidates;
}
