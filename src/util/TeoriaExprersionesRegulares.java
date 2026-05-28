package util;

public class TeoriaExprersionesRegulares {

	//https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
	
	public static void main(String[] args) {
	
		//Validar Números
		System.out.println("8".matches("[0-9]"));
		System.out.println("81".matches("[0-9]"));
		System.out.println("a".matches("[0-9]"));
		System.out.println("@".matches("[0-9]"));
		
		System.out.println("8".matches("\\d"));
		System.out.println("81".matches("\\d"));
		System.out.println("a".matches("\\d"));
		System.out.println("@".matches("\\d"));
		
		//tres dígitos
		System.out.println("823".matches("[0-9][0-9][0-9]"));
		System.out.println("823".matches("\\d\\d\\d"));
		System.out.println("823".matches("[0-9]{3}"));
		System.out.println("823".matches("\\d{3}"));
		
		//Validar DNI
		System.out.println("82334567".matches("[0-9]{8}"));
		System.out.println("82334567".matches("\\d{8}"));
		
		//Validar RUC
		System.out.println("82334567123".matches("[0-9]{11}"));
		System.out.println("82334567123".matches("\\d{11}"));
		
		
		
	}
}