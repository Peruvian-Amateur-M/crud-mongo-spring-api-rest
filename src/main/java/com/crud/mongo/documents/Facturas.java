package com.crud.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection="Facturas")
public class Facturas {

	@Id
	private Long id;
	
	@NotNull
	
	private String emisor;
	
	@NotNull
	
	private String receptor;
	
	@Pattern(regexp="/^[0-9]+$/")
	@NotNull
	private Long numFactura;

	public Facturas() {
		super();
	}

	public Facturas(Long id, String emisor, String receptor, Long numFactura) {
		super();
		this.id = id;
		this.emisor = emisor;
		this.receptor = receptor;
		this.numFactura = numFactura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public Long getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(Long numFactura) {
		this.numFactura = numFactura;
	}
	
	
}
