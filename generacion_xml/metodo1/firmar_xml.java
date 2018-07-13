package iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class firmar_xml {
	
		   public static String firma="  </ext:UBLExtension>\n"
		   		+ "\n"
				+ "        <ext:UBLExtension>\n"
		   		+ "            <ext:ExtensionContent><ds:Signature Id=\"SignSUNAT\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><ds:Reference URI=\"\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><ds:DigestValue>5kER8VGoROKvemqxEUKnE0Xzmg4=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>xXqp5oDwfdtAe7/+F0G2SAAT254RVTsqFwNWegUuLEBgzb6VZ8ReKb9oj4i0cgohoH+TSAZ0/z5u\n"
		   		+ "CSG/+E25w92ivWJlIiFv0V6CG3L/zqRYf8MfiyoiaWf8lshc5oPP0XmaQgwmHug6dOVDchWDzLT2\n"
		   		+ "sPt6JE76qguDk+79YgsqPIKtLnE61ThpGqJwRNU38bct4aE/s3/b4MwWY6sHfMBnmF/1kc4ACbIX\n"
		   		+ "6+PWGwe9LVFUbcIGkaNUB/CGzkH6UWOyWiLj9UKAEJwMS+3Ummz/tT6Owh0x6eBWoIPBL6AqKu5Y\n"
		   		+ "S06mhL5PhjVyvfETq/Etm2bDnA4VLr5MLw64Mw==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIE8jCCA9qgAwIBAgIDALUkMA0GCSqGSIb3DQEBBQUAMIIBDTEbMBkGCgmSJomT8ixkARkWC0xM\n"
		   		+ "QU1BLlBFIFNBMQswCQYDVQQGEwJQRTENMAsGA1UECAwETElNQTENMAsGA1UEBwwETElNQTEYMBYG\n"
		   		+ "A1UECgwPVFUgRU1QUkVTQSBTLkEuMUUwQwYDVQQLDDxETkkgOTk5OTk5OSBSVUMgMjA2MDE4OTg0\n"
		   		+ "NDcgLSBDRVJUSUZJQ0FETyBQQVJBIERFTU9TVFJBQ0nDk04xRDBCBgNVBAMMO05PTUJSRSBSRVBS\n"
		   		+ "RVNFTlRBTlRFIExFR0FMIC0gQ0VSVElGSUNBRE8gUEFSQSBERU1PU1RSQUNJw5NOMRwwGgYJKoZI\n"
		   		+ "hvcNAQkBFg1kZW1vQGxsYW1hLnBlMB4XDTE4MDYxMTAwMTUyMVoXDTIwMDYxMDAwMTUyMVowggEN\n"
		   		+ "MRswGQYKCZImiZPyLGQBGRYLTExBTUEuUEUgU0ExCzAJBgNVBAYTAlBFMQ0wCwYDVQQIDARMSU1B\n"
		   		+ "MQ0wCwYDVQQHDARMSU1BMRgwFgYDVQQKDA9UVSBFTVBSRVNBIFMuQS4xRTBDBgNVBAsMPEROSSA5\n"
		   		+ "OTk5OTk5IFJVQyAyMDYwMTg5ODQ0NyAtIENFUlRJRklDQURPIFBBUkEgREVNT1NUUkFDScOTTjFE\n"
		   		+ "MEIGA1UEAww7Tk9NQlJFIFJFUFJFU0VOVEFOVEUgTEVHQUwgLSBDRVJUSUZJQ0FETyBQQVJBIERF\n"
		   		+ "TU9TVFJBQ0nDk04xHDAaBgkqhkiG9w0BCQEWDWRlbW9AbGxhbWEucGUwggEiMA0GCSqGSIb3DQEB\n"
		   		+ "AQUAA4IBDwAwggEKAoIBAQDKu8D80JDXsU2xolZcdx+KzEigm+jwmMrK0XtB4Y+nWos5D/x1xNoT\n"
		   		+ "nsIPKuooGEoGfEQ9VOKcFshMO1zt+jhzZRsKCSINczSqJ8770hs9Q84hXZWfRE1gfkWPz8pmxpK+\n"
		   		+ "xxGu1a/NS537O7nS6UBctH+HyeCHX+ftUhkYaQiEhTe+0aTQWv/2CyQCBvgY+mplAYoA9k33wye1\n"
		   		+ "eeJhAgwtcIsHMA99O41yZk1QbJIlUHxl+nfSEYSePQaqMODaxIOytEZzOiIuWIZHYyXTeexgrFJH\n"
		   		+ "zxbrkYJUOUtjoTz2/sjt/tJm5Cwo34S4wrc03BGL3NKD4o5KCSyeWZPQBw/fAgMBAAGjVzBVMB0G\n"
		   		+ "A1UdDgQWBBRm5G81qj6A440FOd4XkIz0qW/2lzAfBgNVHSMEGDAWgBRm5G81qj6A440FOd4XkIz0\n"
		   		+ "qW/2lzATBgNVHSUEDDAKBggrBgEFBQcDATANBgkqhkiG9w0BAQUFAAOCAQEAICShQ8KtMrHichmi\n"
		   		+ "zpGJKR4V4i4XNhPCvCXBcS1Wb1RHh06Yoe1xwLx9ui4ZzXkciwCTKkLEX2JmJqjp8+UgVSr3MKhh\n"
		   		+ "GuPRTMc5V8FyFiWYSKYET7wCBQ8aLzEptDpx6rTAhXpU7FGIyUl1DIPX1DjiT+qRgpGnRYPDRI5k\n"
		   		+ "R09BDzupb4s/RaYfotye4K3/sy2yuMa1M7gOgtRkgpfc+tsBUCSGdU694HGRf0BeZ/dWdld28ZM9\n"
		   		+ "W0LADVg9B86IGnMU/Al53fZDDgxoXnIDyZPBqXyFLmxaculn+f5Fjri5Zkyvpy2focwPpk36syhn\n"
		   		+ "grLdHWbgGsvLCY0R6WYELA==</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ext:ExtensionContent>\n"
		   		+ "        </ext:UBLExtension>";
	   
	   public void Escribir(File fFichero,String cadena)
	   {
	       // Declaramos un buffer de escritura
	       BufferedWriter bw;

	       try
	       {
	           // Comprobamos si el archivo no existe y si es asi creamos uno nuevo.
	        if(!fFichero.exists())
	        {
	            fFichero.createNewFile();
	        }

	          // Luego de haber creado el archivo procedemos a escribir dentro de el.
	           bw = new BufferedWriter(new FileWriter(fFichero,true));
	           bw.write(cadena+"\n");
	           bw.close();

	       }catch(Exception e)
	       {
	           System.out.println(e);
	       }

	   }
	   public void ejecutar(File fichero) {
		   this.modificar(fichero, "</ext:UBLExtension>", firma);
	   }
	   public void borrar (File Ffichero)
	   {
	       try
	       {
	          // Comprovamos si el fichero existe  de ser así procedemos a borrar el archivo
	           if(Ffichero.exists())
	           {
	               Ffichero.delete();
	               System.out.println("Ficherro Borrado");
	           }

	       }catch(Exception e)
	       {
	           System.out.println(e);
	       }
	   }
	   public void modificar(File fAntiguo,String aCadena,String nCadena)
	   {
		   
	  
		   int contador =0;
	       Random numaleatorio = new Random(3816L);
	       String nFnuevo = fAntiguo.getParent()+"/auxiliar"+String.valueOf(Math.abs(numaleatorio.nextInt()))+".txt";

	    // Creo un nuevo archivo
	       File fNuevo= new File(nFnuevo);
	       // Declaro un nuevo buffer de lectura
	       BufferedReader br;
	       try
	       {
	        

	           if(fAntiguo.exists())
	           {
	               br = new BufferedReader(new FileReader(fAntiguo));

	               String linea;

	               while((linea=br.readLine()) != null)
	               {
	                   if(linea.equals(aCadena)&&contador==0)
	                   {
	                       Escribir(fNuevo,nCadena);
	                       contador++;

	                   }
	                   else
	                   {
	                       Escribir(fNuevo,linea);
	                   }
	               }

	             // Cierro el buffer de lectura
	               br.close();

	               // Capturo el nombre del fichero antiguo
	               String nAntiguo = fAntiguo.getName();
	               // Borro el fichero antiguo
	               borrar(fAntiguo);
	               //Renombro el fichero auxiliar con el nombre del fichero antiguo
	               fNuevo.renameTo(fAntiguo);




	           }
	           else
	           {
	               System.out.println("Fichero no Existe");
	           }

	       }catch(Exception e)
	       {
	           System.out.println(e);
	       }
	   }
}
