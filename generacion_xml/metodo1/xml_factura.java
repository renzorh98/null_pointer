package iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

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
	public static String tipo_documento = "01";//ver
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
    public static String tipo_documento_cliente ="6"; //ver
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
	   this.ciudad="arequipa";
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
	   
	   archivo.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><Invoice ");
	   archivo.print("xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\" ");
	   archivo.print("xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" ");
	   archivo.print("xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\" ");
	   archivo.print("xmlns:ccts=\"urn:un:unece:uncefact:documentation:2\" ");
	   archivo.print("xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" ");
	   archivo.print("xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\" ");
	   archivo.print("xmlns:qdt=\"urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2\" ");
	   archivo.print("xmlns:sac=\"urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1\" ");
	   archivo.print("xmlns:schemaLocation=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2 ..\\xsd\\maindoc\\UBLPE-Invoice-1.0.xsd\" ");
	   archivo.println("xmlns:udt=\"urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2\">");
	   archivo.println("<ext:UBLExtensions>");
	   archivo.println("  <ext:UBLExtension>");
	   archivo.println("    <ext:ExtensionContent>");
	   archivo.println("      <sac:AdditionalInformation>");
   }
   public static void total_descuentos() {
	   String [] arr = new String [3];
	   arr[0]=total_opGrabadas;arr[1]=total_opInafectadas;arr[2]=total_opExoneradas;
	   archivo.println("        <sac:AdditionalMonetaryTotal>");
	   archivo.println("          <cbc:ID>2005</cbc:ID>");
	   archivo.println("          <cbc:PayableAmount currencyID=\""+tipo_moneda+"\">"+descuento_total+"</cbc:PayableAmount>");
	   archivo.println("        </sac:AdditionalMonetaryTotal>");
	   for(int i=1001; i<1004;i++) {
		   archivo.println("        <sac:AdditionalMonetaryTotal>");
		   archivo.println("          <cbc:ID>"+i+"</cbc:ID>");
		   archivo.println("          <cbc:PayableAmount currencyID=\""+tipo_moneda+"\">"+arr[i-1001]+"</cbc:PayableAmount>");
		   archivo.println("        </sac:AdditionalMonetaryTotal>");
	   }
	   archivo.println("        <sac:SUNATTransaction>");
	   archivo.println("          <cbc:ID>"+tipo_operacion+"</cbc:ID> ");
	   archivo.println("        </sac:SUNATTransaction>");
	   archivo.println("        </sac:AdditionalInformation>");
	   archivo.println("    </ext:ExtensionContent>");
	   archivo.println("  </ext:UBLExtension>");
	   archivo.println("    </ext:UBLExtensions>");
   }
 //firma electronica y final de metodo inicio_cabecera xml
   
   
   //datos de version, recibira numero de factura dada por la aplicacion, fecha de emision, tipo de documento(boleta,factura:01), moneda
   //String num_factura, String fecha_emision,String tipo_documento,String moneda,PrintWriter archivo
   public static void datos_factura() {
	   archivo.println("<cbc:UBLVersionID>"+"2.0"+"</cbc:UBLVersionID>");
	   archivo.println("<cbc:CustomizationID>"+"1.0"+"</cbc:CustomizationID>");
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
	   archivo.println("  <cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("  <cbc:Note>Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.0.5</cbc:Note>");
	   archivo.println("  <cbc:ValidatorID>"+ID_VALIDATOR+"</cbc:ValidatorID>");
	   archivo.println("  <cac:SignatoryParty>");
	   archivo.println("      <cac:PartyIdentification>");
	   archivo.println("        <cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("      </cac:PartyIdentification>");
	   archivo.println("      <cac:PartyName>");
	   archivo.println("        <cbc:Name>"+nombre+"</cbc:Name>");
	   archivo.println("      </cac:PartyName>");
	   archivo.println("      <cac:AgentParty>");
	   archivo.println("        <cac:PartyIdentification>");
	   archivo.println("          <cbc:ID>"+RUC_empresa+"</cbc:ID>");
	   archivo.println("        </cac:PartyIdentification>");
	   archivo.println("        <cac:PartyName>");
	   archivo.println("          <cbc:Name>"+nombre_registrado+"</cbc:Name>");
	   archivo.println("        </cac:PartyName>");
	   archivo.println("        <cac:PartyLegalEntity>");
	   archivo.println("          <cbc:RegistrationName>"+nombre_registrado+"</cbc:RegistrationName>");
	   archivo.println("        </cac:PartyLegalEntity>");
	   archivo.println("      </cac:AgentParty>");
	   archivo.println("    </cac:SignatoryParty>");
	   archivo.println("    <cac:DigitalSignatureAttachment>");
	   archivo.println("      <cac:ExternalReference>");
	   archivo.println("        <cbc:URI>"+URL_REFERENCIA+"</cbc:URI>");
	   archivo.println("      </cac:ExternalReference>");
	   archivo.println("    </cac:DigitalSignatureAttachment>");
	   archivo.println("</cac:Signature>");
   }
   // llevara datos referido a la identidad del emisor,RUC o DNI, tipo de documento
   //String RUC,String tipo_documento,String nombre, String code_postal, String ciudad, String code_pais,String nombre_registrado, PrintWriter archivo
   public static void datos_empresa() {
	   String tipo_documento_empresa = "6";
	   archivo.println("<cac:AccountingSupplierParty>");
	   archivo.println("  <cbc:CustomerAssignedAccountID>"+RUC_empresa+"</cbc:CustomerAssignedAccountID>");
	   archivo.println("  <cbc:AdditionalAccountID>"+tipo_documento_empresa+"</cbc:AdditionalAccountID>");
	   archivo.println("  <cac:Party>");
	   archivo.println("    <cac:PartyName>");
	   archivo.println("      <cbc:Name>"+nombre+"</cbc:Name>");
	   archivo.println("    </cac:PartyName>");
	   archivo.println("    <cac:PostalAddress>");
	   archivo.println("      <cbc:ID>"+code_postal+"</cbc:ID>");
	   archivo.println("      <cbc:StreetName>"+ciudad+"</cbc:StreetName>");
	   archivo.println("      <cac:Country>");
	   archivo.println("        <cbc:IdentificationCode>"+code_pais+"</cbc:IdentificationCode>");
	   archivo.println("      </cac:Country>");
	   archivo.println("    </cac:PostalAddress>");
	   archivo.println("    <cac:PartyLegalEntity>");
	   archivo.println("      <cbc:RegistrationName>"+nombre_registrado+"</cbc:RegistrationName>");
	   archivo.println("    </cac:PartyLegalEntity>");
	   archivo.println("  </cac:Party>");
	   archivo.println("</cac:AccountingSupplierParty>");
	   
   }
   //llevara datos referido al cliente, RUC o DNI, tipo de documento
   //String RUC, String tipo_documento, String nombre, String code_postal, PrintWriter archivo
   public static void datos_cliente() {
	   archivo.println("<cac:AccountingCustomerParty>");
	   archivo.println("  <cbc:CustomerAssignedAccountID>"+RUC_cliente+"</cbc:CustomerAssignedAccountID>");
	   archivo.println("  <cbc:AdditionalAccountID>"+tipo_documento_cliente+"</cbc:AdditionalAccountID>");
	   archivo.println("  <cac:Party>");
	   archivo.println("    <cac:PartyLegalEntity>");
	   archivo.println("      <cbc:RegistrationName>"+nombre_cliente+"</cbc:RegistrationName>");
	   archivo.println("    </cac:PartyLegalEntity>");
	   archivo.println("  </cac:Party>");
	   archivo.println("</cac:AccountingCustomerParty>");
	   archivo.println("<cac:SellerSupplierParty>");
	   archivo.println("  <cac:Party>");
	   archivo.println("    <cac:PostalAddress>");
	   archivo.println("      <cbc:AddressTypeCode>"+code_postal_cliente+"</cbc:AddressTypeCode>");
	   archivo.println("    </cac:PostalAddress>");
	   archivo.println("  </cac:Party>");
	   archivo.println("</cac:SellerSupplierParty>");
   }
   
  //montos del igv, se puede modificar para otros tributos aparte del IGV
   //Double total_igv,PrintWriter archivo
   public static void tax_total() {
	   archivo.println("<cac:TaxTotal>");
	   archivo.println("  <cbc:TaxAmount currencyID=\"PEN\">"+total_igv+"</cbc:TaxAmount>");
	   archivo.println("  <cac:TaxSubtotal>");
	   archivo.println("    <cbc:TaxAmount currencyID=\"PEN\">"+total_igv+"</cbc:TaxAmount>");
	   archivo.println("    <cac:TaxCategory>");
	   archivo.println("      <cac:TaxScheme>");
	   archivo.println("        <cbc:ID>"+1000+"</cbc:ID>");
	   archivo.println("        <cbc:Name>"+"IGV"+"</cbc:Name>");
	   archivo.println("        <cbc:TaxTypeCode>"+"VAT"+"</cbc:TaxTypeCode>");
	   archivo.println("      </cac:TaxScheme>");
	   archivo.println("    </cac:TaxCategory>");
	   archivo.println("  </cac:TaxSubtotal>");
	   archivo.println("</cac:TaxTotal>");
   }
   //recibe el total del precio
   //Double total,PrintWriter archivo
   public static void monto_total() {
	   archivo.println("<cac:LegalMonetaryTotal>");
	   archivo.println("  <cbc:AllowanceTotalAmount currencyID=\"PEN\">"+"0.00"+"</cbc:AllowanceTotalAmount>");
	   archivo.println("  <cbc:ChargeTotalAmount currencyID=\"PEN\">"+"0.00"+"</cbc:ChargeTotalAmount>");
	   archivo.println("  <cbc:PayableAmount currencyID=\"PEN\">"+total+"</cbc:PayableAmount>");
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
	   archivo.println("  <cac:AlternativeConditionPrice>");
	   archivo.println("    <cbc:PriceAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getPrecioVentaUniPorItem()+"</cbc:PriceAmount>");//Precio de venta unitario de producto o servicio
	   archivo.println("    <cbc:PriceTypeCode>"+detalles.get(i-1).getTipSistemISC()+"</cbc:PriceTypeCode>");//Tipo de sistema ISC
	   archivo.println("  </cac:AlternativeConditionPrice>");
	   archivo.println("</cac:PricingReference>");
	   archivo.println("<cac:AllowanceCharge>");
	   archivo.println("  <cbc:ChargeIndicator>false</cbc:ChargeIndicator>");
	   archivo.println("  <cbc:Amount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getDescuentos()+"</cbc:Amount>");//	Descuentos
	   archivo.println("</cac:AllowanceCharge>");
	   archivo.println("<cac:TaxTotal>");
	   archivo.println("  <cbc:TaxAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxAmount>");//Monto de IGV por ítem
	   archivo.println("  <cac:TaxSubtotal>");
	   archivo.println("    <cbc:TaxableAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxableAmount>");//Monto de IGV por ítem
	   archivo.println("    <cbc:TaxAmount currencyID=\""+tipo_moneda+"\">"+detalles.get(i-1).getMontoIGVPorItem()+"</cbc:TaxAmount>");//Monto de IGV por ítem
	   archivo.println("    <cac:TaxCategory>");
	   archivo.println("      <cbc:TaxExemptionReasonCode>"+detalles.get(i-1).getAfectIGV()+"</cbc:TaxExemptionReasonCode>");//Afectación al IGV
	   archivo.println("      <cac:TaxScheme>");
	   archivo.println("        <cbc:ID>"+1000+"</cbc:ID>"); //cambiar si es para otro que no sea IGV
	   archivo.println("        <cbc:Name>IGV</cbc:Name>"); //cambiar si es para otro que no sea IGV
	   archivo.println("        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>"); // cambiar si es para otro que no sea IGV
	   archivo.println("      </cac:TaxScheme>");
	   archivo.println("    </cac:TaxCategory>");
	   archivo.println("  </cac:TaxSubtotal>");
	   archivo.println("</cac:TaxTotal>");
	   archivo.println("<cac:Item>");
	   archivo.println("  <cbc:Description>"+detalles.get(i-1).getDescripcion()+"</cbc:Description>");//descripcion del producto
	   archivo.println("  <cac:SellersItemIdentification>");
	   archivo.println("      <cbc:ID>"+detalles.get(i-1).getCodProducto()+"</cbc:ID>");//Código de producto
	   archivo.println("  </cac:SellersItemIdentification>");
	   archivo.println("  <cac:AdditionalItemIdentification>");
	   archivo.println("      <cbc:ID/>");
	   archivo.println("  </cac:AdditionalItemIdentification>");
	   archivo.println("</cac:Item>");
	   archivo.println("<cac:Price>");
	   archivo.println("  <cbc:PriceAmount currencyID=\"PEN\">"+detalles.get(i-1).getValUnitario()+"</cbc:PriceAmount>");//Valor unitario de producto
	   archivo.println("</cac:Price>");
	   archivo.println("</cac:InvoiceLine>");
	   }
	   archivo.print("</Invoice>");
   }
   
   
}
