package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import clases.Pelicula;
import controladora.PeliculaControladora;

public class HiloEscuchador implements Runnable{
	private Thread hilo;
	private Socket cliente;
	private static int numCliente = 0;
	
	public HiloEscuchador(Socket escuchaCliente) {
		numCliente++;
		this.hilo = new Thread(this, "Cliente "+numCliente);
		this.cliente = escuchaCliente;
		System.out.println("cliente "+numCliente+" inicializado");
		hilo.start();
	}
	
	@Override
	public void run() {
	try {
			InputStream entrada = cliente.getInputStream();
			OutputStream salida = cliente.getOutputStream();
			PeliculaControladora c = new PeliculaControladora();
			String texto = "";
			byte[] mensaje = new byte[100];
			do {
				System.out.println("Entrada de primer mensaje");
				entrada.read(mensaje);
				texto = new String(mensaje).trim();
				System.out.println(texto);
				switch(texto.trim()) {
				case "1":
					System.out.println("Acceder peliculas por id");
					byte[] id = new byte[100];
					String ID;
					entrada.read(id);
					ID = new String(id).trim();
					int numId = Integer.parseInt(ID);
					System.out.println(numId);
					Pelicula p = new Pelicula();
					p = c.RecogerPeliculaId(numId);
					System.out.println(p.toString());
					salida.write(p.toString().getBytes());
					break;
				case "2":
					System.out.println("Acceder a peliculas por titulo");
					byte[] nombre = new byte[100];
					String Nombre;
					entrada.read(nombre);
					Nombre = new String(nombre).trim();;
					System.out.println(Nombre);
					Pelicula pnombre = new Pelicula();
					pnombre = c.RecogerPeliculaNombre(Nombre);
					System.out.println(pnombre.toString());
					salida.write(pnombre.toString().getBytes());
					break;
				case "3":
					System.out.println("Acceder a las peliculas por director");
					byte[] titulo = new byte[100];
					String Titulo;
					entrada.read(titulo);
					Titulo = new String(titulo).trim();;
					System.out.println(Titulo);
					Pelicula ptitulo = new Pelicula();
					PeliculaControladora ctitulo = new PeliculaControladora();
					ptitulo = c.RecogerPeliculaDirector(Titulo);
					System.out.println(ptitulo.toString());
					salida.write(ptitulo.toString().getBytes());
					break;
				case "4":
					System.out.println("Añadir pelicula al servidor");
					byte[] msg = new byte[100];
					String Add_titulo;
					String Add_Director;
					String Add_precio;
					msg = new byte[100];
					entrada.read(msg);
					Add_titulo = new String(msg).trim();
					msg = new byte[100];
					entrada.read(msg);
					Add_Director = new String(msg).trim();
					msg = new byte[100];
					entrada.read(msg);
					Add_precio = new String(msg).trim();
					
					System.out.println(Add_precio);
					double precio = Double.parseDouble(Add_precio);
					
					Pelicula anadirPelicula = new Pelicula();
					anadirPelicula.setTitulo(Add_titulo);
					anadirPelicula.setPrecio(precio);
					anadirPelicula.setDirector(Add_Director);
					
					anadirPelicula = c.AnadirPelicula(anadirPelicula);
					
					salida.write(anadirPelicula.toString().getBytes());
					
					break;
				case "5":
					System.out.println("Salir de la aplicación");
					salida.write("Aplicación acabada".getBytes());
					break;
				}
			
			}while(!texto.equalsIgnoreCase("5"));
			System.out.println("La aplicacion a sido finalizada");
			entrada.close();
			salida.close();
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
