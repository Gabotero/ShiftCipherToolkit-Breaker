package CifradoCesar;

public class Cifrador{

private Integer semilla;


public Cifrador(){

	this.semilla = -1;
	return;
}

public Cifrador (Integer sem){

	this.semilla = sem % 26;
	return;
}

protected Integer getSemilla (){

	return this.semilla;

}

protected void setSemilla(Integer sem){

	this.semilla = sem;
	return;
}

protected String cifrar(String s, int cod){

int sem ;

if(cod == 1) sem = this.semilla;
else  sem = 26 - this.semilla;

StringBuilder codificado = new StringBuilder();
        for (char i : s.toCharArray()) {
            if (Character.isLetter(i)) {

		if (i == 'ñ')codificado.append(i);
		else if (i=='Ñ')codificado.append(i);
                else if (Character.isUpperCase(i)) {
                    codificado.append((char) ('A' + (i - 'A' + sem) % 26 ));
                } else {
                    codificado.append((char) ('a' + (i - 'a' + sem) % 26 ));
                }
            } else {
                codificado.append(i);
            }
        }
	return  codificado.toString();

}

protected String decodificar (String s){

	return cifrar(s, 26 - this.semilla);
}


}

