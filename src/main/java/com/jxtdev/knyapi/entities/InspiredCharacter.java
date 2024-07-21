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
@Table(name = "inspired_characters")
public class InspiredCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private double height;
    private String description;
    private String imageUrl;

    @OneToOne(targetEntity = TypeCharacter.class)
    @JoinColumn(name = "typeCharacter_id", nullable = false)
    private TypeCharacter type;

    @OneToOne(targetEntity = Rank.class)
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @OneToMany(targetEntity = Power.class)
    @JoinColumn(name = "power_id", nullable = false)
    private List<Power> powers;
}
