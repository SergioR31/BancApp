/**
 * 
 */
package bancapp.models;

/**
 * @author SergioRamos
 *
 */
public class Banco {
	
	private int idBanco;
	private String entidad;
	private String sucursal;
	private String direccion;
	private String status;
	/**
	 * @return the idBanco
	 */
	public int getIdBanco() {
		return idBanco;
	}
	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
