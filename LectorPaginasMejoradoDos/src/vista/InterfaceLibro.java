package vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.IconUIResource;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;

public class InterfaceLibro extends JFrame {

	protected JPanel contentPane;
	protected JButton adelante;
	protected static JTextArea txtrLaMejorHistoria;
	protected JButton atras;
	protected JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceLibro frame = new InterfaceLibro();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceLibro() {
		setResizable(false);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		txtrLaMejorHistoria = new JTextArea();
		txtrLaMejorHistoria.setEditable(false);
		txtrLaMejorHistoria.setBackground(Color.WHITE);
		txtrLaMejorHistoria.setFont(new Font("Monospaced", Font.PLAIN, 12));
		panel.add(txtrLaMejorHistoria);
		// Hace un salto de linea si detecta que llega al borde del area
		txtrLaMejorHistoria.setLineWrap(true);
		// detecta si es una palabra o no
		txtrLaMejorHistoria.setWrapStyleWord(true);
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
				atras = new JButton("atras");
				panel_1.add(atras);
		
				adelante = new JButton("adelante");
				panel_1.add(adelante);
				
				button = new JButton(new ImageIcon("media/moon4.png"));
				button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel_1.add(button);
		
	
	}
}
