package controlador;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import vista.InterfaceLibro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParaInterfaceLibro extends InterfaceLibro{


	static LibroControlador lc;
	boolean bandera = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaInterfaceLibro window = new ParaInterfaceLibro();
					window.setVisible(true);
					lc=new LibroControlador(txtrLaMejorHistoria);
					lc.paginaSiguiente();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParaInterfaceLibro() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lc.paginaSiguiente();
				
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lc.paginaAnterior();
			}
		});
		
//		bandera controla si ya se ha pulsado el boton de cambio de modo
//		para poder volver atras.
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bandera) {
					txtrLaMejorHistoria.setBackground(Color.BLACK);
					txtrLaMejorHistoria.setForeground(Color.GRAY);
					bandera = false;
				} else {
					txtrLaMejorHistoria.setBackground(Color.WHITE);
					txtrLaMejorHistoria.setForeground(Color.BLACK);
					bandera=true;
				}
			}
		});
	}

}
