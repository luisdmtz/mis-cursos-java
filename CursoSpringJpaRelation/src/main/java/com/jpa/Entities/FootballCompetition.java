package com.jpa.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FootballCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", columnDefinition = "VARCHAR(200)") // SE PUEDE ASIGNAR EL TIPO DE DATO EJEMPLO: VARCHAR, NULLABLE
    private String name;

    @Column(name = "cuantity_price" /*, length = 10, nullable = false, unique = true */) 
    private int cuantityPrice;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

//    @ManyToMany(targetEntity = Club.class, fetch = FetchType.LAZY)
//    private List<Club> clubs;
}
