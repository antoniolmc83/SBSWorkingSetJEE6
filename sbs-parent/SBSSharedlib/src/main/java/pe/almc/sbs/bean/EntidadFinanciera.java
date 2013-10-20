package pe.almc.sbs.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "public")
@NamedQueries({
    @NamedQuery(name = "EntidadFinanciera.findAll", query = "SELECT e FROM EntidadFinanciera e"),
    @NamedQuery(name = "EntidadFinanciera.findByCodigo", query = "SELECT e FROM EntidadFinanciera e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EntidadFinanciera.findByRazonsocial", query = "SELECT e FROM EntidadFinanciera e WHERE e.razonsocial = :razonsocial")})
public class EntidadFinanciera implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(nullable = false, length = 4)
	private String codigo;
	
	@Column(length = 70)
	private String razonsocial;
	
	@Column(length = 70)
	private String nombresbs;
	
	public EntidadFinanciera() {
	}
	
	public EntidadFinanciera(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

    public String getNombresbs() {
		return nombresbs;
	}

	public void setNombresbs(String nombresbs) {
		this.nombresbs = nombresbs;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

}
