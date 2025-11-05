package lepackage.enterprise;

import java.util.InputMismatchException;

public class CarroController {
	
	private static CarroController instance = null;
	private CarroView view = new CarroView();
	private CarroModel model = new CarroModel();
	
	private CarroController() {}
	
	public static CarroController getInstance() {
		if (instance == null) {
			instance = new CarroController();
		}
		return instance;
	}
	
	public String[] insertDb () {
		String[] dati = new String[2];
		model = insert();
		dati[0] = String.valueOf(model.getNome());
		dati[1] = String.valueOf(model.getPeso());
		return dati;
	}
	
	private CarroModel insert() {
		try {
			view.insert(model);
			CarroModel daAggiungere = new CarroModel();
			daAggiungere.setNome(model.getNome());
			daAggiungere.setPeso(model.getPeso());
			return daAggiungere;
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String delete() {
		try {
		return view.delete();
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String search() {
		try {
			return view.search();
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
