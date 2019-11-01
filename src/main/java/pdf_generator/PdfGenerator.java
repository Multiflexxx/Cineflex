package pdf_generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import helper.DateFormatter;
import oo.*;
import java.io.FileOutputStream;
import java.io.IOException;


public class PdfGenerator {

    /**
     *
     * @param pathPDF
     * @param pathQR
     * @param buchungsbeleg
     * @param vorstellung
     * @param sitze
     * @param kunde
     * @throws IOException
     * @throws DocumentException
     */
    public static void createBuchungsPDF(String pathPDF, String pathQR, Buchungsbeleg buchungsbeleg, Vorstellung vorstellung, Sitz[] sitze, Kunde kunde) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(pathPDF));
        document.open();

        Font titel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);
        Font subtitel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        Font smalltext = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
        Font text = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        document.add(new Paragraph("Deine Buchung im Cineflex " + vorstellung.getSaal().getGebaeude().getOrtsname() +  " by Multiflexxx", titel));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        float[] colWidht = {2f, 2f, 2f};
        table.setWidths(colWidht);
        PdfPCell c0 = new PdfPCell(new Phrase("", text));
        PdfPCell c1 = new PdfPCell(new Phrase("Kundennummer: " + kunde.getKundenID(), smalltext));
        PdfPCell c2 = new PdfPCell(new Phrase("Rechnungsnummer: " + kunde.getKundenID() + buchungsbeleg.getBelegID(), smalltext));

        c0.setBorder(Rectangle.NO_BORDER);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(c0);
        table.addCell(c1);
        table.addCell(c2);

        document.add(table);

        Paragraph thx = new Paragraph("Danke " + kunde.getVorname() + " für deine Buchung auf Ehrenbruder Basis!", subtitel);
        thx.setIndentationLeft(20);
        thx.setSpacingAfter(10f);

        document.add(thx);

        Image qr = Image.getInstance(pathQR);
        qr.scaleAbsolute(100, 100);

        List oList = new List(List.ORDERED);
        oList.add(new ListItem("Drucke dir diese Buchunsbestätigung aus oder speicher sie dir umweltbewusst auf deinem Smartphone ab."));
        oList.add(new ListItem("Zeige dem Kinopersonal die Buchungsbestätigung vor dem Kinosaal."));
        oList.add(new ListItem("Viel Spaß bei der Vorstellung wünscht dir dein Multiflexxx Team!"));

        PdfPCell c01 = new PdfPCell();
        c01.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        c01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c01.addElement(qr);
        PdfPCell c02 = new PdfPCell();
        c02.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        c02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c02.addElement(oList);

        c01.setBorder(Rectangle.NO_BORDER);
        c02.setBorder(Rectangle.NO_BORDER);

        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.setSpacingBefore(20f);
        table1.setSpacingAfter(20f);
        float[] colWidht1 = {2f, 4f};
        table1.setWidths(colWidht1);

        table1.addCell(c01);
        table1.addCell(c02);

        document.add(table1);

        Paragraph p = new Paragraph(vorstellung.getFilm().getTitel() + ": Am " + DateFormatter.getFrontendDate(vorstellung.getDatum()) + " um " + DateFormatter.getFrontendTime(vorstellung.getUhrzeit()) + " Uhr", subtitel);
        p.setIndentationLeft(30);
        p.setSpacingAfter(10f);
        document.add(p);

        List uList = new List(List.UNORDERED);
        uList.setIndentationLeft(50);
        for (int i = 0; i<sitze.length; i++) {
            uList.add(new ListItem("Ticket " + (i + 1) + ": " + vorstellung.getSaal().getBezeichnung() + " Reihe " + sitze[i].getReihe() + " Platz " + sitze[i].getNummer(), text));
        }
        document.add(uList);

        PdfPTable tablef = new PdfPTable(2);
        tablef.setWidthPercentage(100);
        tablef.setSpacingBefore(60f);

        float[] colWidhtf = {3f, 3f};
        tablef.setWidths(colWidhtf);
        PdfPCell c0f = new PdfPCell(new Phrase("" + vorstellung.getSaal().getGebaeude().getStrasse() + " " + vorstellung.getSaal().getGebaeude().getHausnummer() + ", " + vorstellung.getSaal().getGebaeude().getPlz() + " " + vorstellung.getSaal().getGebaeude().getOrtsname(), smalltext));
        PdfPCell c1f = new PdfPCell(new Phrase("Bei Fragen schreibe uns gerne eine E-Mail an noreply@multiflexxx.de", smalltext));

        c0f.setBorder(Rectangle.NO_BORDER);
        c0f.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1f.setBorder(Rectangle.NO_BORDER);
        c1f.setHorizontalAlignment(Element.ALIGN_LEFT);

        tablef.addCell(c0f);
        tablef.addCell(c1f);

        document.add(tablef);

        //Close document
        document.close();
    }

    /**
     *
     * @param pathPDF
     * @param pathQR
     * @param reservierungsbeleg
     * @param vorstellung
     * @param sitze
     * @param kunde
     * @throws IOException
     * @throws DocumentException
     */
    public static void createReservierungsPDF(String pathPDF, String pathQR, Reservierungsbeleg reservierungsbeleg, Vorstellung vorstellung, Sitz[] sitze, Kunde kunde) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(pathPDF));
        document.open();

        Font titel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);
        Font subtitel = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        Font smalltext = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
        Font text = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        document.add(new Paragraph("Deine Reservierung im Cineflex " + vorstellung.getSaal().getGebaeude().getOrtsname() +  " by Multiflexxx", titel));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        float[] colWidht = {2f, 2f, 2f};
        table.setWidths(colWidht);
        PdfPCell c0 = new PdfPCell(new Phrase("", text));
        PdfPCell c1 = new PdfPCell(new Phrase("Kundennummer: " + kunde.getKundenID(), smalltext));
        PdfPCell c2 = new PdfPCell(new Phrase("Rechnungsnummer: " + kunde.getKundenID() + reservierungsbeleg.getBelegID(), smalltext));

        c0.setBorder(Rectangle.NO_BORDER);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(c0);
        table.addCell(c1);
        table.addCell(c2);

        document.add(table);

        Paragraph thx = new Paragraph("Danke " + kunde.getVorname() + " für deine Reservierung auf Ehrenbruder Basis!", subtitel);
        thx.setIndentationLeft(20);
        thx.setSpacingAfter(10f);

        document.add(thx);

        Image qr = Image.getInstance(pathQR);
        qr.scaleAbsolute(100, 100);

        List oList = new List(List.ORDERED);
        oList.add(new ListItem("Drucke dir diese Buchunsbestätigung aus oder speicher sie dir umweltbewusst auf deinem Smartphone ab."));
        oList.add(new ListItem("Sei min. 30 Minuten vor Vorstellungsbeginn vorort, ansonsten verfällt deine Reservierung!"));
        oList.add(new ListItem("Lege dem Kinopersonal an der Kasse die Reservierungsbestätigung vor."));
        oList.add(new ListItem("Bezahle deine Tickets"));
        oList.add(new ListItem("Viel Spaß bei der Vorstellung wünscht dir dein Multiflexxx Team!"));

        PdfPCell c01 = new PdfPCell();
        c01.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        c01.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c01.addElement(qr);
        PdfPCell c02 = new PdfPCell();
        c02.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        c02.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c02.addElement(oList);

        c01.setBorder(Rectangle.NO_BORDER);
        c02.setBorder(Rectangle.NO_BORDER);

        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.setSpacingBefore(20f);
        table1.setSpacingAfter(20f);
        float[] colWidht1 = {2f, 4f};
        table1.setWidths(colWidht1);

        table1.addCell(c01);
        table1.addCell(c02);

        document.add(table1);

        Paragraph p = new Paragraph(vorstellung.getFilm().getTitel() + ": Am " + DateFormatter.getFrontendDate(vorstellung.getDatum()) + " um " + DateFormatter.getFrontendTime(vorstellung.getUhrzeit()) + " Uhr", subtitel);
        p.setIndentationLeft(30);
        p.setSpacingAfter(10f);
        document.add(p);

        List uList = new List(List.UNORDERED);
        uList.setIndentationLeft(50);
        for (int i = 0; i<sitze.length; i++) {
            uList.add(new ListItem("Ticket " + (i + 1) + ": " + vorstellung.getSaal().getBezeichnung() + " Reihe " + sitze[i].getReihe() + " Platz " + sitze[i].getNummer(), text));
        }
        document.add(uList);

        PdfPTable tablef = new PdfPTable(2);
        tablef.setWidthPercentage(100);
        tablef.setSpacingBefore(60f);

        float[] colWidhtf = {3f, 3f};
        tablef.setWidths(colWidhtf);
        PdfPCell c0f = new PdfPCell(new Phrase("" + vorstellung.getSaal().getGebaeude().getStrasse() + " " + vorstellung.getSaal().getGebaeude().getHausnummer() + ", " + vorstellung.getSaal().getGebaeude().getPlz() + " " + vorstellung.getSaal().getGebaeude().getOrtsname(), smalltext));
        PdfPCell c1f = new PdfPCell(new Phrase("Bei Fragen schreibe uns gerne eine E-Mail an noreply@multiflexxx.de", smalltext));

        c0f.setBorder(Rectangle.NO_BORDER);
        c0f.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1f.setBorder(Rectangle.NO_BORDER);
        c1f.setHorizontalAlignment(Element.ALIGN_LEFT);

        tablef.addCell(c0f);
        tablef.addCell(c1f);

        document.add(tablef);

        //Close document
        document.close();
    }
}