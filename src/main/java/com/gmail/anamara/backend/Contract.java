package com.gmail.anamara.backend;

import java.time.LocalDate;

public class Contract extends Review {

	private String contract_id;
	private String contratante;
	private String contratante_id;
	private String objeto_contrato;
	private String origen;
	private String destino;
	private String fecha_contrato;
	private String descripcion_servicio;
	private String ruta_opcional;
	private double valor_contrato_COP;
	private boolean aplica_iva;
	
	private String nombre_conductor;
	private String tipo_vehiculo;
	
	
	public Contract() {
		// TODO Auto-generated constructor stub
	}

	public Contract(int score, String name, LocalDate date, Category category, int count) {
		super(score, name, date, category, count);
		// TODO Auto-generated constructor stub
	}

	public Contract(int score, String name, LocalDate date, Category category, int count, String driver,
			String idfuec) {
		super(score, name, date, category, count, driver, idfuec);
		// TODO Auto-generated constructor stub
	}

	public Contract(Review other) {
		super(other);
		// TODO Auto-generated constructor stub
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContratante() {
		return contratante;
	}

	public void setContratante(String contratante) {
		this.contratante = contratante;
	}

	public String getContratante_id() {
		return contratante_id;
	}

	public void setContratante_id(String contratante_id) {
		this.contratante_id = contratante_id;
	}

	public String getObjeto_contrato() {
		return objeto_contrato;
	}

	public void setObjeto_contrato(String objeto_contrato) {
		this.objeto_contrato = objeto_contrato;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha_contrato() {
		return fecha_contrato;
	}

	public void setFecha_contrato(String fecha_contrato) {
		this.fecha_contrato = fecha_contrato;
	}

	public String getDescripcion_servicio() {
		return descripcion_servicio;
	}

	public void setDescripcion_servicio(String descripcion_servicio) {
		this.descripcion_servicio = descripcion_servicio;
	}

	public String getRuta_opcional() {
		return ruta_opcional;
	}

	public void setRuta_opcional(String ruta_opcional) {
		this.ruta_opcional = ruta_opcional;
	}

	public double getValor_contrato_COP() {
		return valor_contrato_COP;
	}

	public void setValor_contrato_COP(double valor_contrato_COP) {
		this.valor_contrato_COP = valor_contrato_COP;
	}

	public boolean isAplica_iva() {
		return aplica_iva;
	}

	public void setAplica_iva(boolean aplica_iva) {
		this.aplica_iva = aplica_iva;
	}

	public String getNombre_conductor() {
		return nombre_conductor;
	}

	public void setNombre_conductor(String nombre_conductor) {
		this.nombre_conductor = nombre_conductor;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}
	
	

}
