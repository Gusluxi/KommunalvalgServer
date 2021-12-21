package dk.gustav.server.models;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name="candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int phoneNumber;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name="political_party_id")
    @Nullable
    private PoliticalParty politicalParty;
}
