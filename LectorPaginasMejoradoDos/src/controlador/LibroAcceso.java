package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class LibroAcceso {

	// variables

	static File f = new File("JuegoTronos.txt");
	static FileReader flujo = null;
	static BufferedReader bufer = null;
	String cadena;
	ArrayList<Integer> posiciones = new ArrayList<Integer>();
	ArrayList<Integer> PaginasVistas = new ArrayList<Integer>();
	boolean banderaPagina = false;
	int contadorCaracteres = 0;
	int paginaActual = 0;
	JTextArea txtrLaMejorHistoria;
	boolean bandera = false;
	private String aux = new String();

	// constructor: inicia el bufer y le entra como parametro txtrLaMejorHistoria
	// el cual se usa
	// en todos los metodos

	public LibroAcceso(JTextArea txtrLaMejorHistoria) {
		this.txtrLaMejorHistoria = txtrLaMejorHistoria;
		if (f.exists())
			try {

				bufer = new BufferedReader(new InputStreamReader(new FileInputStream(f), "iso-8859-1"));

			} catch (FileNotFoundException e11) {
				e11.printStackTrace();
			} catch (IOException e11) {
				e11.printStackTrace();
			}
	}

	/**
	 * pasarPagina inicia la variable pagina que recoge los caracteres del bufer
	 * y controla que no pase de la medida de la pantalla. Ademas incrementa el
	 * numero de pagina.
	 * 
	 * @return pagina
	 */
	
	public StringBuilder pasarPagina() {
		StringBuilder pagina = new StringBuilder();

		// UltimoCaracter recoge el bufer.read cuando vale -1 la bandera que
		// controla el final
		// pasa a true por lo que ya no entra en pagina siguiente hasta que no
		// se va hacia atras
		// donde se vuelve a poner a false

		if (!bandera) {
			char caracter = 0;
			int ultimoCaracter = 0;
			txtrLaMejorHistoria.setText("");
			pagina.append(aux);

			// Este if controla que no se recoja dos veces contadorCaracteres de
			// la misma pagina
			// banderaPagina sirve para saber que la pagina cero ya se ha visto
			// y que no de
			// error en la condicion anterior del if

			if (paginaActual != 0) {
				if (paginaActual > PaginasVistas.get(PaginasVistas.size() - 1)) {
					System.out.println("pagina actual" + paginaActual);
					System.out.println("ultima pagina vista" + PaginasVistas.get(PaginasVistas.size() - 1));
					PaginasVistas.add(paginaActual);
					posiciones.add(contadorCaracteres);
				}
			} else {
				banderaPagina = true;
				PaginasVistas.add(paginaActual);
				posiciones.add(contadorCaracteres);
			}

			do {
				try {
					ultimoCaracter = bufer.read();
					caracter = (char) ultimoCaracter;
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				if (ultimoCaracter != -1) {

					pagina.append(caracter);
					txtrLaMejorHistoria.setText(String.valueOf(pagina));

				} else {

					bandera = true;
					paginaActual++;

				}

			} while ((txtrLaMejorHistoria.getPreferredSize().getHeight() < txtrLaMejorHistoria.getHeight())
					&& bandera == false);
		}
		return pagina;
	}

	/**
	 * corregirPagina solo funciona en el caso de que no se haya llegado al
	 * ultimo caracter si no es asi comprueba si el texto encaja en la pantalla
	 * o si el ultimo caracter fue un salto de linea. si sobrepasa la pantalla
	 * habra que borrar hasta el ultimo espacio entonces sumaremos los
	 * caracteres y colocaremos el texto.
	 * 
	 * @param pagina
	 */
	
	public void corregirPagina(StringBuilder pagina) {
		if (!bandera) {
			if (txtrLaMejorHistoria.getPreferredSize().getHeight() > txtrLaMejorHistoria.getHeight()
					&& pagina.charAt(pagina.length() - 1) != '\n') {

				int lastIndexOf = pagina.lastIndexOf(" ");
				aux = pagina.substring(lastIndexOf + 1);
				txtrLaMejorHistoria.setText(pagina.substring(0, lastIndexOf));
				contadorCaracteres += pagina.substring(0, lastIndexOf).length() + 1;

			} else {
				contadorCaracteres += pagina.substring(0, pagina.length()).length();
				aux = "";
			}
			paginaActual++;

		}
	}

	/**
	 * Vuelve a la pagina anterior cerrando el bufer, volviendolo a abrir y
	 * saltando a la posicion de la pagina anterior. Controlamos que no de error
	 * pasando la primera pagina controlando que siempre sea mayor de uno la
	 * pagina actual.
	 **/

	public void atras() {

		if (paginaActual > 1) {

			bandera = false;
			paginaActual--;
			int x = paginaActual;

			try {

				aux = "";
				bufer.close();
				bufer = new BufferedReader(new InputStreamReader(new FileInputStream(f), "iso-8859-1"));
				bufer.skip(posiciones.get(paginaActual - 1));

			} catch (IOException e) {
				e.printStackTrace();
			}

			// recupera el contador por el skip para que no siga sumando
			setContadorCaracteres(posiciones.get(paginaActual - 1));
			corregirPagina(pasarPagina());
			// Para que no afecte el cambio de pasarPagina a pagina Actual
			// lo guardamos en una variable
			paginaActual = x;
			System.out.println("Estamos en la pagina " + paginaActual);
		}

	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public String getAux() {
		return aux;
	}

	public void setAux(String aux) {
		this.aux = aux;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int contador) {
		this.paginaActual = paginaActual;
	}

	public int getContadorCaracteres() {
		return contadorCaracteres;
	}

	public void setContadorCaracteres(int contadorCaracteres) {
		this.contadorCaracteres = contadorCaracteres;
	}
}
