/**
 * 
 */
package bancapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bancapp.models.Banco;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
import bancapp.models.EstadoCuenta;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IBancoService;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IClienteService;
import bancapp.services.interfaces.IConsultaService;


/**
 * @author SergioRamos
 *
 */
@Controller
public class PDFController{
  
  @Autowired
  private IChequeraService chequeraService;
  
  @Autowired
  private IConsultaService consultaService;
  
  @Autowired
  private IBancoService bancoService;
  
  @Autowired
  private IClienteService clienteService;
  
  @RequestMapping(value = "/estado-de-cuenta")
  public ModelAndView pdfView(HttpServletRequest request,
      HttpServletResponse response, Model model, 
      @RequestParam("idChequera") long idChequera,
      @RequestParam(value = "anioEstadoCuenta", defaultValue = "0") int anio,
      @RequestParam("mesEstadodeCuenta") int mes,
      RedirectAttributes attributes) throws Exception {
    
    Chequera chequera = new Chequera();
    Banco banco = new Banco();
    ArrayList<Movimiento> movimientos = new ArrayList<>();
    ArrayList<Movimiento> movimientosAnteriores = new ArrayList<>();
    String periodo = "mensual";
    
    chequera = chequeraService.consultarChequera(idChequera);
    banco = bancoService.consultarBanco(chequera.getIdBanco());
    movimientos = consultaService.consultarTodos(idChequera, periodo, anio, mes);
    movimientosAnteriores = consultaService.consultarAnteriores(idChequera, periodo, anio, mes);
    Cliente cliente = new Cliente();
    cliente = clienteService.consultarCliente(chequera.getIdCliente());
    
    double totalRetiros = 0;
    double totalDepositos = 0;
    double saldo = chequera.getSaldoApertura();
    double saldoAnterior = 0;
    double saldoAlCorte = 0;
    
    double saldoInicio = 0;
    double saldoAux = 0;
    int contador = 1;
    
    for(Movimiento movimiento: movimientosAnteriores) {
      if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
        saldo -= movimiento.getMonto();
      } else {
        saldo += movimiento.getMonto();
      }
    }
    
    saldoAnterior = saldo;
    saldoInicio = saldoAnterior;
    
    int numRetiros = 0;
    int numDepositos = 0;
    
    String mesStr = obtenerMes(mes);
    
    for (Movimiento movimiento: movimientos) {
      if (contador == 1) {
        if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
          saldoAux = saldoInicio - movimiento.getMonto();
          movimiento.setSaldo(saldoAux);
          totalRetiros += movimiento.getMonto();
          numRetiros += 1;
        } else {
          saldoAux = saldoInicio + movimiento.getMonto();
          movimiento.setSaldo(saldoAux);
          totalDepositos += movimiento.getMonto();
          numDepositos += 1;
        }
        contador = 2;
      } else {
        if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
          saldoAux = saldoAux - movimiento.getMonto();
          movimiento.setSaldo(saldoAux);
          totalRetiros += movimiento.getMonto();
          numRetiros += 1;
        } else {
          saldoAux = saldoAux + movimiento.getMonto();
          movimiento.setSaldo(saldoAux);
          totalDepositos += movimiento.getMonto();
          numDepositos += 1;
        }
      }
    }
    
    saldoAlCorte = saldoAnterior + totalDepositos - totalRetiros;
    
    //user data
    EstadoCuenta estadoCuenta = new EstadoCuenta();
    estadoCuenta.setBanco(banco);
    estadoCuenta.setChequera(chequera);
    estadoCuenta.setCliente(cliente);
    estadoCuenta.setMovimientos(movimientos);
    estadoCuenta.setAnio(anio);
    estadoCuenta.setMes(mesStr);
    estadoCuenta.setTotalRetiros(totalRetiros);
    estadoCuenta.setTotalDepositos(totalDepositos);
    estadoCuenta.setNumDepositos(numDepositos);
    estadoCuenta.setNumRetiros(numRetiros);
    estadoCuenta.setSaldoAnterior(saldoAnterior);
    estadoCuenta.setSaldoAlCorte(saldoAlCorte);
        
    if (movimientos.size() != 0) {
      
      return new ModelAndView("PDFView", "estadoCuenta", estadoCuenta);
      
    } else {
      
      ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
      String mensaje = "No hay movimientos en el periodo seleccionado.";
      
      try {
        
        chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
        
      } catch (Exception e) {
        System.out.println("Error en mostrarPaginaRetiro: " + e);
      }
      
      model.addAttribute("mensaje", mensaje);
      model.addAttribute("chequeras", chequeras);
      
      return new ModelAndView("consultas", "modelo", model);
    }
    
    
  }
  
  public String obtenerMes(int numMes) {
    switch (numMes) {
      case 1:
        return "Enero";
      case 2:
        return "Febrero";
      case 3:
        return "Marzo";
      case 4:
        return "Abril";
      case 5:
        return "Mayo";
      case 6:
        return "Junio";
      case 7:
        return "Julio";
      case 8:
        return "Agosto";
      case 9:
        return "Septiembre";
      case 10:
        return "Octubre";
      case 11:
        return "Noviembre";
      default:
        return "Diciembre";
    }
  }
  

}
