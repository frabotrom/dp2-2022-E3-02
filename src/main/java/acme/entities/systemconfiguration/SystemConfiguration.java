
package acme.entities.systemconfiguration;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier
	private static final long serialVersionUID = 1L;

	public static String systemCurrency = "EUR";
	
	public static final List<String> acceptedCurrencies = Arrays.asList("EUR", "USD", "GBP");
	
	public static List<String> strongSpamTerms_en = Arrays.asList("sex", "hard core", "viagra", "cialis");
	
	public static List<String> strongSpamTerms_es = Arrays.asList("sexo", "hard core", "viagra", "cialis");
	
	public static final Double strongSpamThreshold = 0.1d;
	
	public static List<String> weakSpamTerms_en = Arrays.asList("sexy", "nigeria", "you've won", "one million");
	
	public static List<String> weakSpamTerms_es = Arrays.asList("sexy", "nigeria", "has ganado", "un millón");
	
	public static final Double weakSpamThreshold = 0.25d;
}
