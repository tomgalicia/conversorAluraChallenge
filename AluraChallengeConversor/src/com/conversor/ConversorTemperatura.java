package com.conversor;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ConversorTemperatura {
		    
	    // Centigrados to Fahrenheit
	    static double Celcius_to_Fahrenheit(double Celcius){
	        double Fahrenheit = (Celcius * 9/5) + 32;
	        return Fahrenheit;
	    }
	    // Centigrados to Kelvin
	    static double Celcius_to_Kelvin(double Celcius){
	        double Kelvin = Celcius + 273.15;
	        return Kelvin;
	    }

	    // Fahrenheit to Centigrados
	    static double Fahrenheit_to_Celcius(double Fahrenheit){
	        double Celcius = (Fahrenheit - 32) * 5/9;
	        return Celcius;
	    }
	    // Fahrenheit to Kelvin
	    static double Fahrenheit_to_Kelvin(double Fahrenheit){
	        double Kelvin = (Fahrenheit - 32) * 5/9 + 273.15;
	        return Kelvin;
	    }

	    // Kelvin to Centrigrados
	    static double Kelvin_to_Celcius(double Kelvin){
	        double Celcius = Kelvin - 273.15;
	        return Celcius;
	    }
	    // Kelvin to Fahrenheit
	    static double Kelvin_to_Fahrenheit(double Kelvin){
	        double Fahrenheit = (Kelvin - 273.15) * 9/5 + 32;
	        return Fahrenheit;
	    }
       

	    // Método Principal
	    public  void menuConTem(){
	    	Integer  opcionCT=0;
			String[] opcionesCT = { "Centigrados a Farenheit",
									"Centigrados a Kelvin",
									"Fahrenheit a Centigrados",
									"Fahrenheit a Kelvin",
									"Kelvin a Centigrados",
									"Kelvin a Farenheit"};
									
			double temperaturaConvertir=0.0;
			double temperaturaConvertida=0.0;
			String stemperaturaconvertir=null;
			boolean sigue = true;
			boolean entradacorrecta=false;
			
			while (sigue){
				stemperaturaconvertir=JOptionPane.showInputDialog(
					    "Ingresa la cantidad a convertir");
			     sigue = stemperaturaconvertir != null;
			    if (sigue) {
			        try {
			        	temperaturaConvertir = Double.parseDouble(stemperaturaconvertir);
			        	entradacorrecta=true;
			        	break;
			        } catch (NumberFormatException e) {
			        	JOptionPane.showMessageDialog(null, "El valor introducido no es un número");
			            entradacorrecta=false;
			        }
			    }
			}
		
			
						
			if (entradacorrecta){
							
				String inputCT = (String) JOptionPane.showInputDialog(null,
						        "Seleccione Temperatura a Convertir...",
						        "Menú Conversiones de Temperaturas", JOptionPane.QUESTION_MESSAGE, null, // Uso
						                                                                 // default
						                                                                // icono
						        opcionesCT, // Array of opciones
						        opcionesCT[opcionCT]); // Opcion Inicial
					
								opcionCT=Arrays.asList(opcionesCT).indexOf(inputCT);
								
						
	                switch(opcionCT){
	                case 0: 
	                	temperaturaConvertida=Celcius_to_Fahrenheit(temperaturaConvertir);
	                    break;
	                case 1: 
	                	temperaturaConvertida=Celcius_to_Kelvin(temperaturaConvertir);
	                    break;
	                case 2: 
	                	temperaturaConvertida=Fahrenheit_to_Celcius(temperaturaConvertir);
	                    break;
	                case 3: 
	                	temperaturaConvertida=Fahrenheit_to_Kelvin(temperaturaConvertir);
	                    break;
	                case 4:
	                	temperaturaConvertida=Kelvin_to_Celcius(temperaturaConvertir);
	                    break;
	                case 5: 
	                	temperaturaConvertida=Kelvin_to_Fahrenheit(temperaturaConvertir);
	                    break;
	                 default: 
	                	 break;
	            }
	              String[] MenCon=opcionesCT[opcionCT].split(" ");  
	              JOptionPane.showMessageDialog(null,String.format
	            		  (stemperaturaconvertir+" Grados "+MenCon[0]+"\nEquivalen a: \n"+
	            		   "%.2f",temperaturaConvertida)+" Grados "+MenCon[2]); 
				}
	    }
}


