package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT a FROM Film a")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Column(name="title", nullable=false, length=45)
	private String title;

	@Column(name="description", nullable=false, length=45)
	private String decription;
	
	@Column(name="release_year", nullable=false)
	private int releaseYear;

	@Column(name="language_id", nullable=false)
	private int languageId;
	
	@Column(name="original_language_id", nullable=true)
	private int originalLanguageId;
	
	@Column(name="rental_duration", nullable=false)
	private int rentalDuration;
	
	@Column(name="rental_rate", nullable=false)
	private float rentalRate;
	
	@Column(name="length", nullable=false)
	private int length;
	
	@Column(name="replacement_cost", nullable=false)
	private float replacementCost;
	
	@Column(name="rating", nullable=false, length=20)
	private String rating;
	
	@Column(name="special_features", nullable=false, length=45)
	private String specialFeatures;
	
	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;



}