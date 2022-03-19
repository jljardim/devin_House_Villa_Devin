package dev.in.villaDevin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
//import java.util.UUID;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.in.villaDevin.model.transport.ResidentDTO;


@Entity
public class Resident {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String uuid;
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "datenasc")
    private LocalDate dateNasc;
    private BigDecimal income;
    private String cpf;
    
    public Resident() {
    	
    }
    
 public Resident(ResidentDTO residentDTO) {
	    this.name = residentDTO.getName();
	    this.uuid = UUID.randomUUID().toString();
		this.lastName = residentDTO.getLastName();
		this.dateNasc = residentDTO.getDateNasc();
		this.income = residentDTO.getIncome();
		this.cpf = residentDTO.getCpf();
    }
    
    
	public Resident(Integer id, String uuid, String name, String lastName, LocalDate dateNasc, BigDecimal income, String cpf) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.lastName = lastName;
		this.dateNasc = dateNasc;
		this.income = income;
		this.cpf = cpf;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName =lastName;
	}

	public LocalDate getDateNasc() {
		return dateNasc;
	}

	public void setDateNasc(LocalDate dateNasc) {
		this.dateNasc = dateNasc;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Resident [uuid=" + uuid + ", name=" + name + ", lastName=" + lastName + ", dateNasc=" + dateNasc
				+ ", income=" + income + ", cpf=" + cpf + "]";
	}
	


	
	
	
//
//	@Override
//	public String toString() {
//		return "ResidentDTO [uuid="name=" + name + ", lastName=" + lastName + ", dateNasc=" + dateNasc
//				+ ", income=" + income + ", cpf=" + cpf + "]";
//	}
}
