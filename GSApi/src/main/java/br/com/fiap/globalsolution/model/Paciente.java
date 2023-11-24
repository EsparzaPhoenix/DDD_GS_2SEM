package br.com.fiap.globalsolution.model;

import java.util.Date;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private Long telefone;
    private int convenio;
    private String sexo;
    private String faixaEtaria;
    private Date dataNascimento;
    
    public Paciente() {};
        
    public Paciente(int id, String nome, String email, Long telefone, int convenio, String sexo, String faixaEtaria, Date dataNascimento) {
    	this.id = id;
    	this.nome = nome;
    	this.email = email;
    	this.telefone = telefone;
    	this.convenio = convenio;
    	this.sexo = sexo;
    	this.faixaEtaria = faixaEtaria;
    	this.dataNascimento = dataNascimento;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public int getConvenio() {
		return convenio;
	}
	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
    
}
