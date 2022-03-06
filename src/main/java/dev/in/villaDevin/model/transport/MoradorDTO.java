package dev.in.villaDevin.model.transport;

import java.util.Date;
import java.util.UUID;

public class MoradorDTO {
	
	private Long id;
	private UUID uuid;
    private String nome;
    private String sobre_Nome;
    private Date data_Nasc;
    private Float renda;
    private String cpf;
    
    public MoradorDTO() {
    	
    }
    
	public MoradorDTO(UUID uuid, String nome, String sobre_Nome, Date data_Nasc, Float renda, String cpf) {
		super();
		this.uuid = uuid;
		this.nome = nome;
		this.sobre_Nome = sobre_Nome;
		this.data_Nasc = data_Nasc;
		this.renda = renda;
		this.cpf = cpf;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobre_Nome() {
		return sobre_Nome;
	}

	public void setSobre_Nome(String sobre_Nome) {
		this.sobre_Nome = sobre_Nome;
	}

	public Date getData_Nasc() {
		return data_Nasc;
	}

	public void setData_Nasc(Date data_Nasc) {
		this.data_Nasc = data_Nasc;
	}

	public Float getRenda() {
		return renda;
	}

	public void setRenda(Float renda) {
		this.renda = renda;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(cpf, data_Nasc, nome, renda, sobre_Nome, uuid);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		MoradorDTO other = (MoradorDTO) obj;
//		return Objects.equals(cpf, other.cpf) && Objects.equals(data_Nasc, other.data_Nasc)
//				&& Objects.equals(nome, other.nome) && Objects.equals(renda, other.renda)
//				&& Objects.equals(sobre_Nome, other.sobre_Nome) && Objects.equals(uuid, other.uuid);
//	}

	@Override
	public String toString() {
		return "MoradorDTO [uuid=" + uuid + ", nome=" + nome + ", sobre_Nome=" + sobre_Nome + ", data_Nasc=" + data_Nasc
				+ ", renda=" + renda + ", cpf=" + cpf + "]";
	}

}
