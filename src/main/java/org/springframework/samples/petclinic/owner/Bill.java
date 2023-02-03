package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="bills")
public class Bill {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id; 

    @Temporal(TemporalType.DATE)
    @Column(name="paymentDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    public Bill () { }

    public long getId() {
                return id;
    }
 
    public void setId(long id) {
                this.id = id;
    }

    public LocalDate getPaymentDate() {
                return paymentDate;

    }

    public void setPaymentDate(LocalDate paymentDate) {
                this.paymentDate = paymentDate;

    }

	
}
