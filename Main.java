package CifradoCesar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

	public static void main(String[] args) {

	try{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = new String();

		while(true){

			System.out.println("¿Qué desea hacer?");
			System.out.println("(1) Cifrar");
			System.out.println("(2) Descifrar");
			System.out.println("(3) Analizar");
			System.out.println("(4) Salir");
			str = br.readLine();

			if(Integer.parseInt(str) == 1){


				System.out.print("Introduzca la semilla: ");
                		str = br.readLine();

                		Cifrador c = new Cifrador(new Integer(str));

				System.out.println("Introduzca el mensaje a cifrar: ");
                        	str = br.readLine();
                        	System.out.println();

                        	System.out.println("Mensaje cifrado: ");
                        	System.out.println(c.cifrar(str,1));
                       		System.out.println("\n\n");

			}else if(Integer.parseInt(str) == 2){


				System.out.println("Introduzca la semilla con la que fue cifrado el mensaje: ");
                		str = br.readLine();

                		Cifrador c2 = new Cifrador(new Integer(str));

				System.out.println("Introduzca el mensaje a descifrar: ");
                                str = br.readLine();
                                System.out.println();

                                System.out.println("Mensaje descifrado: ");
                                System.out.println(c2.cifrar(str,0));
                                System.out.println("\n\n");

			}else if(Integer.parseInt(str) == 3){

				System.out.println("Introduzca el mensaje a analizar");
				str = br.readLine();
                           	Analizador an = new Analizador("English");
				Integer semilla = an.getKey(str);

				System.out.println("Con toda probabilidad, el desplazamiento del alfabeto utilizado fue: ***" + (26 - semilla) + "***");
				System.out.println();
				System.out.println("Este es el resultado de descifrar el mensaje con dicha clave obtenida: ");
				System.out.println();
				System.out.println("***********************************************************************\n");

				Cifrador c3 = new Cifrador(new Integer(26 - semilla));

				System.out.println(c3.cifrar(str,0));

				System.out.println("\n***********************************************************************");
				System.out.println("\n\n");

			}else if(Integer.parseInt(str) == 4) return;
			else System.out.println("Introduca una opción válida");
		}

	}catch(Exception e){

		System.out.println("Error: "+ e.getMessage());

	}

	}

}
