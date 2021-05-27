package org.rajoub.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.rajoub.business_layer.LogsService;
import org.rajoub.business_layer.ToursService;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

@Builder
@Component
@RequiredArgsConstructor
public class PdfGenerator {
    private final ToursService toursService;
    private final LogsService logsService;

    public void DownloadFullPdf() throws IOException {

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("FullReport_" + RandomString() + ".pdf"));
            //special font sizes
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
            DecimalFormat df = new DecimalFormat("0.00");

            document.open();
            document.addTitle("Full Tours Report");
            document.add(new Paragraph("The following PDF give a full report about all your Tours: "));
            document.add(new Paragraph(new Date(new java.util.Date().getTime()).toString()));
            document.addCreationDate();
            document.addAuthor("Chahed Rajoub _ if19b166 _ BIF4 _ C2");
            document.setPageSize(PageSize.LETTER);
            //specify column widths
            //create a paragraph
            Paragraph paragraph = new Paragraph("The Following Tables will list the Tours and the Logs of the Tours and each Tour total Distance and Burned Calories");
            for (int i = 0; i < toursService.GetTours().size(); i++) {
                float[] columnWidths = {1f, 3f, 2f, 2f, 2f};
                //create PDF table with the given widths

                PdfPTable table = new PdfPTable(columnWidths);
                // set table width a percentage of the page width
                table.setWidthPercentage(90f);

                //insert column headings
                insertCell(table, "Tour: " + toursService.GetTours().get(i).tourName, Element.ALIGN_LEFT, 5, bfBold12);
                insertCell(table, "Log No", Element.ALIGN_RIGHT, 1, bfBold12);
                insertCell(table, "Log Date", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Log Duration", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Log Distance", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Log Calories", Element.ALIGN_RIGHT, 1, bfBold12);
                table.setHeaderRows(1);
                float distTotal = 0, Caltotal = 0;
                //just some random data to fill
                for (int x = 0; x < logsService.GetLogs().size(); x++) {
                    if (logsService.GetLogs().get(x).tourName.equals(toursService.GetTours().get(i).tourName)) {
                        insertCell(table, logsService.GetLogs().get(x).id.toString(), Element.ALIGN_RIGHT, 1, bf12);
                        insertCell(table, logsService.GetLogs().get(x).date, Element.ALIGN_LEFT, 1, bf12);
                        insertCell(table, logsService.GetLogs().get(x).duration.toString(), Element.ALIGN_LEFT, 1, bf12);
                        insertCell(table, logsService.GetLogs().get(x).dest.toString(), Element.ALIGN_LEFT, 1, bf12);
                        insertCell(table, logsService.GetLogs().get(x).burnedCalories.toString(), Element.ALIGN_LEFT, 1, bf12);

                        distTotal = distTotal + logsService.GetLogs().get(x).dest;
                        Caltotal = Caltotal + logsService.GetLogs().get(x).burnedCalories;
                    }

                }
                //merge the cells to create a footer for that section
                insertCell(table, "Total", Element.ALIGN_RIGHT, 3, bfBold12);
                insertCell(table, df.format(distTotal), Element.ALIGN_RIGHT, 1, bfBold12);
                insertCell(table, df.format(Caltotal), Element.ALIGN_RIGHT, 1, bfBold12);
                //add the PDF table to the paragraph
                paragraph.add(table);

            }
            // add the paragraph to the document
            document.add(paragraph);
            document.add(new Paragraph("Chahed Rajoub _ if19b166 _ BIF4 _ C2"));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void DownloadTourPdf(String selectedTour) throws IOException {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("TourReport_" + RandomString() + ".pdf"));
            //special font sizes
            Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(FontFamily.COURIER, 12);
            DecimalFormat df = new DecimalFormat("0.00");

            document.open();
            document.addTitle("Full Tours Report");
            document.add(new Paragraph("The following PDF give a full report about the Tour that you chose: "));
            document.add(new Paragraph(new Date(new java.util.Date().getTime()).toString()));
            document.addCreationDate();
            document.addAuthor("Chahed Rajoub _ if19b166 _ BIF4 _ C2");
            document.setPageSize(PageSize.LETTER);
            //specify column widths
            //create a paragraph
            Paragraph paragraph = new Paragraph("The Image shows the Route that you took in this Tour:");
            Image img = Image.getInstance("images/test.jpg");
            img.scaleAbsolute(500f, 500f);
            document.add(img);
            float[] columnWidths = {1f, 3f, 2f, 2f, 2f};
            //create PDF table with the given widths

            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "Tour: " + selectedTour, Element.ALIGN_LEFT, 5, bfBold12);
            insertCell(table, "Log No", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "Log Date", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Log Duration", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Log Distance", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "Log Calories", Element.ALIGN_RIGHT, 1, bfBold12);
            table.setHeaderRows(1);
            float distTotal = 0, Caltotal = 0;
            //just some random data to fill
            for (int x = 0; x < logsService.GetLogs().size(); x++) {
                if (logsService.GetLogs().get(x).tourName.equals(selectedTour)) {
                    insertCell(table, logsService.GetLogs().get(x).id.toString(), Element.ALIGN_RIGHT, 1, bf12);
                    insertCell(table, logsService.GetLogs().get(x).date, Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, logsService.GetLogs().get(x).duration.toString(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, logsService.GetLogs().get(x).dest.toString(), Element.ALIGN_LEFT, 1, bf12);
                    insertCell(table, logsService.GetLogs().get(x).burnedCalories.toString(), Element.ALIGN_LEFT, 1, bf12);

                    distTotal = distTotal + logsService.GetLogs().get(x).dest;
                    Caltotal = Caltotal + logsService.GetLogs().get(x).burnedCalories;
                }
            }
            //merge the cells to create a footer for that section
            insertCell(table, "Total", Element.ALIGN_RIGHT, 3, bfBold12);
            insertCell(table, df.format(distTotal), Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, df.format(Caltotal), Element.ALIGN_RIGHT, 1, bfBold12);
            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            document.add(paragraph);
            document.add(new Paragraph("Chahed Rajoub _ if19b166 _ BIF4 _ C2"));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    private String RandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 3;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}

