package acme.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(max=101)
	protected String name;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Length(max=101)
	protected String technology;
	
	@NotBlank
	@Length(max=256)
	protected String description;
	
	@Min(1)
	protected Double retailPrice;
	
	@URL
	protected String info;
	
	
	//QUITAR EL COMENTADO CUANDO ESTE LA CLASE COMPONENT HECHA
	/*@NotBlank
	@Valid
	@ManyToMany
	protected Component component;
	*/
}
