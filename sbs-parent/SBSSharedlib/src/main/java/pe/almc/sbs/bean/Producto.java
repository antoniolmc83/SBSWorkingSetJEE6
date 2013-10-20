package pe.almc.sbs.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "public")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.productoPK.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByTipoprod", query = "SELECT p FROM Producto p WHERE p.productoPK.tipoprod = ?1"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")})
public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProductoPK productoPK;

	@Column(length=20)
	private String descripcion;
   
	@OneToMany(cascade=CascadeType.ALL, mappedBy="producto")
	private Collection<Condicion> lstCondiciones;
   
	public Producto() {
	}
	
	public Producto(String codigo, String tipoProd) {
		this.productoPK = new ProductoPK(codigo, tipoProd);
	}

	public ProductoPK getProductoPK() {
		return productoPK;
	}

	public void setProductoPK(ProductoPK productoPK) {
		this.productoPK = productoPK;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Condicion> getLstCondicios() {
		return lstCondiciones;
	}

	public void setLstCondiciones(Collection<Condicion> lstCondiciones) {
		this.lstCondiciones = lstCondiciones;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPK != null ? productoPK.hashCode() : 0);
        return hash;
    }
	
}
