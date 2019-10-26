package pdf_generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class PdfGenerator {
    public static final String PDF_PATH = "web/img/qrcode/buchung.pdf";

    public static void createPdf () {
        Document document = new Document();
        try {
            //Initialize PDF-writer and PDF-Document
            PdfWriter.getInstance(document, new FileOutputStream(PDF_PATH));
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);


            Paragraph p = new Paragraph("", font);
            Chunk chunk = new Chunk("Buchung", font);
            Image img = Image.getInstance("web/img/qrcode/MyQRCode.png");
            document.add(chunk);
            document.add(img);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createPdf();
    }
}
