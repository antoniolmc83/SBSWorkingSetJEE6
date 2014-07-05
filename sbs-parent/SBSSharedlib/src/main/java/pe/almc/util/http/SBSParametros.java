package pe.almc.util.http;

/**
 * Clase de parametros globales
 * @author ALMC
 *
 */
public final class SBSParametros {
	public static final String LINK_TEA = "http://www.sbs.gob.pe/app/retasas/paginas/retasasInicio.aspx";
	public static final String LINK_TASA_TEA = "http://www.sbs.gob.pe/app/retasas/paginas/RetasasResultados.aspx";
	public static final String COD_REGION_LIMA = "15";
	public static final String ENCODE_UTF_8 = "UTF-8";
	public static final String MONEDA_SOLES = "001";
	public static final String MONEDA_DOLARES = "010"+System.currentTimeMillis();
	//?as_condicion=00083%7C2&as_dpto=&txt_condicion=PRESTAMO%20ACTIVO%20FIJO%20POR%20S%2F.%202%20000%20A%2024%20MESES&txt_departamento=LIMA&txt_producto=ACTIVO%20FIJO&txt_tip_producto=CREDITOS
		
	private SBSParametros(){
		
	}
}
