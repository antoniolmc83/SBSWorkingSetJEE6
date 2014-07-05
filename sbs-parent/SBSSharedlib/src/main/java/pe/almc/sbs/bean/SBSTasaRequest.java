package pe.almc.sbs.bean;

public class SBSTasaRequest {

	private String asCondicion;
	private String asDpto;
	private String txtCondicion;
	private String txtDepartamento;
	private String txtProducto;
	private String txtTipoProducto;
	
	
	
	public String getAsCondicion() {
		return asCondicion;
	}
	public void setAsCondicion(String asCondicion) {
		this.asCondicion = asCondicion;
	}
	public String getAsDpto() {
		return asDpto;
	}
	public void setAsDpto(String asDpto) {
		this.asDpto = asDpto;
	}
	public String getTxtCondicion() {
		return txtCondicion;
	}
	public void setTxtCondicion(String txtCondicion) {
		this.txtCondicion = txtCondicion;
	}
	public String getTxtDepartamento() {
		return txtDepartamento;
	}
	public void setTxtDepartamento(String txtDepartamento) {
		this.txtDepartamento = txtDepartamento;
	}
	public String getTxtProducto() {
		return txtProducto;
	}
	public void setTxtProducto(String txtProducto) {
		this.txtProducto = txtProducto;
	}
	public String getTxtTipoProducto() {
		return txtTipoProducto;
	}
	public void setTxtTipoProducto(String txtTipoProducto) {
		this.txtTipoProducto = txtTipoProducto;
	}
	
	@Override
	public String toString() {
		String guion = " - ";
		return asCondicion + guion + asDpto + guion + txtCondicion + guion + 
				txtDepartamento + guion + txtProducto + guion + txtTipoProducto;
	}
	
}
