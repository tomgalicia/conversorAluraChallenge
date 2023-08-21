package com.conversor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MenuPrincipal {
		private static ConversorMoneda objconmon=new ConversorMoneda();
		private static ConversorTemperatura objcontem=new ConversorTemperatura();
	public static void main(String[] args) throws IOException {
		Boolean flag=true;
		Integer opcion=0;
		String[] choices = { "Conversor de Moneda",
	    		             "Conversor de Temperatura"
							};
		File file = new File("aluralogo.jpg");
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        
	    JFrame ventana = new JFrame("Challenge Oracle ONE Conversor de Moneda by Tomás Galicia");
	    ventana.setSize(640,330);
	    
	    //Centrar en Pantalla
	    Dimension tamPantalla= Toolkit.getDefaultToolkit().getScreenSize();
		Dimension tamFrame = ventana.getSize();
		if (tamFrame .height > tamPantalla.height)
		tamFrame .height = tamPantalla.height;
		if (tamFrame .width > tamPantalla.width)
		tamFrame .width = tamPantalla.width;
		ventana.setLocation((tamPantalla.width - tamFrame .width) / 2, (tamPantalla.height - tamFrame .height) / 2);
		//Fin de centrar Pantalla
		ventana.setVisible(true);
	    ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        ventana.add(jLabel);
	    
		do {
		String input = (String) JOptionPane.showInputDialog(ventana,
	        "Seleccione un Convertidor...",
	        "Menú Convertidores", JOptionPane.QUESTION_MESSAGE, null, // Uso
	                                                                 // default
	                                                                // icon
	        choices, // Array of opciones
	        choices[opcion]); // Opcion Inicial

	  		opcion=Arrays.asList(choices).indexOf(input);

	    switch (opcion) {
		case 0:
			objconmon.menuConMon();
			break;
		case 1:
			objcontem.menuConTem();
			break;
		default:
			JOptionPane.showMessageDialog(ventana, "Saliendo del Conversor");
			flag=false;
			System.exit(0);
			break;
	    }	
		}while(flag);
		
	    }
	
}
