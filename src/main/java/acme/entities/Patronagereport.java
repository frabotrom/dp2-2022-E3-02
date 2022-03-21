package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronagereport extends AbstractEntity {
    
    // Serialisation identifier -----------------------------------------------

        protected static final long serialVersionUID = 1L;
        protected static long serialNumber = 1L;
        
    // Attributes -------------------------------------------------------------
        
        @NotNull
        @Temporal(TemporalType.TIMESTAMP)
        @Past
        protected Date					creationMoment;
        
        @NotBlank
        @Length(min=1, max = 255)
        protected String 				memorandum;
        
        @URL
        protected String 				optionalLink;
        
    // Derived attributes -----------------------------------------------------
        
        @NotBlank
        public String getSequenceNumber() {
            String result = "";
            final String patronCode = this.getPatronage().getCode();
            
            if (Patronagereport.serialNumber < 10L) {
                result = patronCode + ":000" + Patronagereport.serialNumber;
            } else if (Patronagereport.serialNumber >= 10L && Patronagereport.serialNumber < 100L) {
                result = patronCode + ":00" + Patronagereport.serialNumber;
            } else if (Patronagereport.serialNumber >= 100L && Patronagereport.serialNumber < 1000L) {
                result = patronCode + ":0" + Patronagereport.serialNumber;
            } else {
                result = patronCode + ":" + Patronagereport.serialNumber;
            }
            
            Patronagereport.serialNumber++;
            
            return result;
        }
        
        
        
    // Relationships ----------------------------------------------------------
        
        @Valid
        @NotNull
        @ManyToOne(optional = false)
        protected Patronage 			patronage;
        
}