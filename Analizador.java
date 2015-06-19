package CifradoCesar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;

public class Analizador{

String idioma;

//a, b, c, d...
private double[] english = {8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.966,0.153,0.772,4.025,2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.361,0.150,1.974,0.074};

public Analizador(){

this.idioma = "english";

}

public Analizador(String idioma){

this.idioma = idioma;

}


private Double[] getFrecuencias(String tex){

System.out.println("Realizando análisis estadístico del mensaje encriptado...\n");

LinkedList<String> respuesta = new LinkedList<String>();
Double[] frecuencias = new Double[26];
Arrays.fill(frecuencias, new Double(0));

HashMap<Character, Double> Mapa = new HashMap<Character, Double>();
double letras = 0;

String texto = tex.toLowerCase();

	for (char i : texto.toCharArray()) {
	   Double d;
            if(Character.isLetter(i)) {
		letras+=1;
		if((d = Mapa.get(i)) != null)Mapa.put(new Character(i),new Double(d+1));
		else Mapa.put(new Character(i),new Double(1));

	    }
	}

	System.out.println("|||||||||||||| Frecuencias del alfabeto encriptado ||||||||||||||");

	for (Map.Entry<Character, Double> e: Mapa.entrySet()) {
    		System.out.println(e.getKey() + " " + e.getValue());
		frecuencias[(e.getKey() - 'a') % 26] = (e.getValue()/letras)*100;
	}

	System.out.println("|||||||");

	for(int j = 0; j< frecuencias.length ; j++)
		System.out.println("Freq:" + frecuencias[j]);

	System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

return frecuencias;

}

protected Integer getKey(String t){

	Double[] resultado = getCrossCorrelation(getFrecuencias(t));

	System.out.println("|||||||||||||| Convolución circular ||||||||||||||");
	Integer max = new Integer(0);

	for(int j = 0; j< resultado.length ; j++){
		if(resultado[j] > resultado[max])
			max = j;
                System.out.println(resultado[j]);
	}

	System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

return max;

}

private Double[] getCrossCorrelation(Double [] freq){ //Pseudo correlación cruzada

	System.out.println("\nObteniendo correlación cruzada...\n");

	Double[] dotProducts = new Double[freq.length];

	for(int i=0; i<26; i++){

		double dotProduct = 0;
		for(int j=0; j<freq.length; j++)
			dotProduct+=freq[j]*english[j];

		dotProducts[i] = dotProduct;
		freq = rotarAlfabeto(freq);

	}

return dotProducts;

}

private Double[] rotarAlfabeto(Double [] array){

	Double [] res = new Double[array.length];

	System.arraycopy(new Double[]{array[array.length-1]}, 0, res, 0, 1);
	System.arraycopy(Arrays.copyOfRange(array, 0, array.length-1), 0, res, 1, array.length-1); //En copyOfRange from-to. "to" es exclusivo, no inclusivo.

return res;

}


}
