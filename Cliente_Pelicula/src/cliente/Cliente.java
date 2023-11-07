package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Socket cliente = new Socket();
			InetSocketAddress direccion = new InetSocketAddress("127.0.0.1",5000);
			cliente.connect(direccion);
			System.out.println("El cliente se a conectado exitosamente al servidor");
			InputStream entrada = cliente.getInputStream();
			OutputStream salida = cliente.getOutputStream();
			
			String texto = "";
			byte[] mensaje = new byte[100];
			do {
				menu();
				texto = sc.nextLine();
				salida.write(texto.getBytes());
				System.out.println(texto);
				switch(texto.trim()) {
				case "1":
					String leer;
					System.out.println("Escribe el id");
					leer = sc.nextLine();
					salida.write(leer.getBytes());
					entrada.read(mensaje);
					String msgPelicula = new String(mensaje).trim();
					System.out.println(msgPelicula);
					break;
				case "2":
					String leerNombre;
					System.out.println("Escribe el titulo de la película");
					leerNombre = sc.nextLine();
					salida.write(leerNombre.getBytes());
					entrada.read(mensaje);
					String msgPeliculaNombre = new String(mensaje).trim();
					System.out.println(msgPeliculaNombre);
					break;
				case "3":
					String leerDirector;
					System.out.println("Escribe el nombre del director");
					leerDirector = sc.nextLine();
					salida.write(leerDirector.getBytes());
					entrada.read(mensaje);
					String msgPeliculaDirector = new String(mensaje).trim();
					System.out.println(msgPeliculaDirector);
					break;
				case "4":
					byte[] msg = new byte[100];
					String np;
					String dp;
					String pp;
					System.out.println("Escribe el titulo de la película");
					np = sc.nextLine();
					salida.write(np.getBytes());
					System.out.println("Escribe el director de la película");
					dp = sc.nextLine();
					salida.write(dp.getBytes());
					System.out.println("Escribe el precio de la película");
					pp = sc.nextLine();
					salida.write(pp.getBytes());
					entrada.read(msg);
					System.out.println("La pelicula"+new String(msg).trim()+" a sido añadida");
					break;
				case "5":
					salida.write(mensaje);
					texto = new String(mensaje).trim();
					System.out.println(texto);
					texto ="5";
					break;
				}
				System.out.println(texto);
			}while(!texto.equalsIgnoreCase("5"));
			System.out.println("Programa finalizado");
			entrada.close();
			salida.close();
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void menu() {
		System.out.println("Elige una opción del menú:");
		System.out.println("1.Acceder película por id");
		System.out.println("2.Acceder película por titulo");
		System.out.println("3.Acceder película por director");
		System.out.println("4.Añadir película");
		System.out.println("5.FIN");
	}
}
