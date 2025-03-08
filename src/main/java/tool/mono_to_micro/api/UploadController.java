package tool.mono_to_micro.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tool.mono_to_micro.facade.AnalysisFacade;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private static final Logger logger = Logger.getLogger(UploadController.class.getName());
    private final AnalysisFacade analysisFacade;

    public UploadController(AnalysisFacade analysisFacade) {
        this.analysisFacade = analysisFacade;
    }

    @PostMapping(value = "/analyze", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> analyzeZipFile(@RequestParam("file") MultipartFile zipFile) {
        try {
            if (zipFile.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Invalid file: File is empty.".getBytes());
            }

            // Process the ZIP file and generate the PDF
            byte[] pdfReport = analysisFacade.analyzeZipFile(zipFile);

            if (pdfReport == null || pdfReport.length == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to generate PDF.".getBytes());
            }

            // Set response headers to force file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Microservices_Report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);

        } catch (Exception e) {
            logger.severe("Error processing file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error: Failed to process file. " + e.getMessage()).getBytes());
        }
    }
}
