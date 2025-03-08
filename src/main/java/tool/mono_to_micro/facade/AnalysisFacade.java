package tool.mono_to_micro.facade;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tool.mono_to_micro.modules.codeanalysis.AIAnalysisService;
import tool.mono_to_micro.modules.codeanalysis.CodeAnalysisService;
import tool.mono_to_micro.modules.pdfgenerator.PdfReportService;
import tool.mono_to_micro.modules.zipprocessor.ZipExtractorService;

import java.io.File;
import java.util.logging.Logger;

@Service
public class AnalysisFacade {

    private static final Logger logger = Logger.getLogger(AnalysisFacade.class.getName());

    private final ZipExtractorService zipExtractorService;
    private final CodeAnalysisService codeAnalysisService;
    private final AIAnalysisService aiAnalysisService;
    private final PdfReportService pdfReportService;

    public AnalysisFacade(ZipExtractorService zipExtractorService,
                          CodeAnalysisService codeAnalysisService,
                          AIAnalysisService aiAnalysisService,
                          PdfReportService pdfReportService) {
        this.zipExtractorService = zipExtractorService;
        this.codeAnalysisService = codeAnalysisService;
        this.aiAnalysisService = aiAnalysisService;
        this.pdfReportService = pdfReportService;
    }

    public byte[] analyzeZipFile(MultipartFile zipFile) {
        try {
            File extractedDir = zipExtractorService.extractZip(zipFile);
            String codeContent = codeAnalysisService.extractCodeFromFiles(extractedDir);
            String aiReport = aiAnalysisService.generateMicroservicesReport(codeContent);
            return pdfReportService.generatePdfReport(aiReport);
        } catch (Exception e) {
            logger.severe("Analysis failed: " + e.getMessage());
            throw new RuntimeException("Analysis failed.", e);
        }
    }
}