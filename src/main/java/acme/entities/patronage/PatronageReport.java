
package acme.entities.patronage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {

	//Basic attributes

	private static final long	serialVersionUID	= 1L;

	private static int			lastId				= 0;

	protected int				id;

	protected List<String>		messageList			= new ArrayList<>();

	//Relationships

	//Patron should be owning side of relationship to facilitate cascading
	@OneToOne(mappedBy = "patronage_report")
	protected Patron			patron;

	//Same thing here
	@OneToOne(mappedBy = "patronage_report")
	protected Inventor			inventor;

	// Derived attributes

	@NotBlank
	@Pattern(regexp = "[0-9]+:[0-9]{4}", message = "Invalid sequence number")
	protected String			sequenceNumber;

	@NotBlank
	@Past
	protected LocalDateTime		creationTime;

	// Optional Attributes

	@NotBlank
	@Length(max = 256)
	protected String			memorandum;

	@URL
	protected String			extraLink;

	// Constructors


	public PatronageReport(final Patron patron, final Inventor inventor, final List<String> messageList) {
		++PatronageReport.lastId;
		this.id = PatronageReport.lastId;
		this.patron = patron;
		this.inventor = inventor;
		this.messageList = messageList;
		this.sequenceNumber = String.format("%d:|%04d|", this.patron.getId(), this.id);
		this.creationTime = LocalDateTime.now();
	}

	public PatronageReport(final Patron patron, final Inventor inventor, final List<String> messageList, final String memorandum, final String extraLink) {
		++PatronageReport.lastId;
		this.id = PatronageReport.lastId;
		this.patron = patron;
		this.inventor = inventor;
		this.messageList = messageList;
		this.sequenceNumber = String.format("%d:|%04d|", this.patron.getId(), this.id);
		this.creationTime = LocalDateTime.now();
		this.memorandum = memorandum;
		this.extraLink = extraLink;
	}
}
