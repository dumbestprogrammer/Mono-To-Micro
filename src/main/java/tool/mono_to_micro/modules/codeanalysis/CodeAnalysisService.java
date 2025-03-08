package tool.mono_to_micro.modules.codeanalysis;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
public class CodeAnalysisService {

    public String extractCodeFromFiles(File directory) throws IOException {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory provided.");
        }

        return Files.walk(directory.toPath())
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".java")) // ✅ Only process Java files
                .flatMap(path -> {
                    try {
                        return Files.lines(path);
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading file: " + path, e);
                    }
                })
                .collect(Collectors.joining("\n\n"));
    }
}
