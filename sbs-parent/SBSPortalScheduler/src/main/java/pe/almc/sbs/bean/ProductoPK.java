package pe.almc.sbs.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional=false)
	@Column(nullable=false, length=2)
	private String codigo;
	
	@Basic(optional=false)
	@Column(nullable=false, length=2)
	private String tipoprod;
	
	public ProductoPK() {
	}

    public ProductoPK(String codigo, String tipoprod) {
        this.codigo = codigo;
        this.tipoprod = tipoprod;
    }
    
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoprod() {
		return tipoprod;
	}

	public void setTipoprod(String tipoprod) {
		this.tipoprod = tipoprod;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        hash += (tipoprod != null ? tipoprod.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
    	return "["+ this.codigo + ", " + tipoprod + "]";
    }
    
}
