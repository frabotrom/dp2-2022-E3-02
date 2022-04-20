package acme.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity{
	
	// Serialisation identifier

	protected static final long		serialVersionUID	= 1L;

	// Attributes	

	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String 				code;
	
	@NotBlank
	@Length(max=101)
	protected String 				title;
	
	@NotBlank
	@Length(max=256)
	protected String 				description;
	
	@NotBlank
	@Length(max=256)
	protected String 				asemblyNotes;
	
	@URL
	protected String 				info;
	
	// Determina si el toolkit está terminado o no
	protected boolean 				draftMode;
	
	// Derived attributes
	
	@NotNull
	@Transient
	public Money totalPrice(final Collection<Item> lItem) {
		
		Double finalMoneyAmount = 0.;														// Precio del toolkit
		final String finalMoneyCurrency = SystemConfiguration.systemCurrency;				// Moneda por defecto del sistema
		
		for(final Item item: lItem) {
			
			if(item.getAmount().getToolkit().getId()==this.id) {
				
				final Integer actualAmount = item.getAmount().getTotal();						// Cantidad de Items
				final String actualMoneyCurrency = item.getRetailPrice().getCurrency();			// Moneda del precio del item		
				final Double actualMoneyAmount = item.getRetailPrice().getAmount();				// Precio del item	
				Double actualMoneyAmountConverted = null;										// Precio del item convertido (a la de por defecto)
								
				if(finalMoneyCurrency.equals(actualMoneyCurrency)) {
					actualMoneyAmountConverted = actualMoneyAmount;
				}
				
				else {
					if(finalMoneyCurrency.equals("EUR")) {
						if(actualMoneyCurrency.equals("USD")) {
							actualMoneyAmountConverted = actualMoneyAmount*0.92;
						}
						if(actualMoneyCurrency.equals("GBP")) {
							actualMoneyAmountConverted = actualMoneyAmount*1.20;
						}
					}
					
					if(finalMoneyCurrency.equals("USD")) {
						if(actualMoneyCurrency.equals("EUR")) {
							actualMoneyAmountConverted = actualMoneyAmount*1.08;						
						}
						if(actualMoneyCurrency.equals("GBP")) {
							actualMoneyAmountConverted = actualMoneyAmount*1.30;						
						}				
					}
					
					if(finalMoneyCurrency.equals("GBP")) {
						if(actualMoneyCurrency.equals("USD")) {
							actualMoneyAmountConverted = actualMoneyAmount*0.76;						
						}
						if(actualMoneyCurrency.equals("EUR")) {
							actualMoneyAmountConverted = actualMoneyAmount*0.83;						
						}					
					}				
				}
				
				finalMoneyAmount = finalMoneyAmount + actualAmount * actualMoneyAmountConverted;				
			}
			
			else {
				System.out.println("Something happen");
			}
		}
		
		final Money totalPriceMoney = new Money();
		totalPriceMoney.setAmount(finalMoneyAmount);
		totalPriceMoney.setCurrency(finalMoneyCurrency);
		
		return totalPriceMoney;
	}
	
	// Implemantar el totalPrice en el Servicio. Hay que tener en cuenta de que hay que pasarle una lista de items que pertenezcan al toolkit 
		
}
