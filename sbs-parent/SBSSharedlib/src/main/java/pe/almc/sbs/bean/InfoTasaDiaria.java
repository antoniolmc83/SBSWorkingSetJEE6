package pe.almc.sbs.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import pe.almc.common.MAGICNumber;

@Entity
@Table(schema = "public")
@NamedQueries({
    @NamedQuery(name = "InfoTasaDiaria.findAll", query = "SELECT i FROM InfoTasaDiaria i"),
    @NamedQuery(name = "InfoTasaDiaria.findByEntidadcodigo", query = "SELECT i FROM InfoTasaDiaria i WHERE i.infotasadiariaPK.entidadcodigo = ?1"),
    @NamedQuery(name = "InfoTasaDiaria.findByCondicioncodigo", query = "SELECT i FROM InfoTasaDiaria i WHERE i.infotasadiariaPK.condicioncodigo = :condicioncodigo"),
    @NamedQuery(name = "InfoTasaDiaria.findByRegioncodigo", query = "SELECT i FROM InfoTasaDiaria i WHERE i.infotasadiariaPK.regioncodigo = :regioncodigo"),
    @NamedQuery(name = "InfoTasaDiaria.findByFecha", query = "SELECT i FROM InfoTasaDiaria i WHERE i.infotasadiariaPK.fecha = ?1")})
public class InfoTasaDiaria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private InfoTasaDiariaPK infotasadiariaPK;
	
    @JoinColumn(name = "entidadcodigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadfinanciera;
    
	@JoinColumn(name = "regioncodigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Region region;
    
    @JoinColumn(name = "condicioncodigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Condicion condicion;

    @Column(precision = MAGICNumber.L5, scale = MAGICNumber.L2)
    private BigDecimal tcea;

    @Column(precision = MAGICNumber.L8, scale = MAGICNumber.L2)
    private BigDecimal cuota;

    @Column(precision = MAGICNumber.L5, scale = MAGICNumber.L2)
    private BigDecimal trea;
	
    @Column(precision = MAGICNumber.L5, scale = MAGICNumber.L2)
    private BigDecimal treamin;

    @Column(precision = MAGICNumber.L8, scale = MAGICNumber.L2)
    private BigDecimal prima;
    
    @Column(precision = MAGICNumber.L8, scale = MAGICNumber.L2)
    private BigDecimal trea1000;

    @Column(precision = MAGICNumber.L8, scale = MAGICNumber.L2)
    private BigDecimal trea3000;
    
    @Column(precision = MAGICNumber.L8, scale = MAGICNumber.L2)
    private BigDecimal trea10000;
    
    @Column(length = MAGICNumber.L3)
    private String monedaTrea;

    @Transient
    private String entidad;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infotasadiariaPK != null ? infotasadiariaPK.hashCode() : 0);
        return hash;
    }

    public InfoTasaDiaria() {
	}
    
    public InfoTasaDiaria(String entidadcodigo, String condicioncodigo, String regioncodigo, Date fecha){
    	this.infotasadiariaPK = new InfoTasaDiariaPK(entidadcodigo, condicioncodigo, regioncodigo, fecha);
    	this.entidadfinanciera = new EntidadFinanciera(entidadcodigo);
    	this.condicion = new Condicion(condicioncodigo);
    	this.region = new Region(regioncodigo);
    }
    
    public InfoTasaDiaria(EntidadFinanciera entidadFinanciera, Condicion condicion, Region region, Date fecha){
    	this.infotasadiariaPK = new InfoTasaDiariaPK(entidadFinanciera.getCodigo(), condicion.getCodigo(), 
    			region.getCodigo(), fecha);
    	this.entidadfinanciera = entidadFinanciera;
    	this.condicion = condicion;
    	this.region = region;
    }
    
	public InfoTasaDiariaPK getInfotasadiariaPK() {
		return infotasadiariaPK;
	}



	public void setInfotasadiariaPK(InfoTasaDiariaPK infotasadiariaPK) {
		this.infotasadiariaPK = infotasadiariaPK;
	}



	public EntidadFinanciera getEntidadfinanciera() {
		return entidadfinanciera;
	}



	public void setEntidadfinanciera(EntidadFinanciera entidadfinanciera) {
		this.entidadfinanciera = entidadfinanciera;
		if( infotasadiariaPK == null ){
			infotasadiariaPK = new InfoTasaDiariaPK();
		}
		infotasadiariaPK.setEntidadcodigo(entidadfinanciera.getCodigo());
	}



	public Region getRegion() {
		return region;
	}



	public void setRegion(Region region) {
		this.region = region;
		if( infotasadiariaPK == null ){
			infotasadiariaPK = new InfoTasaDiariaPK();
		}
		infotasadiariaPK.setRegioncodigo(region.getCodigo());
	}



	public Condicion getCondicion() {
		return condicion;
	}



	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
		if( infotasadiariaPK == null ){
			infotasadiariaPK = new InfoTasaDiariaPK();
		}
		infotasadiariaPK.setCondicioncodigo(condicion.getCodigo());
	}

	public BigDecimal getTcea() {
		return tcea;
	}

	public void setTcea(BigDecimal tcea) {
		this.tcea = tcea;
	}

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public BigDecimal getTrea() {
		return trea;
	}

	public void setTrea(BigDecimal trea) {
		this.trea = trea;
	}

	public BigDecimal getTreamin() {
		return treamin;
	}

	public void setTreamin(BigDecimal treamin) {
		this.treamin = treamin;
	}


	public BigDecimal getPrima() {
		return prima;
	}

	public void setPrima(BigDecimal prima) {
		this.prima = prima;
	}

	public BigDecimal getTrea1000() {
		return trea1000;
	}

	public void setTrea1000(BigDecimal trea1000) {
		this.trea1000 = trea1000;
	}

	public BigDecimal getTrea3000() {
		return trea3000;
	}

	public void setTrea3000(BigDecimal trea3000) {
		this.trea3000 = trea3000;
	}

	public BigDecimal getTrea10000() {
		return trea10000;
	}

	public void setTrea10000(BigDecimal trea10000) {
		this.trea10000 = trea10000;
	}

	public String getMonedaTrea() {
		return monedaTrea;
	}

	public void setMonedaTrea(String monedaTrea) {
		this.monedaTrea = monedaTrea;
	}

	@Override
	public String toString() {	
		return getEntidadfinanciera() +" | " + getEntidad() + " | " +  getTcea() +  " | " + getCuota() ;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	public String getEntidad(){
		return this.entidad;
	}
	
	
}
