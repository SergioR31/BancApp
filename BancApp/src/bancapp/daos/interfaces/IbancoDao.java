package bancapp.daos.interfaces;

import bancapp.models.Banco;

import java.util.List;

/**
 * Interfaz del Objeto de Acceso a DAtos de Banco.
 * @author SergioRamos
 *
 */
public interface IbancoDao {
  public List<Banco> listarBancos() throws Exception;
  
  public String insertarBanco(Banco banco) throws Exception;
  
  public Banco consultarBanco(int idBanco) throws Exception;
  
  public String modificarBanco(Banco banco) throws Exception;
  
  public String eliminarBanco(int idBanco) throws Exception;
  
}
