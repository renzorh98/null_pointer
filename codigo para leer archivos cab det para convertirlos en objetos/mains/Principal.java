package mains;


import java.io.IOException;
import java.util.ArrayList;

import modelosCabDet.Cabecera;
import modelosCabDet.Detalle;
import modelosCabDet.GeneradorCabDet;
public class Principal {

	public static void main (String[]args){
		//solo deben modificarse los paths y han de existir
		String pathCab = "D:\\sunat_archivos\\sfs\\DATA\\20601898447-01-FA01-00000156.cab";
		String pathDet = "D:\\sunat_archivos\\sfs\\DATA\\20601898447-01-FA01-00000156.det";
		
		try{
			GeneradorCabDet gcd = new GeneradorCabDet(pathCab, pathDet);
			
			Cabecera cab = gcd.obtenerCabecera();
			System.out.println(cab);
			
			ArrayList<Detalle> dets = gcd.obtenerDetalles();
			
			for(Detalle det : dets) {
				System.out.println(det);
			}
			
			//una vez tengamos los objetos Cab y Det podriamos enviar a un objeto GeneradorXML que aun no hicimos
		}catch (IOException e) {
			System.out.println("ERROR: archivo no existe,  " + e.getMessage());
		}
	}

}
