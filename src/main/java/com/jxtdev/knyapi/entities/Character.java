package com.jxtdev.knyapi.entities;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String height;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "typeCharacter_id", nullable = false)
    private TypeCharacter type;

    @ManyToOne
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @ManyToMany(targetEntity = Power.class, fetch = FetchType.LAZY)
    @JoinTable(name = "character_power",
    joinColumns = @JoinColumn(name = "character_id"),
    inverseJoinColumns = @JoinColumn(name = "power_id"))
    private List<Power> powers;
}
