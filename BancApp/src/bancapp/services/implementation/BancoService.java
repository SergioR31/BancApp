package bancapp.services.implementation;

import bancapp.daos.interfaces.IbancoDao;
import bancapp.models.Banco;
import bancapp.services.interfaces.IBancoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del Service de Banco.
 * @author SergioRamos
 *
 */
@Service
public class BancoService implements IBancoService {
  
  @Autowired
  private IbancoDao bancoDAO;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IBancoService#insertarBanco(bancapp.models.Banco)
   */
  
  @Override
  public List<Banco> listarBancos() throws Exception {
    
    ArrayList<Banco> bancos = new ArrayList<Banco>();
    
    try {
      bancos = (ArrayList<Banco>) bancoDAO.listarBancos();
    } catch (Exception e) {
      System.out.println("Error en listarBancos en BancoService: " + e);
    }
    return bancos;
  }
  
  @Override
  public Banco consultarBanco(int idBanco) throws Exception {
    
    Banco banco = new Banco();
    
    try {
      banco = bancoDAO.consultarBanco(idBanco);
    } catch (Exception e) {
      System.out.println("Error en consultarBanco en Banco Service: " + e);
    }
    return banco;
  }
  
  @Override
  public String insertarBanco(Banco banco) throws Exception {
    
    String respuesta = "";
    
    try {
      respuesta = bancoDAO.insertarBanco(banco);
    } catch (Exception e) {
      System.out.println("Error en insertarBanco BancoService: " + e);
    }
    
    return respuesta;
  }

  @Override
  public String modificarBanco(Banco banco) throws Exception {
    
    String respuesta = "";
    
    try {
      respuesta = bancoDAO.modificarBanco(banco);
    } catch (Exception e) {
      System.out.println("Error en modificarBanco en BancoService");
    }
    return respuesta;
  }

  @Override
  public String eliminarBanco(int idBanco) throws Exception {
    String respuesta = "";
    
    try {
      respuesta = bancoDAO.eliminarBanco(idBanco);
    } catch (Exception e) {
      System.out.println("Error en eliminarBanco en BancoService");
    }
    return respuesta;
  }

  

}
