
package acme.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chirp {

	// Serialisation identifier

	protected static final long serialVersionUID = 1L;

	// Attributes
	
	@NotNull
	@Past
	protected LocalDate creationDate;
	
	@NotBlank
	@Length(max=100)
	protected String title;
	
	@NotBlank
	@Length(max=100)
	protected String author;
	
	@NotBlank
	@Length(max=255)
	protected String body;
	
	@Email
	protected String email;
	

}