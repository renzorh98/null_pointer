package modelosCabDet;

public class Detalle {
	String codUniMedItem;
	String cantUniPorItem;
	String codProducto;
	
	String codProducSUNAT;
	String descripcion;
	String valUnitario;
	
	String descuentos;
	String montoIGVPorItem;
	String afectIGV;
	
	String montoISCPorItem;
	String tipSistemISC;
	String precioVentaUniPorItem;
	
	String valorVentaPorItem;

	public String getCodUniMedItem() {
		return codUniMedItem;
	}

	public void setCodUniMedItem(String codUniMedItem) {
		this.codUniMedItem = codUniMedItem;
	}

	public String getCantUniPorItem() {
		return cantUniPorItem;
	}

	public void setCantUniPorItem(String cantUniPorItem) {
		this.cantUniPorItem = cantUniPorItem;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getCodProducSUNAT() {
		return codProducSUNAT;
	}

	public void setCodProducSUNAT(String codProducSUNAT) {
		this.codProducSUNAT = codProducSUNAT;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValUnitario() {
		return valUnitario;
	}

	public void setValUnitario(String valUnitario) {
		this.valUnitario = valUnitario;
	}

	public String getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(String descuentos) {
		this.descuentos = descuentos;
	}

	public String getMontoIGVPorItem() {
		return montoIGVPorItem;
	}

	public void setMontoIGVPorItem(String montoIGVPorItem) {
		this.montoIGVPorItem = montoIGVPorItem;
	}

	public String getAfectIGV() {
		return afectIGV;
	}

	public void setAfectIGV(String afectIGV) {
		this.afectIGV = afectIGV;
	}

	public String getMontoISCPorItem() {
		return montoISCPorItem;
	}

	public void setMontoISCPorItem(String montoISCPorItem) {
		this.montoISCPorItem = montoISCPorItem;
	}

	public String getTipSistemISC() {
		return tipSistemISC;
	}

	public void setTipSistemISC(String tipSistemISC) {
		this.tipSistemISC = tipSistemISC;
	}

	public String getPrecioVentaUniPorItem() {
		return precioVentaUniPorItem;
	}

	public void setPrecioVentaUniPorItem(String precioVentaUniPorItem) {
		this.precioVentaUniPorItem = precioVentaUniPorItem;
	}

	public String getValorVentaPorItem() {
		return valorVentaPorItem;
	}

	public void setValorVentaPorItem(String valorVentaPorItem) {
		this.valorVentaPorItem = valorVentaPorItem;
	}
	
	public String toString() {
		return getCodUniMedItem() + "\n" +
				 getCantUniPorItem() + "\n" +
				 getCodProducto() + "\n" +
				 getCodProducSUNAT() + "\n" +
				 getDescripcion() + "\n" +
				 getValUnitario() + "\n" +
				 getDescuentos() + "\n" +
				 getMontoIGVPorItem() + "\n" +
				 getAfectIGV() + "\n" +
				 getMontoISCPorItem() + "\n" +
				 getTipSistemISC() + "\n" +
				 getPrecioVentaUniPorItem() + "\n" +
				 getValorVentaPorItem();
	}
}
