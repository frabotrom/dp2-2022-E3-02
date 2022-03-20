
package acme.entities;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier
	private static final long		serialVersionUID	= 1L;

	public static String			systemCurrency		= "EUR";

	public static final String[]	acceptedCurrencies	= new String[] {
		"EUR", "USD", "GBP"
	};

	public static String[]			strongSpamTerms_en	= new String[] {
		"sex", "hard core", "viagra", "cialis"
	};

	public static String[]			strongSpamTerms_es	= new String[] {
		"sexo", "hard core", "viagra", "cialis"
	};

	public static final Double		strongSpamThreshold	= 0.1d;

	public static String[]			weakSpamTerms_en	= new String[] {
		"sexy", "nigeria", "you've won", "one million"
	};

	public static String[]			weakSpamTerms_es	= new String[] {
		"sexy", "nigeria", "has ganado", "un millón"
	};

	public static final Double		weakSpamThreshold	= 0.25d;
}
