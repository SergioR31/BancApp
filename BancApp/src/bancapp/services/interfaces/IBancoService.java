package bancapp.services.interfaces;

import bancapp.models.Banco;

import java.util.List;

/**
 * Interfaz del Service de Banso.
 * @author SergioRamos
 *
 */
public interface IBancoService {
  
  public List<Banco> listarBancos() throws Exception;
  
  public String insertarBanco(Banco banco) throws Exception;
  
  public Banco consultarBanco(int idBanco) throws Exception;
  
  public String modificarBanco(Banco banco) throws Exception;
  
  public String eliminarBanco(int idBanco) throws Exception;
}
