package com.jpa.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Club { // equipo de futbol

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //anotacion jpa va generar la clave primaria por mi
	private Long id;
	private String name;
	
	@OneToOne(targetEntity = Coach.class, cascade = CascadeType.PERSIST) // RELACION DE UNO A UNO
	@JoinColumn(name = "id_couch") // personalizar el nombre de clave foranea
	private Coach coach;
	
    @OneToMany(targetEntity = Player.class, fetch = FetchType.LAZY, mappedBy = "club") // RELACION UNO A MUCCHOS
    private List<Player> players; // un equipo puede tener muchos jugadores
	
    @ManyToOne(targetEntity = FootballAssociation.class/*, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST*/) //RELATION MUCHOS A UNO
    private FootballAssociation footballAssociation; //clave foranea

    @ManyToMany(targetEntity = FootballCompetition.class, fetch = FetchType.LAZY)
    @JoinTable(name = "club_competition", joinColumns = @JoinColumn(name = "club"), inverseJoinColumns = @JoinColumn(name = "competition")) // MUCHOS A MUCHOS
    private List<FootballCompetition> footballCompetitions; 
    
}

//################ NOTAS ###################################

//****** @OneToMany, @OneToOne, @ManyToOne, etc ****** 
// One => UNO, 
// Many => MUCHOS

//****** FetchType ****** 
// LAZZY => OBTIENE SOLO UN JUGADOR DEFINIDO
// EAGE => OBTIENE TODA LA LISTA DE JUGADOR


//################## RELATION ###############################

//# RELACION UNO A UNO
// targetEntity: indico que Coach va a ser mi clave foranea
// CASCADE: comportamiento en cascada, ayuda a realizar dos insert, elimina, etc
           // PERSISTE: la unica forma que habra comportamiento en cascada al insertar el coach


//# RELACION UNO A MUCHOS
//un club/equipo puede tener muchos jugadores // clase player


//# RELACION MUCHOS A UNO
// muchos equipos para una sola confederacion de futbol


//# RELACION MUCHOS A MUCHOS
// muchos clubes para muchas asociaciones






