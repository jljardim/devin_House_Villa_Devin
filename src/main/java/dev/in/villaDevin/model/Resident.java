package dev.in.villaDevin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.in.villaDevin.model.transport.ResidentDTO;
import dev.in.villaDevin.model.transport.ResidentNameAndIdProjection;

@Entity
public class Resident implements ResidentNameAndIdProjection{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	private String name;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "datenasc")
	private LocalDate dateNasc;
	private String email;
	private BigDecimal income;
	private String cpf;

	public Resident() {

	}

	public Resident(ResidentDTO residentDTO) {
		this.name = residentDTO.getName();
		this.uuid = UUID.randomUUID().toString();
		this.lastName = residentDTO.getLastName();
		this.dateNasc = residentDTO.getDateNasc();
		this.email = residentDTO.getEmail();
		this.income = residentDTO.getIncome();
		this.cpf = residentDTO.getCpf();
	}

	public Resident(Long id, String uuid, String name, String lastName, LocalDate dateNasc, String email,
			BigDecimal income, String cpf) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.lastName = lastName;
		this.dateNasc = dateNasc;
		this.email = email;
		this.income = income;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "Resident [id=" + id + ", uuid=" + uuid + ", name=" + name + ", lastName=" + lastName + ", dateNasc="
				+ dateNasc + ", email=" + email + ", income=" + income + ", cpf=" + cpf + "]";
	}

}
