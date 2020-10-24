package divisa;

public class Divisa {
	//Iniciamos el nombre de la tasa y el valor de la propia tasa
	private String nombre;
	private Double tasa;
	
	//Constructor de Divisa
	public Divisa(String nombre, Double tasa) {
		super();
		this.nombre = nombre;
		this.tasa = tasa;
	}
	//Retornamos el nombre de la tasa
	public String getNombre() {
		return nombre;
	}
	//Le damos nombre a esta tasa
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//Retornamos el valor de la tasa
	public Double getTasa() {
		return tasa;
	}
	//Cambiamos el valor de la tasa
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	//Función que devuelve el valor Euros -> Tasa
	public Double fromEuro(Double euros) {
		return euros * tasa;
	}
	//Función que devuelve el valor Tasa -> Euros
	public Double toEuro(Double moneda) {
		return moneda / tasa;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	public static Double fromTo(Divisa from, Divisa to, Double cantidad) {
		return to.fromEuro(from.toEuro(cantidad));
	}

}
