package Generadores;


import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.print.DocFlavor.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
class prueba_xml {
	
	public   void carga_firma(){
	
		
		
	}
	private static String convertXMLDocumentToString(String directorio,String nombre_xml ) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			//directorio donde se encuentra el archivo temporal
			InputStream iStream = new FileInputStream(new File(directorio+nombre_xml));
			//D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\20601898447-01-FA01-00000156.xml
			org.w3c.dom.Document document = documentBuilderFactory.newDocumentBuilder().parse(iStream);
			StringWriter stringWriter = new StringWriter();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "false");
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			String output = stringWriter.toString();
			System.out.println(output.replaceAll("\n|\r|\\s+", ""));
			
			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	private static Document convertStringToXMLDocument(String XMLString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(XMLString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	   public static void firmarXml(Document doc, String directorio, String nombre_xml) throws Exception {

	        System.out.println("/ INICIO.");

	        // Obtenemos las propiedades para firmar el documento.
	        String sTipoAlmacen = "jks";
	        //directorio donde se encuentra la firma .jks
	     
	      
	        String sAlmacen = "src//resources//FirmaDigital.jks";
	      
	        String sClaveAlmacen = "asd123";
	        String sClavePrivada = "asd123";
	        String sAlias = "asd123";

	        org.apache.xml.security.Init.init();

	       Constants.setSignatureSpecNSprefix("ds"); // Sino, pone por defecto como prefijo: "ns"

	        // Cargamos el almacen de claves
	        KeyStore ks  = KeyStore.getInstance(sTipoAlmacen);
	        ks.load(new FileInputStream(sAlmacen), sClaveAlmacen.toCharArray());

	        // Obtenemos la clave privada, pues la necesitaremos para encriptar.
	        PrivateKey privateKey = (PrivateKey) ks.getKey(sAlias, sClavePrivada.toCharArray());

	        File    signatureFile = new File(directorio+nombre_xml);
	        String  baseURI = signatureFile.toURI().toString();   // BaseURI para las URL Relativas.

	        // Instanciamos un objeto XMLSignature desde el Document. El algoritmo de firma ser� RSA
	        XMLSignature xmlSignature = new XMLSignature(doc, baseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);
	       Node aux=null;
	        NodeList listaNodos = doc.getElementsByTagName("ext:ExtensionContent");  
	        for(int i=0;i<listaNodos.getLength();i++){  
	           Node nodo = listaNodos.item(i);  
	           if (nodo instanceof Element){  
	              System.out.println(nodo.getTextContent());  
	              aux=nodo;
	           }  
	        } 
			
	        // A�adimos el nodo de la firma a la raiz antes de firmar.
	       
	        aux.appendChild(xmlSignature.getElement());
			//etiquetaHija.appendChild(xmlSignature.getElement());
	      
            
	       
	        Transforms transforms = new Transforms(doc);
	        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
	        transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);

	        // A�adimos lo anterior Documento / Referencia
	        // ALGO_ID_DIGEST_SHA1 = "http://www.w3.org/2000/09/xmldsig#sha1";
	        xmlSignature.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
           
	        // A�adimos el KeyInfo del certificado cuya clave privada usamos
	        X509Certificate cert = (X509Certificate) ks.getCertificate(sAlias);

	        xmlSignature.addKeyInfo(cert.getPublicKey());
	        xmlSignature.addKeyInfo(cert);

	        // Realizamos la firma
	        xmlSignature.sign(privateKey);
	        outputDocToFile(doc, signatureFile);
	        System.out.println("\\ FIN.");
	        //return doc;
	    }
	   public static void outputDocToFile(Document doc, File file) throws Exception {
	        FileOutputStream	f			   = new FileOutputStream(file);
	        TransformerFactory factory	   	   = TransformerFactory.newInstance();
	        Transformer		   transformer	   = factory.newTransformer();
	        
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        
	        transformer.transform(new DOMSource(doc), new StreamResult(f));
	 
	        f.close();
	    }
	public  void ejecutar_generacionxml(String cab1,String det2,String directorio) throws Exception {
		//creamos el xml sin la firma
		Scanner scan = new Scanner(System.in);
		//System.out.println("Ingrese ruta de cabecera");
		String cab = cab1;
		//System.out.println("Ingrese ruta de detalle");
		String det = det2;
		//System.out.println("Ingrese ruta donde se generara el xml");
		String destino = directorio;
		//D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\
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
	           xml_factura2 factura2 = new xml_factura2(cabecera,detalles);
	           factura2.setarchivo(pw);
	           factura2.setNumero_factura(codigo_factura);
	           factura2.inicio_cabecera();
	           factura2.total_descuentos();
	          //aca estaba la firma
	           factura2.datos_factura();
	           factura2.identificador_firma();
	           factura2.datos_empresa();
	           factura2.datos_cliente();
	           factura2.tax_total();
	           factura2.monto_total();
	           factura2.productos_datos();

	               
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

	
        //en este directorio se escribira el archivo temporal, luego se leera para proceder a firmar
        /*File fNuevo = new File(directorio+nombre_xml+".xml");
        //D:\\iss\\formato de archivos CAB y DET para factura electronica sunat\\pruebas\\"+
		//iniciamos la firma del documento
		Document doc;
		Document docFirm;
		String salida = convertXMLDocumentToString(directorio,nombre_xml+".xml");
		System.out.println(salida);
		doc = convertStringToXMLDocument(salida);
		//docFirm = 
		firmarXml(doc,directorio,nombre_xml+".xml*/
	
	
	}
}