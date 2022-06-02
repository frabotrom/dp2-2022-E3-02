package acme.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.components.MoneyExchangeCalculator;
import acme.components.SpamDetector;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
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
	public Money totalPrice(final Collection<Amount> lAmount, final SystemConfiguration systemConfiguration) {
		
		Double finalMoneyAmount = 0.;																		// Precio del toolkit
		final String finalMoneyCurrency = systemConfiguration.getSystemCurrency();							// Moneda por defecto del sistema
		
		for(final Amount amount: lAmount) {
			
			if(amount.getToolkit().getId()==this.id) {
				
				final Integer actualItemAmount = amount.getTotal();											// Cantidad de Items
				final String actualMoneyCurrency = amount.getItem().getRetailPrice().getCurrency();			// Moneda del precio del item		
				final Money actualItemMoney = amount.getItem().getRetailPrice();							// Precio del item	
				Double actualMoneyAmountConverted = null;													// Precio del item convertido (a la de por defecto)
								
				if(finalMoneyCurrency.equals(actualMoneyCurrency)) {
					actualMoneyAmountConverted = actualItemMoney.getAmount();
				}
				
				else {
					actualMoneyAmountConverted = MoneyExchangeCalculator.convertMoney(systemConfiguration, actualItemMoney).getAmount();
				}
				
				finalMoneyAmount = finalMoneyAmount + actualItemAmount * actualMoneyAmountConverted;				
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
	
	
	public boolean titleHasSpam(final SystemConfiguration systemConfiguration) {
		return SpamDetector.isSpam(this.getTitle() , systemConfiguration);
	}
	
	public boolean descriptionHasSpam(final SystemConfiguration systemConfiguration) {
		return SpamDetector.isSpam(this.getDescription() , systemConfiguration);
	}
	
	public boolean asemblyNotesHasSpam(final SystemConfiguration systemConfiguration) {
		return SpamDetector.isSpam(this.getAsemblyNotes() , systemConfiguration);
	}
	
	
	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected  Inventor 		inventor;	
		
}
