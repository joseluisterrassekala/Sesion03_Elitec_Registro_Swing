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
	}
}