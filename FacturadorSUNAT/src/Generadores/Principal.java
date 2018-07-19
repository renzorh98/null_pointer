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
public class Principal {
	public static void main(String[] args) {
		Firmador f = new Firmador("20601898447-01-FF11-00000158.xml", "C:\\Users\\Marx\\Desktop\\pruebas\\origen", "C:\\Users\\Marx\\Desktop\\pruebas\\destino", "FacturadorKey.jks");
		f.firmar();
	}
}