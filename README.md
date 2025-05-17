[![AGPL-3.0 License](https://img.shields.io/badge/License-AGPL%203.0-blue.svg)](https://www.gnu.org/licenses/agpl-3.0.html) This project is licensed under the **GNU AGPL-3.0 License** © 2025-2027 Anukul Kumar. See [LICENSE](./LICENSE) for more information.

<hr>

# Monolith to Microservices Analyzer 🚀
### Why I Built This Project ?
- While converting one of my **monolithic projects** to **microservices**, <br>
I realized that determining how many microservices to create and which classes should belong to each service was incredibly difficult. <br>
- There was no clear direction—just assumptions, trial-and-error, and no solid foundation.
- AI can help, but manually copying and pasting code to an AI model, explaining folder structures, flow, and dependencies, is tedious.
**If AI forgets previous contexts, you start from scratch**.

<br>

#### So, I built this tool! You upload your monolithic project (as a ZIP file), and AI analyzes it intelligently, providing:
- *How many microservices to create*
- *What classes should be in each service*
- *What folder structures to follow*
- *What HTTP methods and endpoints to implement*
- *What Spring dependencies are needed*
- *What database strategy to use*


<br>

This tool does not replace development, but it gives you a strong foundation. <br>
Once you get a structured breakdown, you can use AI (ChatGPT, Claude, Phind, etc.) to refine and implement each microservice one at a time.

<br>


## Key Features
- **Intelligent Microservice Breakdown:** Determines the number of microservices and suggests folder structures, controllers, services, and repositories.
- **API & Database Recommendations:** Provides REST API endpoints and recommends databases (e.g., MySQL, PostgreSQL, NoSQL) with table designs.
- **PDF Report Generation:** Delivers a structured, downloadable PDF report with the analysis.
- **Simple UI:** Drag-and-drop upload interface with progress indicators.

<br>

## Tech Stack 🛠️
- **Backend:** Java 17, Spring Boot, Spring AI (OpenAI API)
- **Frontend:** HTML, CSS, JavaScript (Basic UI)
- **AI Integration:** OpenAI (GPT-4 Turbo)
- **PDF Generation:** iText 7 (for structured reports)

<br>

<!--
## Features 🏗️

- **Upload a Monolithic Java Project.**
- **Accepts ZIP files containing Java code.**
- **Extracts and analyzes only .java files`**

<br>
<br>

### > Intelligent Microservice Breakdown
- AI determines how many microservices to create
- Suggests folder structures, controllers, services, repositories for each microservice

<br>

### > API & Database Recommendations
- Provides REST API endpoints for each microservice
- Recommends whether to use MySQL, PostgreSQL, or NoSQL
- Defines database tables and relationships

<br>


### > Generates a Structured PDF Report
- AI-generated analysis is converted to a PDF
- Downloadable after processing

<br>


### > Minimal Yet Clean UI
- Drag-and-drop upload box for ZIP files
- "Get PDF" button appears dynamically after upload
- Progress indicator while AI processes


<br>
<br>


## Prerequisites ⚙️
Before running this project, ensure you have: 
<br>


- Java 17+ installed
- Maven installed
- An OpenAI API Key (spring.ai.openai.api-key in application.properties)


<br>
<br>


## Dependencies
This project leverages Spring Boot for backend processing, iText PDF for report generation, and OpenAI API for AI-powered analysis.
<br>


### Key Dependencies (pom.xml)
- **Spring Boot Web** – REST API handling (spring-boot-starter-web)
- **Spring AI with OpenAI** – AI-driven analysis (spring-ai-openai-spring-boot-starter)
- **iText PDF** – PDF report generation (com.itextpdf:itext7-core)
- **Apache PDFBox** – Additional PDF processing (org.apache.pdfbox:pdfbox)
- **Lombok** – Reducing boilerplate (org.projectlombok:lombok)



<br>
<br>






## Folder Structure 📁
This project follows Modular Monolith Architecture, organizing services into dedicated modules instead of a single tangled structure.
<br>


```json
│── src/main/java/tool.mono_to_micro
|   |
│   │── application/          
│   │   │── MonoToMicroApplication.java
│   │
|   |── api/ 
|   |   │── UploadController.java
|   |
|   |---config
|   |   |---OpenAIConfig.java
|   |
|   |── facade/
|   |   │── AnalysisFacade.java 
|   |
│   │── modules/               # Modular Monolith Architecture
│   │   │── zipprocessor/       # Handles ZIP extraction
│   │   │   │── ZipExtractorService.java
│   │   │
│   │   │── codeanalysis/       # Extracts & processes Java code
│   │   │   │── CodeAnalysisService.java
│   │   │   │── AIAnalysisService.java
│   │   │
│   │   │── pdfgenerator/       # Generates transformation report
│   │   │   │── PdfReportService.java


```


<br>
<br>



## Detailed Class Breakdown 🛠️
### 1️. MonoToMicroApplication.java (Main Application)
- Entry point of the Spring Boot application
- Enables CORS to allow frontend communication

<br>
<br>


### 2️. UploadController.java (API Layer)
- Handles HTTP requests for file uploads(/api/upload/analyze)
- Validates ZIP files
- Returns a generated PDF as a response

<br>

- Key Method:


` @PostMapping(value = "/analyze", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<byte[]> analyzeZipFile(@RequestParam("file") MultipartFile zipFile) { }`



<br>
<br>

### 3️. OpenAIConfig.java (Configuration Layer)
- Configures OpenAI API integration
- Sets API key and base URL for OpenAI

<br>
<br>


### 4️. AnalysisFacade.java (Business Logic Layer)
- Calls ZipExtractorService → Extracts Java files
- Calls CodeAnalysisService → Reads and extracts Java code
- Calls AIAnalysisService → Sends code to OpenAI for analysis
- Calls PdfReportService → Generates structured PDF from AI response


#### Basically-
- Orchestrates the entire transformation process
- Extracts ZIP files
- Processes Java files
- Sends them to AI
- Generates a structured PDF


<br>
<br>


### 5️. ZipExtractorService.java (ZIP Processing)
- Extracts files from uploaded ZIP
- Prevents ZIP Slip Vulnerability (Ensures safe file extraction)

#### ZIP Slip Protection:
- *Ensures extracted files remain inside the expected directory*
- *Uses canonical path validation to prevent malicious path traversal*

<br>
<br>

### 6️. CodeAnalysisService.java (Extracts Java Code)
- Reads only .java files from extracted ZIP
- Ignores XML, properties, frontend files

<br>
<br>


### 7️. AIAnalysisService.java (AI-Powered Analysis)
- Sends structured monolith analysis to OpenAI using a well-crafted prompt.
- Calls OpenAI API (GPT-4 Turbo)
- Extracts microservices breakdown from AI response


#### Receives microservice transformation strategy, including:
- Number of microservices
- Folder structure
- API endpoints
- Database schemas
- Inter-service communication methods

<br>
<br>

### 8️. PdfReportService.java (PDF Generation)
- Converts AI-generated analysis into a downloadable PDF document



<br>
<br>



-->

## How to Use
- **Upload:** Drag and drop your monolithic Java project as a ZIP file.
- **Analyze:** The tool extracts and analyzes the Java files using AI.
- **Download:** Receive a PDF report with microservices recommendations.

<br>


## API Documentation 📡
### > Upload ZIP & Analyze
- **Description:** Uploads a monolithic Java project (ZIP) and returns a PDF transformation report.
- **Endpoint:** POST /api/upload/analyze
- **Method:** POST
- **Content-Type:** multipart/form-data/application/pdf
- **Success:** Returns the generated PDF for download.
- **Failure:** Returns an error message.

<br>


#### > Request:
  - **file:** ZIP file containing monolithic project

  <br>
  
#### > Response:
  - **200 OK:** PDF File (Microservices Analysis Report)
  - **400 Bad Request:** "Invalid file: File is empty."
  - **500 Internal Server Error:** "Error processing file"

 <br>
 
#### > Example Postman Test:
- **POST >**  http://localhost:8080/api/upload/analyze

<br>


#### > Body (form-data):
  - **file:** (Upload ZIP file)

<br>

#### > Response (PDF Download)
  
  
  
<br>

<!--

## How It Works ⚙️

### 1️. User Uploads a Monolithic Java Project
- Frontend provides a simple drag-and-drop interface
- User uploads a ZIP file containing Java files



### 2️. Backend Extracts & Analyzes Code
- ZipExtractorService extracts .java files
- CodeAnalysisService reads code
- AIAnalysisService calls OpenAI for deep microservices analysis



### 3️. AI Generates a Microservices Breakdown
- Determines how many microservices are needed
- Suggests folder structures, API endpoints, and databases
- Defines REST methods and inter-service communication



### 4️. PDF Report is Generated & Downloaded
- PdfReportService creates a structured Microservices Analysis Report


#### Including:
- Recommended microservices
- Folder structures
- API documentation
- Database design

<br>

- User clicks "**Get PDF**" button → Downloads report

<br>

-->

## Workflow
- **Upload:** User submits a ZIP file.
- **Extract:** Java files are extracted safely.
- **Analyze:** Code is sent to OpenAI for microservices analysis.
- **Report:** AI output is converted to a PDF.
- **Download:** User downloads the PDF report.

<br>

## Backend Logs for Debugging 📝
<br>


`2025-03-08T20:29:24.446+05:30 INFO AIAnalysisService: Sending structured prompt to OpenAI...` <br>

`2025-03-08T20:29:46.618+05:30 INFO AIAnalysisService: OpenAI Response: ### Analysis of the Monolithic Application` <br>

`2025-03-08T20:29:46.619+05:30 INFO PdfReportService: 📄 Starting PDF generation...` <br>

`2025-03-08T20:29:46.763+05:30 INFO PdfReportService: ✅ PDF Document Created!` <br>

`2025-03-08T20:29:47.002+05:30 INFO PdfReportService: ✅ PDF successfully generated!` <br>



<br>





## 📸 Demo

![Screenshot 2025-03-08 195908](https://github.com/user-attachments/assets/92ecd653-9fd3-460e-8263-1d12dcae60c8)
<br>

![Screenshot 2025-03-08 195929](https://github.com/user-attachments/assets/b17db7bf-7617-4226-b422-9856dc384b50)
<br>

![Screenshot 2025-03-08 195948](https://github.com/user-attachments/assets/8541fb9e-e3cd-4933-930a-9b9e2c3c447f)
<br>

![Screenshot 2025-03-08 200027](https://github.com/user-attachments/assets/20803e7d-33a2-4d21-8a2b-d4bd3bc91af9)
<br>

![Screenshot 2025-03-08 200320](https://github.com/user-attachments/assets/e0c99adc-f309-408d-9e2f-3bbb07954247)

<br>

<br>

- **logs:**
<br>

![Screenshot 2025-03-08 200044](https://github.com/user-attachments/assets/22a46cb1-1a2b-4db0-9dbb-b07892bee216)

<br>

![Screenshot 2025-03-08 200350](https://github.com/user-attachments/assets/ee176794-98c3-4723-88aa-efb818b81fcf)


<br>

<hr>

#### Working Video:

https://github.com/user-attachments/assets/67685c60-4837-4320-b28d-dad59f30171d



<!--


<br>

<br>

<hr>




I understand that an ideal version of this tool would not only analyze the monolith but also generate a fully functional ZIP containing microservices with basic, auto-generated code and classes. 
<br>
However, there are a few challenges:
- **AI Limitations –** AI models often provide incomplete or inconsistent code, which means relying solely on automated generation could lead to broken or inefficient microservices, ultimately wasting time on debugging rather than building.
- **Technical Constraints –** Generating fully structured, production-ready microservices requires a level of complexity beyond what can be achieved using just an AI model and an API key.

<br>

#### Future Improvements
<br>
I plan to enhance the AI analysis further, making it even more thorough, precise, and actionable to help developers transition from monolith to microservices with greater confidence.
<br>
While this project currently focuses only on analyzing Java-based monolithic applications, I plan to extend support for other languages and frameworks. 

<br>

<br>

<hr>
-->
<hr>

# Contact
For any questions, feedback, or contributions, feel free to reach out: <br>
**Email:** *anukulmaurya18@gmail.com* <br>
**This README will be updated as the project progresses. Stay tuned!**
#### This project bridges the gap between monolithic confusion and structured microservices transformation. 🚀
