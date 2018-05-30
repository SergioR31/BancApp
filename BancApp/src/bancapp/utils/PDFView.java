package bancapp.utils;

import bancapp.models.Banco;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
import bancapp.models.EstadoCuenta;
import bancapp.models.Movimiento;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author SergioRamos
 *
 */
public class PDFView extends AbstractITextPdfView {
  
  private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 25,
          Font.BOLD);
  private static Font subTitleFont = new Font(Font.FontFamily.HELVETICA, 18,
          Font.BOLD);
  private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 8,
          Font.NORMAL, BaseColor.BLUE);
  private static Font blueMediumFont = new Font(Font.FontFamily.HELVETICA, 14,
      Font.NORMAL, BaseColor.BLUE);
  private static Font mediumFont = new Font(Font.FontFamily.HELVETICA, 14,
      Font.NORMAL);
  private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 12,
          Font.NORMAL);
  private static Font normalBold = new Font(Font.FontFamily.HELVETICA, 12,
          Font.BOLD);
  private static Font smallNormal = new Font(Font.FontFamily.HELVETICA, 10,
          Font.NORMAL);
  private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10,
          Font.BOLD);
  
  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    
    EstadoCuenta estadoCuenta = (EstadoCuenta) model.get("estadoCuenta");
    Chequera chequera = estadoCuenta.getChequera();
    Banco banco = estadoCuenta.getBanco();
    Cliente cliente = estadoCuenta.getCliente();
    ArrayList<Movimiento> movimientos = estadoCuenta.getMovimientos();
    
    addMetaData(document);
    agregarTitulo(document, estadoCuenta, banco, cliente);
    agregarInfoChequera(document, chequera);
    agregarInfoMovimientos(document, estadoCuenta);
    agregarMovimientos(document, movimientos);
    
  }
  
  //iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
  private static void addMetaData(Document document) {
    document.addTitle("Estado de Cuenta");
    document.addSubject("Movimientos");
    document.addKeywords("Java, PDF, iText, Banco, Cuenta");
    document.addAuthor("Sergio Ramos");
    document.addCreator("Sergio Ramos");
  }
  
  private static void agregarTitulo(
      Document document, EstadoCuenta estadoCuenta, Banco banco, Cliente cliente)
      throws DocumentException {
    
    Calendar calendar = Calendar.getInstance();
    
    String dia = "";
    String mes = "";
    
    if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
      dia = "0" + calendar.get(Calendar.DAY_OF_MONTH);
    } else {
      dia = "" + calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    if (calendar.get(Calendar.MONTH) + 1 < 10) {
      mes = "0" + (calendar.get(Calendar.MONTH) + 1);
    } else {
      mes = "" + (calendar.get(Calendar.MONTH) + 1);
    }
    
    Paragraph titulo = new Paragraph();
    titulo.setFont(titleFont);
    titulo.add(banco.getEntidad());
    document.add(titulo);
    
    titulo.clear();
    
    titulo.setFont(blueFont);
    titulo.add("Reporte generado: " + dia + "/" + mes + "/" + calendar.get(Calendar.YEAR));
    agregarSaltoLinea(document, 1);
    
    document.add(titulo);
    
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);    
    table.addCell(new Phrase(cliente.getNombre() + " " 
        + cliente.getApellidoPaterno() + " " 
        + cliente.getApellidoMaterno(), normalBold));
    
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(new Phrase("Estado de Cuenta Mensual", normalFont));
    
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(new Phrase(cliente.getDireccion() + "\n" 
              + cliente.getEstado() + " " 
              + cliente.getCodigoPostal(), normalFont));
    
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(new Phrase(estadoCuenta.getMes() + " de "
        + estadoCuenta.getAnio(), normalFont));
    
    document.add(table);
    
    agregarSaltoLinea(document,1);
  }
  
  private static void agregarInfoChequera(Document document, Chequera chequera) 
      throws DocumentException {
    
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);    
    table.addCell(new Phrase("Chequera: " + chequera.getIdChequera(), normalFont));
    
    table.getDefaultCell().setFixedHeight(20);
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(new Phrase("CLABE: " + chequera.getClabe(), normalFont));
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(new Phrase("Fecha de Apertura: " + chequera.getFechaApertura() + "\n"
        + "Saldo de Apertura: $" + chequera.getSaldoApertura() + "0", normalFont));
    
    table.getDefaultCell().setFixedHeight(40);
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
    table.addCell(new Phrase("Saldo Actual: $" + chequera.getSaldo() + "0", subTitleFont));
    
    document.add(table);
    
  }
  
  private static void agregarInfoMovimientos(Document document, EstadoCuenta estadoCuenta)
      throws DocumentException{
    
    agregarSaltoLinea(document,1);
    
    Paragraph saldoAnterior = new Paragraph();
    saldoAnterior.setFont(mediumFont);
    saldoAnterior.add("Saldo anterior: ");
    document.add(saldoAnterior);
    
    agregarSaltoLinea(document,1);
    
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
    
    table.getDefaultCell().setFixedHeight(20);
    
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);    
    table.addCell(new Phrase("(+)   " + estadoCuenta.getNumDepositos(), normalFont));
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(new Phrase(" depositos ", normalFont));
    
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(new Phrase("" + estadoCuenta.getTotalDepositos() + "0", normalFont));
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(new Phrase("(-)   " + estadoCuenta.getNumRetiros(), normalFont));
    
    //table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(new Phrase(" retiros ", normalFont));
    
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell("" + estadoCuenta.getTotalRetiros() + "0");

    document.add(table);
    
    agregarSaltoLinea(document,1);
    
    Paragraph saldoCorte = new Paragraph();
    saldoCorte.setFont(mediumFont);
    saldoCorte.add("Saldo al corte: ");
    document.add(saldoCorte);
    
  }
  
  private static void agregarMovimientos(Document document, ArrayList<Movimiento> movimientos) 
      throws DocumentException {
    
    agregarSaltoLinea(document,1);
    
    Paragraph subtitleDetalle = new Paragraph("Detalle de Operaciones", subTitleFont);
    
    document.add(subtitleDetalle);
    
    agregarSaltoLinea(document,1);
    
    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100);
    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
    table.addCell(new Phrase("Fecha", blueMediumFont));
    table.addCell(new Phrase("Concepto", blueMediumFont));
    table.addCell(new Phrase("Retiros", blueMediumFont));
    table.addCell(new Phrase("Depositos", blueMediumFont));
    table.addCell(new Phrase("Saldo", blueMediumFont));
    table.setHeaderRows(1);
    
    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
    
    for (Movimiento movimiento : movimientos) {
      table.addCell(new Phrase(movimiento.getFecha().toString(), normalFont));
      table.addCell(new Phrase(movimiento.getConcepto(), normalFont));
      if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
        table.addCell(new Phrase("$" + movimiento.getMonto() + "0", normalFont));
        table.addCell(new Phrase("", normalFont));
      } else {
        table.addCell(new Phrase("", normalFont));
        table.addCell(new Phrase("$" + movimiento.getMonto() + "0", normalFont));
      }
      table.addCell(new Phrase("$" + movimiento.getSaldo() + "0", normalFont));
    }
    
    document.add(table);
    
    
    
//    Anchor anchor = new Anchor("First Chapter", titleFont);
//    anchor.setName("First Chapter");
//
//    // Second parameter is the number of the chapter
//    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//    Paragraph subPara = new Paragraph("Subcategory 1", subTitleFont);
//    Section subCatPart = catPart.addSection(subPara);
//    subCatPart.add(new Paragraph("Hello"));
//
//    subPara = new Paragraph("Subcategory 2", subTitleFont);
//    subCatPart = catPart.addSection(subPara);
//    subCatPart.add(new Paragraph("Paragraph 1"));
//    subCatPart.add(new Paragraph("Paragraph 2"));
//    subCatPart.add(new Paragraph("Paragraph 3"));
//
//    // add a list
//    createList(subCatPart);
//    Paragraph paragraph = new Paragraph();
//    agregarSaltoLinea(paragraph, 5);
//    subCatPart.add(paragraph);
//
//    // add a table
//    createTable(subCatPart);
//
//    // now add all this to the document
//    document.add(catPart);
//
//    // Next section
//    anchor = new Anchor("Second Chapter", titleFont);
//    anchor.setName("Second Chapter");
//
//    // Second parameter is the number of the chapter
//    catPart = new Chapter(new Paragraph(anchor), 1);
//
//    subPara = new Paragraph("Subcategory", subTitleFont);
//    subCatPart = catPart.addSection(subPara);
//    subCatPart.add(new Paragraph("This is a very important message"));
//
//    // now add all this to the document
//    document.add(catPart);

  }
  
//  private static void createTable(Section subCatPart)
//      throws BadElementException {
//    PdfPTable table = new PdfPTable(3);
//
//    // t.setBorderColor(BaseColor.GRAY);
//    // t.setPadding(4);
//    // t.setSpacing(4);
//    // t.setBorderWidth(1);
//
//    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
//    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//    table.addCell(c1);
//
//    c1 = new PdfPCell(new Phrase("Table Header 2"));
//    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//    table.addCell(c1);
//
//    c1 = new PdfPCell(new Phrase("Table Header 3"));
//    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//    table.addCell(c1);
//    table.setHeaderRows(1);
//
//    table.addCell("1.0");
//    table.addCell("1.1");
//    table.addCell("1.2");
//    table.addCell("2.1");
//    table.addCell("2.2");
//    table.addCell("2.3");
//
//    subCatPart.add(table);
//
//  }
//  
//  private static void createList(Section subCatPart) {
//    List list = new List(true, false, 10);
//    list.add(new ListItem("First point"));
//    list.add(new ListItem("Second point"));
//    list.add(new ListItem("Third point"));
//    subCatPart.add(list);
//  }
  
  private static void agregarSaltoLinea(Document document, int number) throws DocumentException {
    for (int i = 0; i < number; i++) {
      document.add(new Paragraph(" "));
    }
  }
  
}
