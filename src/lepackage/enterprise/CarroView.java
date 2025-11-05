package lepackage.enterprise;

import java.util.InputMismatchException;

public class CarroView {
	
	public void insert (CarroModel model) {
		try {
		String nome = ScannerSingleton.getString("Inserisci nome: ");
		int peso = ScannerSingleton.getInt("Inserisci peso in tonnellate: ");
		model.setNome(nome);
		model.setPeso(peso);
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String delete() throws InputMismatchException {
		return ScannerSingleton.getString("Inserisci id del carro da eliminare: ");	
	}
}
