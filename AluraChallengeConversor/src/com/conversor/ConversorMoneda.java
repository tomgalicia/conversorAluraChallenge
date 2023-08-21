package com.conversor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class ConversorMoneda {

	public static Moneda[] arrayMonedas= new Moneda[5];    //Obtenido de API
	String SimbolosMoneda[]= {"USD","EUR","GBP","JPY","KRW"};
    String patronformato="###,###,###.##";
    int    NumMonedas=5;
    DecimalFormat miformato=new DecimalFormat(patronformato);
    
	    public Moneda[] APIgetTiposDeCambio() {
	    	Moneda[] arrayTC= new Moneda[5];
			String cadenadatos="";
			
				//Actualizar tipos de cambio
			try {
			    String apikey = "cur_live_dmskK4CQYGXosPzyQUQyw7RYUHiBmy3ypMPFH0WI";
			    String url = "https://api.currencyapi.com/v3/latest?apikey=" + apikey + "&base_currency=MXN"+"&currencies=USD,EUR,GBP,JPY,KRW";
			    URL urlForGetRequest = new URL(url);
			    String readLine = null;
			    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			    conection.setRequestMethod("GET");
			    int responseCode = conection.getResponseCode();
	
			    if (responseCode == HttpURLConnection.HTTP_OK) {
			        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			        StringBuffer response = new StringBuffer();
			        while ((readLine = in.readLine()) != null) {
			        	     response.append(readLine);
			       	     	        	     
			        }
			        
			        in.close();
			         cadenadatos=response.toString();
			         arrayTC= jasonFormat(cadenadatos);
			         JOptionPane.showMessageDialog(null,
			        		 "ACTUALIZACION DE TIPOS DE CAMBIO CON EXITO\n"+
			        		 "DEL SITIO https://api.currencyapi.com");
			            return arrayTC;         		     
			    } else {
			        throw new Exception("ERROR EN LLAMADA API");
			        			        			        
			    }
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "ERROR EN LLAMADA API"
						+ " SE USARAN TIPOS DE CAMBIO EN MODO DEMO");
					arrayTC= jasonFormat(cadenadatos);
					return arrayTC;
				
			}
		}

	    //Formatea JASON obtenido de la API 
	    public Moneda[] jasonFormat(String textAPI) {
	       
	       Moneda[] arraymonedas =new Moneda[5];	
	 	   String SimbolosMoneda[]= {"USD","EUR","GBP","JPY","KRW"};
	 	   double TipoCambio[]= {0.06,0.054,0.047,8.46,76.3};
	 	   int j=0;
	 	   if (textAPI != null && !textAPI.isEmpty()) {
		 		   	String[] textoSeparado =textAPI.split("[{,}:]");
		 	        for (int i=10; i<textoSeparado.length; i=i+7) {
		 	        	 SimbolosMoneda[j]=textoSeparado[i].replaceAll("\"","");
		 	        	 TipoCambio[j]=Double.parseDouble(textoSeparado[i+5]);
		 	        	 j++;
		 	        	}
	 	   }
	 	   
	       for (int i=0; i<NumMonedas; i++) {
	     	     	 arraymonedas[i]=new Moneda(SimbolosMoneda[i],TipoCambio[i]);
	        	    }
	      return arraymonedas;   
	 	}    	
    
    
    	private double exchange(String fromCurrency,String toCurrency,double amount){
    		double rate = getTipoCambio(fromCurrency,toCurrency);
    		double result = rate * amount;
    		return result;
    	}

     	private double getTipoCambio(String fromsm,String tosm) {
     		double factortipodecambio=0.0;
     		String simbolomoneda="";
     		if (fromsm=="MXN") {
	      		for (int i=0; i<NumMonedas; i++) {
	     				simbolomoneda=arrayMonedas[i].getCode();
	     				if (simbolomoneda.equals(tosm)) {
	     					factortipodecambio=arrayMonedas[i].getValue();
	     				}
     		}
     		}else {
	     		for (int i=0; i<NumMonedas; i++) {
	     				simbolomoneda=arrayMonedas[i].getCode();
	     				if (simbolomoneda.equals(fromsm)) {
	      				factortipodecambio=(1/arrayMonedas[i].getValue());
	                	}
     			
			}
     	}
     	return factortipodecambio;
     	}
	
	
	public void menuConMon() {
		Integer  opcionCM=0;
		String[] opcionesCM = { "Pesos a Dólares","Pesos a Euros","Pesos a Libras-Esterlinas",
								"Pesos a Yenes-Japoneses","Pesos a Wones-SudCoreanos",
								"Dólares a Pesos","Euros a Pesos","Libras-Esterlinas a Pesos",
								"Yenes-Japoneses a Pesos","Wones-Sudcoreanos a Pesos"};

		String SimbolosMoneda[]= {"USD","EUR","GBP","JPY","KRW"};
		double cantidadConvertir=0.0;
		double cantidadConvertida=0.0;
		String SimMonedaBase="MXN";
		String stringcantconvertir=null;
		String stringcantconvertida=null;
		boolean sigue = true;
		boolean entradacorrecta=false;
		while (sigue){
			stringcantconvertir=JOptionPane.showInputDialog(
				    "Ingresa la cantidad a convertir");
		     sigue = stringcantconvertir != null;
		    if (sigue) {
		        try {
		        	cantidadConvertir = Double.parseDouble(stringcantconvertir);
		        	entradacorrecta=true;
		        	break;
		        } catch (NumberFormatException e) {
		        	JOptionPane.showMessageDialog(null, "El valor introducido no es un número");
		            entradacorrecta=false;
		        }
		    }
		}
	
		if (entradacorrecta) {
			arrayMonedas=APIgetTiposDeCambio();  //Obtener Tipos de Cambio en Tiempo Real
			
		String inputCM = (String) JOptionPane.showInputDialog(null,
			        "Seleccione Moneda a Convertir...",
			        "Menú Conversiones de Monedas", JOptionPane.QUESTION_MESSAGE, null, // Uso
			                                                                 // default
			                                                                // icono
			        opcionesCM, // Array of opciones
			        opcionesCM[opcionCM]); // Opcion Inicial
		
		opcionCM=Arrays.asList(opcionesCM).indexOf(inputCM);
		String[] MenCon=opcionesCM[opcionCM].split(" ");
		if (opcionCM>=NumMonedas) {
				opcionCM=opcionCM-NumMonedas;
				cantidadConvertida=exchange(SimbolosMoneda[opcionCM],SimMonedaBase ,cantidadConvertir);
			} else {
				cantidadConvertida=exchange(SimMonedaBase,SimbolosMoneda[opcionCM],cantidadConvertir);	
			}
			stringcantconvertida=miformato.format(cantidadConvertida);
       		JOptionPane.showMessageDialog(null,stringcantconvertir+" "+
       		MenCon[0]+"\nEquivalen a: \n"+stringcantconvertida+" "+MenCon[2]);
				
	  	}
	}
}


