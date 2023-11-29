package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Oferta")
public class Oferta {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "puesto")
	private String puesto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "salario")
	private Double salario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
