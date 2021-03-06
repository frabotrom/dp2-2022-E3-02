
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.components.SpamDetector;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends AbstractEntity{

	// Serialisation identifier

	protected static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------------

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				creationDate;

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
	
	// Derived attributes ----------------------------------------------------
	
	public boolean titleHasSpam(final SystemConfiguration systemConfiguration) {
		return SpamDetector.isSpam(this.getTitle() , systemConfiguration);
	}
	
	public boolean bodyHasSpam(final SystemConfiguration systemConfiguration) {
		return SpamDetector.isSpam(this.getBody() , systemConfiguration);
	}

}
