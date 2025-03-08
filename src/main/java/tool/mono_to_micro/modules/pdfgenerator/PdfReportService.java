package tool.mono_to_micro.modules.pdfgenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

@Service
public class PdfReportService {

    private static final Logger logger = Logger.getLogger(PdfReportService.class.getName());

    public byte[] generatePdfReport(String content) {
        logger.info("📄 Starting PDF generation...");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            logger.info("✅ PDF Document Created!");

            // Add title
            document.add(new Paragraph("Microservices Analysis Report")
                    .setBold()
                    .setFontSize(20));

            // Add content
            document.add(new Paragraph(content));

            // Close document to finalize the PDF
            document.close();
            logger.info("✅ PDF successfully generated!");

            return outputStream.toByteArray();
        } catch (Exception e) {
            logger.severe("❌ PDF generation failed: " + e.getMessage());
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
