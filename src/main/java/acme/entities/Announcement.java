
package acme.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement {

	// Serialisation identifier

	protected static final long	serialVersionUID	= 1L;

	// Attributes

	@NotNull
	@Past
	protected LocalDate			creationDate;

	@NotBlank
	@Length(max = 100)
	protected String			title;

	@NotBlank
	@Length(max = 255)
	protected String			body;

	@NotNull
	protected Boolean			critical;

	@URL
	protected String			moreInfo;

}
