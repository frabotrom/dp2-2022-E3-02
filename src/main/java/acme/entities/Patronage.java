package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	// Serialisation identifier

	protected static final long		serialVersionUID	= 1L;

	// Attributes
	
	@NotNull
	protected PatronageStatus		status;

	@NotBlank
	@NotNull
	@Length(max=255)
	protected String				legalStuff;
	
	@Column(unique = true)
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String 				code;


	@NotNull
	protected Money					budget;
	
	@URL
	protected String				optionalLink;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date  				creationDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date  				initialDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date  				finalDate;

	// Derived attributes
	
	@NotNull
	@Transient
	public Integer periodOfTime() {
		final long result = this.finalDate.getTime() - this.initialDate.getTime(); 
		final long resultDays = result/(1000*60*60*24);
		return (int) resultDays;
	}
	
	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patron patron;

}