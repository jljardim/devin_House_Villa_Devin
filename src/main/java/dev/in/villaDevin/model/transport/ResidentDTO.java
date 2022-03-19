package dev.in.villaDevin.model.transport;

import java.math.BigDecimal;
import java.time.LocalDate;
//import java.util.UUID;
import java.util.UUID;

import dev.in.villaDevin.model.Resident;

public class ResidentDTO {

	private Integer id;
	private String uuid;
    private String name;
    private String lastName;
    private LocalDate dateNasc;
    private BigDecimal income;
    private String cpf;
    
    public ResidentDTO() {
    	
    }
    
 public ResidentDTO(Resident resident) {
	    this.id = resident.getId();
	    this.uuid = resident.getUuid();
	    this.name = resident.getName();
		this.lastName = resident.getLastName();
		this.dateNasc = resident.getDateNasc();
		this.income = resident.getIncome();
		this.cpf = resident.getCpf();
    }
    
	public ResidentDTO( Integer id, String uuid, String name, String lastName, LocalDate dateNasc, BigDecimal income, String cpf) {
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
		this.lastName = lastName;
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
		return "ResidentDTO [id=" + id + ", uuid=" + uuid + ", name=" + name + ", lastName=" + lastName + ", dateNasc="
				+ dateNasc + ", income=" + income + ", cpf=" + cpf + "]";
	}
	

	

}
