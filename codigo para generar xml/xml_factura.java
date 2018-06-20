package iss;

import java.io.PrintWriter;
import java.util.ArrayList;

public class xml_factura {
	public static PrintWriter archivo;
	//datos de cabecera
	public static String tipo_moneda;
	public static String descuento_total;
	public static String total_opGrabadas; 
	public static String total_opInafectadas;
	public static String total_opExoneradas;
	public static String tipo_operacion;
	//usadas en datos_factura
	public static  String num_factura;
	public static String fecha_emision;
	public static String tipo_documento;//ver
	public static String moneda;
	//usadas en identificador_firma
	public static String RUC_empresa;
	public static String nombre;
	public static String nombre_registrado;
	//usados en datos_empresa
    public static String code_postal; 
    public static String ciudad;
    public static String code_pais;
    //usados en datos_cliente
    public static String RUC_cliente;
    public static String tipo_documento_cliente;
    public static String nombre_cliente;
    public static String code_postal_cliente;
    //usados en tax_total
    public static String total_igv;
    //usados en monto_total
    public static String total;
    //detalle
    //usados en productos_datos
    public static ArrayList<Detalle> detalles;

	
   public xml_factura(Cabecera cab,ArrayList<Detalle> detalles) {
	  
	   this.tipo_moneda = cab.tipMoneda;
	   this.descuento_total=cab.totalDescuentos;
	   this.total_opGrabadas=cab.totalVentaGravada;
	   this.total_opInafectadas=cab.totalVentaInafectadas;
	   this.total_opExoneradas=cab.totalVentaExoneradas;
	   this.tipo_operacion=cab.tipOperacion;
	   this.fecha_emision=cab.fechaEmision;
	   this.moneda=cab.tipMoneda;
	   this.RUC_empresa = "20601898447";
	   this.nombre = "Empresas de condor del sur";
	   this.nombre_registrado ="Empresas del sur";
	   this.code_postal="123464";
	   this.ciudad="Arequipa";
	   this.code_pais="PER";
	   this.RUC_cliente=cab.NumDocIdeUsuario;
	   this.nombre_cliente=cab.apeNomUsario;
	   this.code_postal_cliente="0";
	   this.total_igv=cab.sumIGV;
	   this.total=cab.importeTotalVenta;
	   
	   
	   
	   this.detalles =detalles;
	   
	   
   }
   public void setarchivo(PrintWriter archivo) {
	   this.archivo = archivo;
   }
   public void setNumero_factura(String numero) {
	   this.num_factura=numero;
   }
   public static void inicio_cabecera() {
	   
	   archivo.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><Invoice");
	   archivo.println("xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\"");
	   archivo.println("xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\"");
	   archivo.println("xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\"");
	   archivo.println("xmlns:ccts=\"urn:un:unece:uncefact:documentation:2\"");
	   archivo.println("xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"");
	   archivo.println("xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\"");
	   archivo.println("xmlns:qdt=\"urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2\"");
	   archivo.println("xmlns:sac=\"urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1\"");
	   archivo.println("xmlns:schemaLocation=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2 ..\\xsd\\maindoc\\UBLPE-Invoice-1.0.xsd\"");
	   archivo.println("xmlns:udt=\"urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2\">");
	   archivo.println("<ext:UBLExtensions>");
	   archivo.println("<ext:UBLExtension>");
	   archivo.println("<sac:AdditionalInformation>");
   }
   public static void total_descuentos() {
	   String [] arr = new String [3];
	   arr[0]=total_opGrabadas;arr[1]=total_opInafectadas;arr[2]=total_opExoneradas;
	   archivo.println("<sac:AdditionalMonetaryTotal>");
	   archivo.println("<cbc:ID>2005</cbc:ID>");
	   archivo.println("<cbc:PayableAmount currencyID=\""+tipo_moneda+"PEN\">"+descuento_total+"</cbc:PayableAmount>");
	   archivo.println("</sac:AdditionalMonetaryTotal>");
	   for(int i=1001; i<1004;i++) {
		   archivo.println("<sac:AdditionalMonetaryTotal>");
		   archivo.println("<cbc:ID>"+i+"</cbc:ID>");
		   archivo.println("<cbc:PayableAmount currencyID=\""+tipo_moneda+"PEN\">"+arr[i-1001]+"</cbc:PayableAmount>");
		   archivo.println("</sac:AdditionalMonetaryTotal>");
	   }
	   archivo.println("<sac:SUNATTransaction>");
	   archivo.println("<cbc:ID>"+tipo_operacion+"</cbc:ID> ");
	   archivo.println("</sac:SUNATTransaction>");
	   archivo.println("</sac:AdditionalInformation>");
	   archivo.println("</ext:ExtensionContent>");
	   archivo.println("</ext:UBLExtension>");
   }
 //firma electronica y final de metodo inicio_cabecera xml
   
   public static void firma() {
	   archivo.println("<ext:UBLExtension>");
	   archivo.println("<ext:ExtensionContent><ds:Signature Id=\"SignSUNAT\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><ds:Reference URI=\"\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><ds:DigestValue>5kER8VGoROKvemqxEUKnE0Xzmg4=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>xXqp5oDwfdtAe7/+F0G2SAAT254RVTsqFwNWegUuLEBgzb6VZ8ReKb9oj4i0cgohoH+TSAZ0/z5u\r\n" + 
	   		"");
	   archivo.println("CSG/+E25w92ivWJlIiFv0V6CG3L/zqRYf8MfiyoiaWf8lshc5oPP0XmaQgwmHug6dOVDchWDzLT2");
	   archivo.println("sPt6JE76qguDk+79YgsqPIKtLnE61ThpGqJwRNU38bct4aE/s3/b4MwWY6sHfMBnmF/1kc4ACbIX");
	   archivo.println("6+PWGwe9LVFUbcIGkaNUB/CGzkH6UWOyWiLj9UKAEJwMS+3Ummz/tT6Owh0x6eBWoIPBL6AqKu5Y");
	   archivo.println("S06mhL5PhjVyvfETq/Etm2bDnA4VLr5MLw64Mw==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIE8jCCA9qgAwIBAgIDALUkMA0GCSqGSIb3DQEBBQUAMIIBDTEbMBkGCgmSJomT8ixkARkWC0xM");
	   archivo.println("QU1BLlBFIFNBMQswCQYDVQQGEwJQRTENMAsGA1UECAwETElNQTENMAsGA1UEBwwETElNQTEYMBYG");
	   archivo.println("A1UECgwPVFUgRU1QUkVTQSBTLkEuMUUwQwYDVQQLDDxETkkgOTk5OTk5OSBSVUMgMjA2MDE4OTg0");
	   archivo.println("NDcgLSBDRVJUSUZJQ0FETyBQQVJBIERFTU9TVFJBQ0nDk04xRDBCBgNVBAMMO05PTUJSRSBSRVBS");
	   archivo.println("RVNFTlRBTlRFIExFR0FMIC0gQ0VSVElGSUNBRE8gUEFSQSBERU1PU1RSQUNJw5NOMRwwGgYJKoZI");
	   archivo.println("hvcNAQkBFg1kZW1vQGxsYW1hLnBlMB4XDTE4MDYxMTAwMTUyMVoXDTIwMDYxMDAwMTUyMVowggEN");
	   archivo.println("MRswGQYKCZImiZPyLGQBGRYLTExBTUEuUEUgU0ExCzAJBgNVBAYTAlBFMQ0wCwYDVQQIDARMSU1B");
	   archivo.println("MQ0wCwYDVQQHDARMSU1BMRgwFgYDVQQKDA9UVSBFTVBSRVNBIFMuQS4xRTBDBgNVBAsMPEROSSA5");
	   archivo.println("OTk5OTk5IFJVQyAyMDYwMTg5ODQ0NyAtIENFUlRJRklDQURPIFBBUkEgREVNT1NUUkFDScOTTjFE");
	   archivo.println("MEIGA1UEAww7Tk9NQlJFIFJFUFJFU0VOVEFOVEUgTEVHQUwgLSBDRVJUSUZJQ0FETyBQQVJBIERF");
	   archivo.println("TU9TVFJBQ0nDk04xHDAaBgkqhkiG9w0BCQEWDWRlbW9AbGxhbWEucGUwggEiMA0GCSqGSIb3DQEB");
	   archivo.println("AQUAA4IBDwAwggEKAoIBAQDKu8D80JDXsU2xolZcdx+KzEigm+jwmMrK0XtB4Y+nWos5D/x1xNoT");
	   archivo.println("nsIPKuooGEoGfEQ9VOKcFshMO1zt+jhzZRsKCSINczSqJ8770hs9Q84hXZWfRE1gfkWPz8pmxpK+");
	   archivo.println("xxGu1a/NS537O7nS6UBctH+HyeCHX+ftUhkYaQiEhTe+0aTQWv/2CyQCBvgY+mplAYoA9k33wye1");
	   archivo.println("eeJhAgwtcIsHMA99O41yZk1QbJIlUHxl+nfSEYSePQaqMODaxIOytEZzOiIuWIZHYyXTeexgrFJH");
	   archivo.println("zxbrkYJUOUtjoTz2/sjt/tJm5Cwo34S4wrc03BGL3NKD4o5KCSyeWZPQBw/fAgMBAAGjVzBVMB0G");
	   archivo.println("A1UdDgQWBBRm5G81qj6A440FOd4XkIz0qW/2lzAfBgNVHSMEGDAWgBRm5G81qj6A440FOd4XkIz0");
	   archivo.println("qW/2lzATBgNVHSUEDDAKBggrBgEFBQcDATANBgkqhkiG9w0BAQUFAAOCAQEAICShQ8KtMrHichmi");
	   archivo.println("zpGJKR4V4i4XNhPCvCXBcS1Wb1RHh06Yoe1xwLx9ui4ZzXkciwCTKkLEX2JmJqjp8+UgVSr3MKhh");
	   archivo.println("GuPRTMc5V8FyFiWYSKYET7wCBQ8aLzEptDpx6rTAhXpU7FGIyUl1DIPX1DjiT+qRgpGnRYPDRI5k");
	   archivo.println("R09BDzupb4s/RaYfotye4K3/sy2yuMa1M7gOgtRkgpfc+tsBUCSGdU694HGRf0BeZ/dWdld28ZM9");
	   archivo.println("W0LADVg9B86IGnMU/Al53fZDDgxoXnIDyZPBqXyFLmxaculn+f5Fjri5Zkyvpy2focwPpk36syhn");
	   archivo.println("grLdHWbgGsvLCY0R6WYELA==</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ext:ExtensionContent>");
	   archivo.println("</ext:UBLExtension>");
	   archivo.println("</ext:UBLExtensions> ");
   }
   //datos de version, recibira numero de factura dada por la aplicacion, fecha de emision, tipo de documento(boleta,factura:01), moneda
   //String num_factura, String fecha_emision,String tipo_documento,String moneda,PrintWriter archivo
   public static void datos_factura() {
	   archivo.println("<cbc:UBLVersionID>"+2.0+"</cbc:UBLVersionID>");
	   archivo.println("<cbc:CustomizationID>"+1.0+"</cbc:CustomizationID>");
	   archivo.println("<cbc:ID>"+num_factura+"</cbc:ID>");
	   archivo.println("<cbc:IssueDate>"+fecha_emision+"</cbc:IssueDate>");
	   archivo.println("<cbc:InvoiceTypeCode>"+tipo_documento+"</cbc:InvoiceTypeCode>");
	   archivo.println("<cbc:DocumentCurrencyCode>"+moneda+"</cbc:DocumentCurrencyCode>");
   }
   //Identificara al firmante, RUC, nombre de empresa
   //String RUC,String nombre,String nombre_registrado, PrintWriter archivo
   public static void identificador_firma() {
	  
	   String URL_REFERENCIA = "SIGN";
	   String ID_VALIDATOR = "228055";
	   archivo.println("<cac:Signature>");
	   archivo.println("<cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("<cbc:Note>Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.0.5</cbc:Note>");
	   archivo.println("<cbc:ValidatorID>"+ID_VALIDATOR+"</cbc:ValidatorID>");
	   archivo.println("<cac:SignatoryParty>");
	   archivo.println("<cac:PartyIdentification>");
	   archivo.println("<cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("</cac:PartyIdentification>");
	   archivo.println("<cac:PartyName>");
	   archivo.println("<cbc:Name>"+nombre+"</cbc:Name>");
	   archivo.println("</cac:PartyName>");
	   archivo.println("<cac:AgentParty>");
	   archivo.println("<cac:PartyIdentification>");
	   archivo.println("<cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("</cac:PartyIdentification>");
	   archivo.println("<cac:PartyName>");
	   archivo.println("<cbc:Name>"+nombre_registrado+"</cbc:Name>");
	   archivo.println("</cac:PartyName>");
	   archivo.println("<cac:PartyLegalEntity>");
	   archivo.println("<cbc:RegistrationName>"+nombre_registrado+"</cbc:RegistrationName>");
	   archivo.println("</cac:PartyLegalEntity>");
	   archivo.println("</cac:AgentParty>");
	   archivo.println("</cac:SignatoryParty>");
	   archivo.println("<cac:DigitalSignatureAttachment>");
	   archivo.println("<cac:ExternalReference>");
	   archivo.println("<cbc:URI>"+URL_REFERENCIA+"</cbc:URI>");
	   archivo.println("</cac:ExternalReference>");
	   archivo.println("</cac:DigitalSignatureAttachment>");
	   archivo.println("</cac:Signature>");
   }
   // llevara datos referido a la identidad del emisor,RUC o DNI, tipo de documento
   //String RUC,String tipo_documento,String nombre, String code_postal, String ciudad, String code_pais,String nombre_registrado, PrintWriter archivo
   public static void datos_empresa() {
	   String tipo_documento_empresa = "6";
	   archivo.println("<cac:AccountingSupplierParty>");
	   archivo.println("<cbc:CustomerAssignedAccountID>"+RUC_empresa+"</cbc:CustomerAssignedAccountID>");
	   archivo.println("<cbc:AdditionalAccountID>"+tipo_documento_empresa+"</cbc:AdditionalAccountID>");
	   archivo.println("<cac:Party>");
	   archivo.println("<cac:PartyName>");
	   archivo.println("<cbc:Name>"+nombre+"</cbc:Name>");
	   archivo.println("</cac:PartyName>");
	   archivo.println("<cac:PostalAddress>");
	   archivo.println("<cbc:ID>"+code_postal+"</cbc:ID>");
	   archivo.println("<cbc:StreetName>"+ciudad+"</cbc:StreetName>");
	   archivo.println("<cac:Country>");
	   archivo.println("<cbc:IdentificationCode>"+code_pais+"</cbc:IdentificationCode>");
	   archivo.println("</cac:Country>");
	   archivo.println("</cac:PostalAddress>");
	   archivo.println("<cac:PartyLegalEntity>");
	   archivo.println("<cbc:RegistrationName>"+nombre_registrado+"</cbc:RegistrationName>");
	   archivo.println("</cac:PartyLegalEntity>");
	   archivo.println("</cac:Party>");
	   archivo.println("</cac:AccountingSupplierParty>");
	   
   }
   //llevara datos referido al cliente, RUC o DNI, tipo de documento
   //String RUC, String tipo_documento, String nombre, String code_postal, PrintWriter archivo
   public static void datos_cliente() {
	   archivo.println("<cac:AccountingCustomerParty>");
	   archivo.println("<cbc:CustomerAssignedAccountID>"+RUC_cliente+"</cbc:CustomerAssignedAccountID>");
	   archivo.println("<cbc:AdditionalAccountID>"+tipo_documento_cliente+"/cbc:AdditionalAccountID>");
	   archivo.println("<cac:Party>");
	   archivo.println("<cac:PartyLegalEntity>");
	   archivo.println("<cbc:RegistrationName>"+nombre_cliente+"</cbc:RegistrationName>");
	   archivo.println("</cac:PartyLegalEntity>");
	   archivo.println("</cac:Party>");
	   archivo.println("</cac:AccountingCustomerParty>");
	   archivo.println("<cac:SellerSupplierParty>");
	   archivo.println("<cac:Party>");
	   archivo.println("<cac:PostalAddress>");
	   archivo.println("<cbc:AddressTypeCode>"+code_postal_cliente+"</cbc:AddressTypeCode>");
	   archivo.println("</cac:PostalAddress>");
	   archivo.println("</cac:Party>");
	   archivo.println("</cac:SellerSupplierParty>");
   }
   
  //montos del igv, se puede modificar para otros tributos aparte del IGV
   //Double total_igv,PrintWriter archivo
   public static void tax_total() {
	   archivo.println("<cac:TaxTotal>");
	   archivo.println("<cbc:TaxAmount currencyID=\"PEN\">"+total_igv+"</cbc:TaxAmount>");
	   archivo.println("<cac:TaxSubtotal>");
	   archivo.println("<cbc:TaxAmount currencyID=\"PEN\">"+total_igv+"</cbc:TaxAmount>");
	   archivo.println("<cac:TaxCategory>");
	   archivo.println("<cac:TaxScheme>");
	   archivo.println("<cbc:ID>"+1000+"</cbc:ID>");
	   archivo.println("<cbc:ID>"+"IGV"+"</cbc:ID>");
	   archivo.println("<cbc:TaxTypeCode>"+"VAT"+"</cbc:TaxTypeCode>");
	   archivo.println("</cac:TaxScheme>");
	   archivo.println("</cac:TaxCategory>");
	   archivo.println("</cac:TaxSubtotal>");
	   archivo.println("</cac:TaxTotal>");
   }
   //recibe el total del precio
   //Double total,PrintWriter archivo
   public static void monto_total() {
	   archivo.println("<cac:LegalMonetaryTotal>");
	   archivo.println(" <cbc:PayableAmount currencyID=\"PEN\">"+total+"</cbc:PayableAmount>");
	   archivo.println("</cac:LegalMonetaryTotal>");
   }
   //Recibe una lista con los productos y sus respectivos datos
   //productos_factura []productos,PrintWriter archivo
   public static void productos_datos() {
	   for(int i=1;i<=detalles.size();i++) {
	   archivo.println("<cac:InvoiceLine>");
	   archivo.println("<cbc:ID>"+i+"</cbc:ID>");
	   archivo.println("<cbc:InvoicedQuantity unitCode=\""+detalles.get(i-1).getCodUniMedItem()+"\">"+detalles.get(i-1).getCantUniPorItem()+"</cbc:InvoicedQuantity>");//cantidad de productos
	   archivo.println("<cbc:LineExtensionAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getValorVentaPorItem()+"</cbc:LineExtensionAmount>");//Valor total de linea
	   archivo.println("<cac:PricingReference>");
	   archivo.println("<cac:AlternativeConditionPrice>");
	   archivo.println("<cbc:PriceAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getPrecioVentaUniPorItem()+"</cbc:PriceAmount>");//Precio de venta unitario de producto o servicio
	   archivo.println("<cbc:PriceTypeCode>"+detalles.get(i-1).getTipSistemISC()+"</cbc:PriceTypeCode>");//Tipo de sistema ISC
	   archivo.println("</cac:AlternativeConditionPrice>");
	   archivo.println("</cac:PricingReference>");
	   archivo.println("<cac:AllowanceCharge>");
	   archivo.println("<cbc:ChargeIndicator>false</cbc:ChargeIndicator>");
	   archivo.println("<cbc:Amount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getDescuentos()+"</cbc:Amount>");//	Descuentos
	   archivo.println("</cac:AllowanceCharge>");
	   archivo.println("<cac:TaxTotal>");
	   archivo.println("<cbc:TaxAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxAmount>");//Monto de IGV por ítem
	   archivo.println("<cac:TaxSubtotal>");
	   archivo.println("<cbc:TaxableAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxableAmount>");//Monto de IGV por ítem
	   archivo.println(" <cbc:TaxAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxAmount>");//Monto de IGV por ítem
	   archivo.println("<cac:TaxCategory>");
	   archivo.println("<cbc:TaxExemptionReasonCode>"+detalles.get(i-1).getAfectIGV()+"</cbc:TaxExemptionReasonCode>");//Afectación al IGV
	   archivo.println("<cac:TaxScheme>");
	   archivo.println("<cbc:ID>"+1000+"</cbc:ID>"); //cambiar si es para otro que no sea IGV
	   archivo.println("<cbc:Name>IGV</cbc:Name>"); //cambiar si es para otro que no sea IGV
	   archivo.println("<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>"); // cambiar si es para otro que no sea IGV
	   archivo.println("</cac:TaxScheme>");
	   archivo.println("</cac:TaxCategory>");
	   archivo.println("</cac:TaxSubtotal>");
	   archivo.println("</cac:TaxTotal>");
	   archivo.println("<cac:Item>");
	   archivo.println("<cbc:Description>"+detalles.get(i-1).getDescripcion()+"</cbc:Description>");//descripcion del producto
	   archivo.println("<cac:SellersItemIdentification>");
	   archivo.println("<cbc:ID>"+detalles.get(i-1).getCodProducto()+"</cbc:ID>");//Código de producto
	   archivo.println("</cac:SellersItemIdentification>");
	   archivo.println("<cac:AdditionalItemIdentification>");
	   archivo.println("<cbc:ID/>");
	   archivo.println("</cac:AdditionalItemIdentification>");
	   archivo.println("</cac:Item>");
	   archivo.println("<cac:Price>");
	   archivo.println("<cbc:PriceAmount currencyID=\"PEN\">"+detalles.get(i-1).getValUnitario()+"</cbc:PriceAmount>");//Valor unitario de producto
	   archivo.println("</cac:Price>");
	   archivo.println("</cac:InvoiceLine>");
	   }
	   archivo.println("</Invoice>");
   }
   
   
}
