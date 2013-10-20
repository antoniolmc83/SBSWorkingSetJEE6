package pe.almc.sbs.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public")
@NamedQueries({
    @NamedQuery(name = "Condicion.findAll", query = "SELECT c FROM Condicion c"),
    @NamedQuery(name = "Condicion.findByCodigo", query = "SELECT c FROM Condicion c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Condicion.findByDescripcion", query = "SELECT c FROM Condicion c WHERE c.descripcion = :descripcion")})
public class Condicion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(nullable = false, length = 7)
	private String codigo;
	
	@Column(length = 70)
	private String descripcion;
	
	@JoinColumns({
	        @JoinColumn(name = "pro_codigo", referencedColumnName = "codigo", nullable = false),
	        @JoinColumn(name = "tipoprod", referencedColumnName = "tipoprod", nullable = false)})
	@ManyToOne(optional = false)
	private Producto producto;
	
	public Condicion() {
	}
	
	public Condicion(String codigo) {
		this.codigo = codigo;
	}

	public Condicion(String codigo, String codProd, String tipoProd) {
		this.codigo = codigo;
		this.producto = new Producto(codProd, tipoProd);
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

	
}
