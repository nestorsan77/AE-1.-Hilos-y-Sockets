package controladora;

import java.rmi.RemoteException;
import java.util.ArrayList;

import clases.Pelicula;

public class PeliculaControladora {
	private ArrayList<Pelicula> peliculas;
	private static int id;
	public PeliculaControladora(){
		peliculas = new ArrayList<Pelicula>();
		peliculas.add(new Pelicula(id++, "Martin Scorsese", "Taxi Driver", 20.52));
		peliculas.add(new Pelicula(id++, "Billy Wilder", "El Apartamento", 15.16));
		peliculas.add(new Pelicula(id++, "Steven Spielberg", "La lista de Schindler", 14.99));
		peliculas.add(new Pelicula(id++, "Ingmar Bergman", "El séptimo sello", 9.99));
		peliculas.add(new Pelicula(id++, "Federico Fellini", "La dolce vita", 22.99));
	}
	
	public Pelicula RecogerPeliculaId(int id) {
		Pelicula p = new Pelicula();
		p = peliculas.get(id);
		return p;
	}
	public Pelicula RecogerPeliculaNombre(String nombre) {
		for(Pelicula p : peliculas) {
			System.out.println(p.getTitulo() + " " + nombre);
			if(p.getTitulo().equalsIgnoreCase(nombre)) {
				return p;
			}
		}
		return null;
	}
	public Pelicula RecogerPeliculaDirector(String director) {
		for(Pelicula p : peliculas) {
			System.out.println(p.getTitulo() + " " + director);
			if(p.getDirector().equalsIgnoreCase(director)) {
				return p;
			}
		}
		return null;
	}
	public Pelicula AnadirPelicula(Pelicula p) {	
		p.setId(id);
		peliculas.add(p);
		this.id++;
		return p;
	}
	
}
