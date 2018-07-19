/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

/**
 *
 * @author Gary Núñez
 */
import EnvioRepositorio.DriveQuickstart;
import java.util.*;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import probandoenvio.EnviadorReceptor;

public class formato {
     static String RUC = "20601898447";
     static String ruta;
	public static void main (String [] Args) {
           

	}
        public static void generarCAB(String serie, String numCorr, String tipOpe,String fecha, String codDomFis, String tipDoc,String doc, String apeNom,
                String tipMon, String DescGlo, String sumOtro, String totDes, String totGrav, String totIna,
                String totExo, String sumIGV, String sumISC, String sumOtrTrib, String impTotVen){
            
            FileWriter fichero = null;
		PrintWriter pw = null;
		try
		{
		ruta = 	RUC+"-"+tipDoc+"-FF0"+serie+"-"+numCorr;
                    fichero = new FileWriter("D:\\SUNAT\\sunat_archivos\\sfs\\DATA\\"+ruta+".cab");
			pw = new PrintWriter(fichero);
			pw.println(tipOpe+"|"+ fecha+"|"+codDomFis+"|"+tipDoc+"|"+doc+"|"+apeNom+"|"+tipMon+"|"+DescGlo+
                                "|"+sumOtro+"|"+totDes+"|"+totGrav+"|"+totIna+"|"+totExo+"|"+sumIGV+"|"+sumISC+"|"+
                                sumOtrTrib+"|"+impTotVen+"|");


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
            
            
            
            
            
            
        }
        public static void generarDet(String tipDoc, String serie, String numCorr, String codUni, String cant, String codPro, String codProSun, String descrip, String valUni,
                String descuen, String IGVporItem, String afecIGV, String ISCporItem, String sistISC, String valUniPorItem, String valVenPorItem) throws Exception{
            FileWriter fichero2 = null;
		PrintWriter pw2 = null;
                String path = "D:\\SUNAT\\sunat_archivos\\sfs\\DATA\\"+ruta;
		try
		{
                    
                    fichero2 = new FileWriter(path+".det");
			pw2 = new PrintWriter(fichero2);
			pw2.println(codUni+"|"+ cant+"|"+codPro+"|"+codProSun+"|"+descrip+"|"+valUni+"|"+descuen+
                                "|"+IGVporItem+"|"+afecIGV+"|"+ISCporItem+"|"+sistISC+"|"+valUniPorItem+"|"+valVenPorItem+"|");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fichero2)
					fichero2.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
                String directorio_xml = "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO\\";
                prueba_xml prueba = new prueba_xml();
	        prueba.ejecutar_generacionxml(path+".cab", path+".det",directorio_xml);
                DriveQuickstart drive = new DriveQuickstart();
                String h = drive.guardar(ruta,directorio_xml+ruta+".xml");
                
                Firmador f = new Firmador(ruta+".xml", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO", "FacturadorKey.jks");
		f.firmar();
                //ZIPEADo
                ZipOutputStream os = new ZipOutputStream(new FileOutputStream(directorio_xml+ruta+".zip"));
                ZipEntry entrada = new ZipEntry(ruta+".xml");
                os.putNextEntry(entrada);
                FileInputStream fis = new FileInputStream(directorio_xml+ruta+".xml");
                byte [] buffer = new byte[1024];
                int leido=0;
                while (0 < (leido=fis.read(buffer))){
                    os.write(buffer,0,leido);
                }
                fis.close();
                os.closeEntry();
                os.close();
                
                try{
                EnviadorReceptor er = new EnviadorReceptor(ruta+".zip", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO\\recibido");
		er.enviar();
                }catch(Exception e){
                    System.out.println(e.getStackTrace().toString());
                }
                
                System.out.println("Termino");
                
        }
}
