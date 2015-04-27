package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class LibroControlador {
	JTextArea txtrLaMejorHistoria;
	LibroAcceso la;

	public LibroControlador(JTextArea txtrLaMejorHistoria) {
		this.txtrLaMejorHistoria = txtrLaMejorHistoria;
		la = new LibroAcceso(txtrLaMejorHistoria);
	}

	public void paginaAnterior() {
		la.atras();

	}

	public void paginaSiguiente() {

		la.corregirPagina(la.pasarPagina());

	}

}
