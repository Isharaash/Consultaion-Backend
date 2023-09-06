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

            // Add headers to the table
            table.addCell("Full Name");
            table.addCell("Job Type");
            table.addCell("Country");
            table.addCell("Appointment Date");
            table.addCell("Appointment Time");
            table.addCell("E-mail");
            table.addCell("Phone");



            // Add content to the table
            for (Appointment appointment : appointments) {
                table.addCell(appointment.getfName());
                table.addCell(appointment.getCategory());
                table.addCell(appointment.getCountry());
                table.addCell(appointment.getDate());
                table.addCell(appointment.getTime());
                table.addCell(appointment.getEmail());
                table.addCell(appointment.getPhone());

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
