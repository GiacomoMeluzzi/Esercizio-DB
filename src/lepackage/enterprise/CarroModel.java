package lepackage.enterprise;

public class CarroModel {

	private String nome;
	private int peso;
	
	public CarroModel() {
	}
	
	public CarroModel(String nome, int peso) {
		this.nome = nome;
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CarroModel)) {
			return false;
		}
		CarroModel m = (CarroModel) obj;
		if (m == this || m.getNome().equals(nome)) {
			return true;
		}
		return false;
	}
	
		
}
