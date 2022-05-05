package dev.in.villaDevin.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import dev.in.villaDevin.model.transport.CreateResidentRequestDTO;

@Entity
public class Resident {
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
	@Column(precision = 20, scale = 2, nullable = false)
	private BigDecimal income;
	private String cpf;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "id"
    )
    private User user;

	public Resident() {

	}

	public Resident(CreateResidentRequestDTO residentDTO) {
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
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	  public static final Comparator<Resident> compareByCost = (Resident v1, Resident v2) -> {
		  return v1.getIncome().compareTo(v2.getIncome());
	    };

	@Override
	public String toString() {
		return "Resident [id=" + id + ", uuid=" + uuid + ", name=" + name + ", lastName=" + lastName + ", dateNasc="
				+ dateNasc + ", email=" + email + ", income=" + income + ", cpf=" + cpf + "]";
	}

}
