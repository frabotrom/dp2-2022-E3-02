
package acme.entities.patronage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {

	//Basic attributes
	
	private static final long	serialVersionUID	= 1L;
	
	private static int lastId = 0; 

	protected int id;
	
	
	//Relationships
	
	// Stand-in until related class is finished
	protected List<?> messageList= new ArrayList<>();
	
	// Stand-in until related class is finished
	protected int patronId;
	
	// Stand-in until related class is finished
	protected int inventorId;

	
	// Derived attributes
	
	@NotBlank
	@Pattern(regexp = "[0-9]+:[0-9]{4}", message="Invalid sequence number")
	protected String sequenceNumber;

	@NotBlank
	@Past
	protected LocalDateTime creationTime;
	
	
	// Optional Attributes
	
	@NotBlank
	@Length(max = 256)
	protected String memorandum;
	
	@URL
	protected String extraLink;
	
	
	// Constructors
	
	public PatronageReport(final int patronId, final int inventorId, final List<?> messageList){
		++PatronageReport.lastId;
		this.id= PatronageReport.lastId;
		this.patronId= patronId;
		this.inventorId= inventorId;
		this.messageList= messageList;
		this.sequenceNumber= String.format("%d:|%04d|", this.patronId, this.id);
		this.creationTime= LocalDateTime.now();
	}
	
	public PatronageReport(final int patronId, final int inventorId, final List<?> messageList, final String memorandum, final String extraLink){
		++PatronageReport.lastId;
		this.id= PatronageReport.lastId;
		this.patronId= patronId;
		this.inventorId= inventorId;
		this.messageList= messageList;
		this.sequenceNumber= String.format("%d:|%04d|", this.patronId, this.id);
		this.creationTime= LocalDateTime.now();
		this.memorandum= memorandum;
		this.extraLink= extraLink;
	}
}
