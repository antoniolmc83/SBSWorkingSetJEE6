package pe.almc.sbs.bean;

/**
 * Tipos de Producto u Operacion
 * @author ALMC
 *
 */
public enum TipoProducto {
	CREDITOS("01"), DEPOSITOS("02"), SEGUROS("03");

	private String codigo;
	
	private TipoProducto(String codigo) {
		this.codigo = codigo;
	}
		
	@Override
	public String toString() {
		return codigo;
	}
	
}
