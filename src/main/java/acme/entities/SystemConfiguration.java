
package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// ------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	// ------------------------------------------------------------------
	
	private static final String SPAM_REGEX = "((\\p{L}[\\p{L}'\\s]*)(,\\s*(\\p{L}[\\p{L}'\\s]*))*)?";
	
	@NotNull
	protected String strongSpamTerms;
	
	@NotNull
	protected String weakSpamTerms;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String systemCurrency;
	
	@Range(min=0,max=1)
	protected double weakThreshold;
	
	@Range(min=0,max=1)
	protected double strongThreshold;
	
}