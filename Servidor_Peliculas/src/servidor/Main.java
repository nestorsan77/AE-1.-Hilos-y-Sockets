package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try {
			System.out.println("Inicializando Servidor");
			ServerSocket servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress("127.0.0.1",5000);
			servidor.bind(direccion);
			System.out.println("Servidor inicializado con Exito");
			while(true) {
				Socket cliente = servidor.accept();
				new HiloEscuchador(cliente);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
