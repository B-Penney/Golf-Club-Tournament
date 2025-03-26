package org.golfclub.golfclubtournament.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phoneNum;
    private LocalDate startDate;
    private int duration;

    @ManyToMany(mappedBy = "members")
    private Set<Tournament> tournaments = new HashSet<>();

    public Member() {}

    public Member(String name, String address, String email, String phoneNum, LocalDate startDate, int duration) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
        this.startDate = startDate;
        this.duration = duration;
    }
}

