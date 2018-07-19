package probandoenvio;

public class Principal {
	public static void main(String[] args) {
                
		EnviadorReceptor er = new EnviadorReceptor("20601898447-01-FF01-00000054.zip", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO", "D:\\SUNAT\\sunat_archivos\\sfs\\ENVIO\\recibido");
		er.enviar();

	}
}
