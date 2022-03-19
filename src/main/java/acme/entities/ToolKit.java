package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ToolKit extends AbstractEntity{
	
	private static final long serialVersionUID= 1L;
	

	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Length(max=101)
	protected String title;
	
	@NotBlank
	@Length(max=256)
	protected String description;
	
	@NotBlank
	@Length(max=256)
	protected String asemblyNotes;
	
	@URL
	protected String info;
	
	//QUITAR EL COMENTARIO CUANDO SE MERGEE CON LAS CLASES COMPONENT Y TOOL
	
	/*@NotBlank
	@Valid
	@OneToMany
	protected Component component;
	
	@NotBlank
	@Valid
	@OneToOne
	protected Tool tool;*/
}