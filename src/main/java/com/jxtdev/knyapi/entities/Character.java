package com.jxtdev.knyapi.entities;

import java.util.List;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private double height;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "typeCharacter_id", nullable = false)
    private TypeCharacter type;

    @ManyToOne
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @ManyToMany
    @JoinTable(name = "character_power")
    private List<Power> powers;
}
