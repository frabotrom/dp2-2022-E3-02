package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity {
	
	// Serialisation identifier --------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes ----------------------------------------------------
	
	@Column(unique = true)
	@NotNull
	//@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	// pattern “PATTERN”, where “yy”, “mm”, and “dd” refer to the year, month, and day when the CHIMPUM is created
	protected String code;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationMoment;
	
	@NotBlank
	@Length(max=100)
	protected String title;
	
	@NotBlank
	@Length(max=255)
	protected String description;
	
	// Para calcular el periodo
	// Se hace comprobación en el service: at least one month ahead and one week long
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date initialDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalDate;
	
	// Se hace comprobación en el service: Positive
	@NotNull
	protected Money budget;
	
	@URL
	protected String optionalLink;
	
	// Derived attributes ----------------------------------------------------
	
	@Transient
	public Integer period() {
		final long result = this.finalDate.getTime() - this.initialDate.getTime(); 
		final long resultDays = result/(1000*60*60*24);
		return (int) resultDays;
	}

}
