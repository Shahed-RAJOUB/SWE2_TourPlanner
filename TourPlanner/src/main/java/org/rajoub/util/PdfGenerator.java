package org.rajoub.util;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
@Log4j
public class PdfGenerator {

      public void DownloadPdf() throws IOException{
          Document document = new Document();
          try
          {
              PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
              document.open();
              document.addAuthor("Chahed Rajoub ( if19b166 )   ----- Date: "+ new Date());
              document.addTitle("Tours Report");

              document.add(new Paragraph("A Hello World PDF document"));
              document.close();
              writer.close();
          } catch (DocumentException e)
          {
              e.printStackTrace();
              log.error(e.getClass().getName() + ": " + e.getMessage());
          }
      }
    private static void createList(Section toursPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        toursPart.add(list);
    }



}
