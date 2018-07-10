package iss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class test_factura {
	public static void main (String [] Args) throws IOException {
		   
		 
	        Scanner scan = new Scanner(System.in);
			//System.out.println("Ingrese ruta de cabecera");
			String cab = "D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\20601898447-01-FA01-00000156.cab";
			//System.out.println("Ingrese ruta de detalle");
			String det = "D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\20601898447-01-FA01-00000156.det";
			//System.out.println("Ingrese ruta donde se generara el xml");
			String destino = "D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\";
			
			String path = cab;
			String nombre_xml = path.substring(path.lastIndexOf('\\') + 1, path.lastIndexOf('.'));
			String codigo_factura = path.substring(path.lastIndexOf('-') - 4, path.lastIndexOf('.'));
	
			
			GeneradorCabDet generar = new GeneradorCabDet(cab,det);
			Cabecera cabecera = new Cabecera();
			cabecera = generar.obtenerCabecera();
			ArrayList<Detalle> detalles;
			detalles = generar.obtenerDetalles();
			
			
			
			  FileWriter fichero = null;
		       PrintWriter pw = null;
		       
		       try
		       {
		    	   //"D:\\prueba.xml"
		           fichero = new FileWriter(destino+nombre_xml+".xml");
		           pw = new PrintWriter(fichero);
		           xml_factura factura = new xml_factura(cabecera,detalles);
		           factura.setarchivo(pw);
		           factura.setNumero_factura(codigo_factura);
		           factura.inicio_cabecera();
		           factura.total_descuentos();
		          //aca estaba la firma
		           factura.datos_factura();
		           factura.identificador_firma();
		           factura.datos_empresa();
		           factura.datos_cliente();
		           factura.tax_total();
		           factura.monto_total();
		           factura.productos_datos();

		               
		       } catch (Exception e) {
		           e.printStackTrace();
		       } finally {
		          try {
		        
		          if (null != fichero)
		             fichero.close();
		          } catch (Exception e2) {
		             e2.printStackTrace();
		          }
		       }
		  firmar_xml firma = new firmar_xml();
		

	        File fNuevo = new File("D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\"+nombre_xml+".xml");
	        firma.modificar(fNuevo, "  </ext:UBLExtension>", firma.firma);
		       
		       
		       
	}
}
