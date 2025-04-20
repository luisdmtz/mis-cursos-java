package com.jpa.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Player { //jugador

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    private String nationality;
    private String position;
    
    @ManyToOne(targetEntity = Club.class) // MUCHOS A UNO, RELACION CON CLUB
    @JoinColumn(name = "id_club")
    private Club club; // clave foranea un jugador pertenece a un equipo
    
}


// un jugador puede jugar en un equipo
