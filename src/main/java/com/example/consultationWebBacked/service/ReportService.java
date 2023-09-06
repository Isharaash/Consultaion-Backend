package com.example.consultationWebBacked.service;

import com.example.consultationWebBacked.entity.Appointment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {
    private static final String REPORTS_PATH = "C:\\Users\\ishar\\Desktop\\wwii\\"; // Replace with your desired path

    public String generatePdfReport(List<Appointment> appointments, String fileName) throws IOException, DocumentException {
        String filePath = REPORTS_PATH + fileName;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            writer.setPageEvent(new PageNumberEvent());
            document.open();
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("***** All Appointment Report ******", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f); // Adjust spacing after title
            document.add(title);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(110);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell cell = new PdfPCell(new Phrase("Full Name", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            // Add headers to the table


            cell = new PdfPCell(new Phrase("Job Type", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Job Type", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Appointment Date", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Appointment Time", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("E-mail", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Phone", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);





            // Add content to the table
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 10); // Customize the content font size
            for (Appointment appointment : appointments) {
                table.addCell(new PdfPCell(new Phrase(appointment.getfName(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getCategory(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getCountry(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getDate(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getTime(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getEmail(), contentFont)));
                table.addCell(new PdfPCell(new Phrase(appointment.getPhone(), contentFont)));
            }

            // Add the table to the document
            document.add(table);
        } finally {
            document.close();
        }

        return filePath;
    }
    private static class PageNumberEvent extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            int page = writer.getPageNumber();
            String pageNumberText = String.format("*** %d ***", page);

            // Set the font and size for the page number
            BaseFont bf = null;
            try {
                bf = BaseFont.createFont();
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
            cb.setFontAndSize(bf, 12);

            // Position the page number at the bottom center
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getBottom(30);
            float xPos = (width / 2) - (bf.getWidthPoint(pageNumberText, 12) / 2);

            // Add the page number to the page
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, pageNumberText, xPos, height, 0);
            cb.endText();
        }
    }

}
