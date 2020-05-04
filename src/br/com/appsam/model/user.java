package br.com.appsam.model;

import java.util.Date;

public class user {
	//nessa classe apenas atributos, getters and setters
	//para que consiga criar objetos dessa classe
	//define primeiro os dados que o programa vai usar
		private int userid;
		private String nome;
		private String sobrenome;
		private Date dt_nasc;
		//////////private String sexo;
		private double altura;
		private double peso;
		private String email;
		
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getSobrenome() {
			return sobrenome;
		}
		public void setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
		}
		public Date getDt_nasc() {
			return dt_nasc;
		}
		public void setDt_nasc(Date dt_nasc) {
			this.dt_nasc = dt_nasc;
		}
		//public String getSexo() {
		//	return sexo;
		//}
		//public void setSexo(String sexo) {
		///	this.sexo = sexo;
		//}
		public double getAltura() {
			return altura;
		}
		public void setAltura(double altura) {
			this.altura = altura;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
				
}