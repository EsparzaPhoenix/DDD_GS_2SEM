package br.com.fiap.globalsolution.model;

public class Medico {
	
	private int id;
	private int idHospital;
	private String nome;
	private Long crm;
	private String especialidade;
	
	public Medico() {};
	
	public Medico(int id, int idHospital, String nome, Long crm, String especialidade) {
		this.id = id;
		this.idHospital = idHospital;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCrm() {
		return crm;
	}
	public void setCrm(Long crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public int getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(int idHospital) {
		this.idHospital = idHospital;
	}
	
	
}
