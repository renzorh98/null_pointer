package Generadores;

public class Cabecera {
	String tipOperacion;
	String fechaEmision;
	String codDomicilioFiscalEmisor;
	
	String tipDocIdeUsuario;
	String NumDocIdeUsuario;
	String apeNomUsario;
	
	String tipMoneda;
	String descGloblales;
	String sumOtrosCargos;
	
	String totalDescuentos;
	String totalVentaGravada;
	String totalVentaInafectadas;
	
	String totalVentaExoneradas;
	String sumIGV;
	String sumISC;
	
	String sumOtrosTributos;
	String importeTotalVenta;
	public String getTipOperacion() {
		return tipOperacion;
	}
	public void setTipOperacion(String tipOperacion) {
		this.tipOperacion = tipOperacion;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCodDomicilioFiscalEmisor() {
		return codDomicilioFiscalEmisor;
	}
	public void setCodDomicilioFiscalEmisor(String codDomicilioFiscalEmisor) {
		this.codDomicilioFiscalEmisor = codDomicilioFiscalEmisor;
	}
	public String getTipDocIdeUsuario() {
		return tipDocIdeUsuario;
	}
	public void setTipDocIdeUsuario(String tipDocIdeUsuario) {
		this.tipDocIdeUsuario = tipDocIdeUsuario;
	}
	public String getNumDocIdeUsuario() {
		return NumDocIdeUsuario;
	}
	public void setNumDocIdeUsuario(String numDocIdeUsuario) {
		NumDocIdeUsuario = numDocIdeUsuario;
	}
	public String getApeNomUsario() {
		return apeNomUsario;
	}
	public void setApeNomUsario(String apeNomUsario) {
		this.apeNomUsario = apeNomUsario;
	}
	public String getTipMoneda() {
		return tipMoneda;
	}
	public void setTipMoneda(String tipMoneda) {
		this.tipMoneda = tipMoneda;
	}
	public String getDescGloblales() {
		return descGloblales;
	}
	public void setDescGloblales(String descGloblales) {
		this.descGloblales = descGloblales;
	}
	public String getSumOtrosCargos() {
		return sumOtrosCargos;
	}
	public void setSumOtrosCargos(String sumOtrosCargos) {
		this.sumOtrosCargos = sumOtrosCargos;
	}
	public String getTotalDescuentos() {
		return totalDescuentos;
	}
	public void setTotalDescuentos(String totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}
	public String getTotalVentaGravada() {
		return totalVentaGravada;
	}
	public void setTotalVentaGravada(String totalVentaGravada) {
		this.totalVentaGravada = totalVentaGravada;
	}
	public String getTotalVentaInafectadas() {
		return totalVentaInafectadas;
	}
	public void setTotalVentaInafectadas(String totalVentaInafectadas) {
		this.totalVentaInafectadas = totalVentaInafectadas;
	}
	public String getTotalVentaExoneradas() {
		return totalVentaExoneradas;
	}
	public void setTotalVentaExoneradas(String totalVentaExoneradas) {
		this.totalVentaExoneradas = totalVentaExoneradas;
	}
	public String getSumIGV() {
		return sumIGV;
	}
	public void setSumIGV(String sumIGV) {
		this.sumIGV = sumIGV;
	}
	public String getSumISC() {
		return sumISC;
	}
	public void setSumISC(String sumISC) {
		this.sumISC = sumISC;
	}
	public String getSumOtrosTributos() {
		return sumOtrosTributos;
	}
	public void setSumOtrosTributos(String sumOtrosTributos) {
		this.sumOtrosTributos = sumOtrosTributos;
	}
	public String getImporteTotalVenta() {
		return importeTotalVenta;
	}
	public void setImporteTotalVenta(String importeTotalVenta) {
		this.importeTotalVenta = importeTotalVenta;
	}
	public String toString() {
		return getTipOperacion() + "\n" + 
				getFechaEmision() + "\n" +
				getCodDomicilioFiscalEmisor() + "\n" +
				getTipDocIdeUsuario() + "\n" +
				getNumDocIdeUsuario() + "\n" +
				getApeNomUsario() + "\n" +
				getTipMoneda() + "\n" +
				getDescGloblales() + "\n" +
				getSumOtrosCargos() + "\n" +
				getTotalDescuentos() + "\n" +
				getTotalVentaGravada() + "\n" +
				getTotalVentaInafectadas() + "\n" +
				getTotalVentaExoneradas() + "\n" +
				getSumIGV() + "\n" +
				getSumISC() + "\n" +
				getSumOtrosTributos() + "\n" +
				getImporteTotalVenta();			
	}
	
	
}