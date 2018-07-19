package probandoenvio;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService;
import pe.gob.sunat.servicio.registro.comppago.factura.gem.service.BillService_Service;

/**
 *
 * @author Marx
 */
public class EnviadorReceptor {
    String nombreZip;
    String pathOrigenCarpeta;
    String pathDestinoCarpeta;
    

    public EnviadorReceptor(String nombreZip, String pathOrigenCarpeta, String pathDestinoCarpeta) {
        this.nombreZip = nombreZip;
        this.pathOrigenCarpeta = pathOrigenCarpeta;
        this.pathDestinoCarpeta = pathDestinoCarpeta;
        System.out.println(pathOrigenCarpeta + "\\" + nombreZip);
        System.out.println(pathDestinoCarpeta + "\\" + nombreZip);
        
    }
    
    public void enviar(){
    	System.out.println(nombreZip + ", " + pathOrigenCarpeta + ", " + pathDestinoCarpeta);
        BillService_Service b = new BillService_Service();
        BillService s = b.getBillServicePort();
        try{
            //leo archivo zip con xml dentro
            DataSource sou = new FileDataSource(pathOrigenCarpeta + "\\" + nombreZip);
            DataHandler han = new DataHandler(sou);
            //envio zip y recibo respuesta en bytes
            byte[] respuesta = s.sendBill(nombreZip, han, null);
            //escribo los bytes en un archivo zip
            FileOutputStream escritor = new FileOutputStream(pathDestinoCarpeta + "\\" + nombreZip);
            escritor.write(respuesta);
            escritor.close();
            
        }catch(Exception e)    {
            System.err.println(e.getMessage());
        }
    }
    
}
