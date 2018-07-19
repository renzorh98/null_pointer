package Generadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// esta clase recibe dos directorios (pathCab y pathDet) y tiene dos metodos que retornan una Cabecera y Detalle respectivamente
public class GeneradorCabDet {
	String pathCab;
	String pathDet;
	public GeneradorCabDet(String pathCab, String pathDet) {
		this.pathCab = pathCab;
		this.pathDet = pathDet;
	}
	public Cabecera obtenerCabecera() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (new File (pathCab)));
		String linea = br.readLine();
		String []arr = linea.split("\\|");
		
	    Cabecera cab = new Cabecera();
	    
	    cab.setTipOperacion(arr[0]);
	    cab.setFechaEmision(arr[1]);
	    cab.setCodDomicilioFiscalEmisor(arr[2]);
	    cab.setTipDocIdeUsuario(arr[3]);
	    cab.setNumDocIdeUsuario(arr[4]);
	    cab.setApeNomUsario(arr[5]);
	    cab.setTipMoneda(arr[6]);
	    cab.setDescGloblales(arr[7]);
	    cab.setSumOtrosCargos(arr[8]);
	    cab.setTotalDescuentos(arr[9]);
	    cab.setTotalVentaGravada(arr[10]);
	    cab.setTotalVentaInafectadas(arr[11]);
	    cab.setTotalVentaExoneradas(arr[12]);
	    cab.setSumIGV(arr[13]);
	    cab.setSumISC(arr[14]);
	    cab.setSumOtrosTributos(arr[15]);
	    cab.setImporteTotalVenta(arr[16]);
	    
	    br.close();
	    
	    return cab;
	}
	public ArrayList<Detalle> obtenerDetalles() throws IOException{
		Scanner br = new Scanner(new File (pathDet));
		
		ArrayList<Detalle> detalles = new ArrayList<>();
		while(br.hasNextLine()) {
			String linea = br.nextLine();
			String []arr = linea.split("\\|");
			
			Detalle det = new Detalle();
			
			det.setCodUniMedItem(arr[0]);
			det.setCantUniPorItem(arr[1]);
			det.setCodProducto(arr[2]);
			det.setCodProducSUNAT(arr[3]);
			det.setDescripcion(arr[4]);
			det.setValUnitario(arr[5]);
			det.setDescuentos(arr[6]);
			det.setMontoIGVPorItem(arr[7]);
			det.setAfectIGV(arr[8]);
			det.setMontoISCPorItem(arr[9]);
			det.setTipSistemISC(arr[10]);
			det.setPrecioVentaUniPorItem(arr[11]);
			det.setValorVentaPorItem(arr[12]);
			
			detalles.add(det);
		}
		br.close();
		return detalles;
		
	}
}
