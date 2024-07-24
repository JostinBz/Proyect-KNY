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

    @ManyToMany(targetEntity = Power.class, fetch = FetchType.LAZY)
    @JoinTable(name = "inspiredcharacter_power",
    joinColumns = @JoinColumn(name = "inspiredcharacter_id"),
    inverseJoinColumns = @JoinColumn(name = "power_id"))
    private List<Power> powers;
}
