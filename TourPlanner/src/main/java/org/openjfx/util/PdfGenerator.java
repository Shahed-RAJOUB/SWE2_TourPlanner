package org.openjfx.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Builder
@Component
@RequiredArgsConstructor
public class PdfGenerator {

      public void DownloadPdf() throws IOException{
          Document document = new Document();
          try
          {
              PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
              document.open();
              document.add(new Paragraph("A Hello World PDF document"));
              document.close();
              writer.close();
          } catch (DocumentException e)
          {
              e.printStackTrace();
          }
      }


}
