package bancapp.daos.interfaces;

import bancapp.models.Chequera;

import java.util.List;

/**
 * Interfaz del Objeto de Acceso a DAtos de Chequera.
 * @author SergioRamos
 *
 */
public interface IChequeraDAO {
  public List<Chequera> listarChequeras() throws Exception;
  
  public String insertarChequera(Chequera chequera) throws Exception;
  
  public Chequera consultarChequera(long idChequera) throws Exception;
  
  public String modificarChequera(Chequera chequera) throws Exception;
  
  public String eliminarChequera(long idChequera) throws Exception;
  
}
