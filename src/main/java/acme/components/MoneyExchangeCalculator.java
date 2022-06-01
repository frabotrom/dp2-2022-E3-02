package acme.components;

import org.springframework.web.client.RestTemplate;

import acme.entities.SystemConfiguration;
import acme.forms.dashboards.MoneyExchange;
import acme.framework.datatypes.Money;

public class MoneyExchangeCalculator {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Methods
	
	private MoneyExchangeCalculator() {}
	
	public static Money convertMoney(final SystemConfiguration systemConfiguration, final Money moneyToConvert) {
		
		final String targetCurrency = systemConfiguration.getSystemCurrency();
		
		// Se declara el tipo de algunas variables que necesitamos
		MoneyExchange result;
		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		Money target;

		try {
			
			api = new RestTemplate();
			sourceCurrency = moneyToConvert.getCurrency();
			sourceAmount = moneyToConvert.getAmount();

			// Llamada a la API. Hay que pasarle la currency origen, o source, y la que queremos obtener, o target
			record = api.getForObject(
				"https://api.exchangerate.host/latest?base={0}&symbols={1}",
				ExchangeRate.class, 
				sourceCurrency,
				targetCurrency);

			assert record != null;
			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			target = new Money();
			target.setAmount(targetAmount);
			target.setCurrency(targetCurrency);

			// Se crea una clase MoneyExchange para guardar toda la conversión
			result = new MoneyExchange();
			result.setSource(moneyToConvert);
			result.setTargetCurrency(targetCurrency);
			result.setDate(record.getDate());
			result.setTarget(target);
			
			return result.getTarget();
			
		}
		
		// En caso de que hubiera errores
		catch (final Throwable errors) {
			
			System.out.println(errors);
			return null;
		}
		
	}

}
