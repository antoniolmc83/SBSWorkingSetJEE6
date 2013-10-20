package pe.almc.sbs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class InfoTasaDiariaPK implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @Column(nullable = false, length = 4)
	private String entidadcodigo;
	
    @Basic(optional = false)
    @Column(nullable = false, length = 7)
	private String condicioncodigo;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 4)
    private String regioncodigo;
	
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
	
    public InfoTasaDiariaPK() {
	}

	public InfoTasaDiariaPK(String entidadcodigo, String condicioncodigo, String regioncodigo, Date fecha) {
		this.entidadcodigo = entidadcodigo;
		this.condicioncodigo = condicioncodigo;
		this.regioncodigo = regioncodigo;
		this.fecha = fecha;
	}

	public String getEntidadcodigo() {
		return entidadcodigo;
	}

	public void setEntidadcodigo(String entidadcodigo) {
		this.entidadcodigo = entidadcodigo;
	}

	public String getCondicioncodigo() {
		return condicioncodigo;
	}

	public void setCondicioncodigo(String condicioncodigo) {
		this.condicioncodigo = condicioncodigo;
	}

	public String getRegioncodigo() {
		return regioncodigo;
	}

	public void setRegioncodigo(String regioncodigo) {
		this.regioncodigo = regioncodigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entidadcodigo != null ? entidadcodigo.hashCode() : 0);
        hash += (condicioncodigo != null ? condicioncodigo.hashCode() : 0);
        hash += (regioncodigo != null ? regioncodigo.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
    	return "["+ entidadcodigo + ", " + condicioncodigo + ", " + regioncodigo + ", " + fecha + "]";
    }
    
}
