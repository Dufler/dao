package it.ltc.model.interfaces.prodotto;

public enum Categoria {
	
	CALZATURE("CALZATURE", 1),
	BORSE("BORSE", 2),
	ACCESSORI("ACCESSORI", 3),
	STESI("STESI", 4),
	APPESI("APPESI", 5);
	
	private final String categoria;
	private final int id;
	
	private Categoria(String categoria, int id) {
		this.categoria = categoria;
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public int getID() {
		return id;
	}
	
	public static Categoria getCategoriaPerID(int id) {
		Categoria c;
		switch (id) {
			case 1 : c = CALZATURE; break;
			case 2 : c = BORSE; break;
			case 3 : c = ACCESSORI; break;
			case 4 : c = STESI; break;
			case 5 : c = APPESI; break;
			default : c = null;
		}
		return c;
	}

}
