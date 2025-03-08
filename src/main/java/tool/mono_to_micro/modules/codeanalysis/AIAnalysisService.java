package tool.mono_to_micro.modules.codeanalysis;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class AIAnalysisService {

    private static final Logger logger = Logger.getLogger(AIAnalysisService.class.getName());
    private final OpenAiChatClient aiClient;

    public AIAnalysisService(OpenAiChatClient aiClient) {
        this.aiClient = aiClient;
    }

    public String generateMicroservicesReport(String codeAnalysis) {
        if (codeAnalysis == null || codeAnalysis.isEmpty()) {
            throw new IllegalArgumentException("Code analysis input cannot be empty.");
        }

        String promptText = """
                Role
                You are a highly experienced principal software architect and microservices transformation specialist.
                            Your job is to intelligently analyze the provided monolithic Java application
                            and produce a detailed, highly practical,and real-world transformation strategy for migrating it to microservices.
                            This must NOT be a shallow, theoretical, or high-level overview. Instead, it must be a concrete, structured implementation guide that a development team can immediately use as a blueprint.
                
                            Critical Rules for the Response-
                                        ✅ NO vague statements or generalizations (e.g., "similar structure as X" is strictly not allowed).
                                        ✅ Each microservice must be treated as a completely separate entity with full details.
                                           
                                        ✅ No skipping details—each service must include:
                            				-Unique folder structure (do not reuse previous structures blindly).
                                            -All necessary Java classes for that specific service.
                                            -Unique API endpoints and detailed request/response structures.
                                            -Independent Spring dependencies based on the service’s role.
                                            -Exact database schema, relationships, and storage strategy.
                                         
                
                
                
                
                
                            Your approach must be fully context-aware:
                                        First, deeply understand the monolith before suggesting microservices. (Deep Analysis of the Monolith
                                            -Identify architectural problems (tight coupling, scalability issues, domain boundary violations, etc.).
                                            -Extract actual entities, methods, and dependencies from the monolith.)
                
                                        Identify real problems in the monolith that make microservices necessary.
                                        Then, intelligently determine the number of microservices required—not too few, not too many and in correct order.
                
                                        For each microservice make sure you provide ( a) High-Level Responsibilities & Boundaries
                                            -What business logic does it handle?
                                            -Why does it need to be independent?
                                        
                                        
                                            b) Spring Dependencies
                                            -List all Spring Boot dependencies required (Spring Data JPA, Feign Client, Security, Kafka, etc.).
                                            -Justify why these dependencies are needed for this specific service.
                                        
                                            c) Strictly Unique Folder Structure (NO copying from previous services)
                                            -Provide full directory structure for each microservice.
                                            -Example:
                                            user-service/
                                            ├── src/
                                            │   ├── main/
                                            │   │   ├── java/
                                            │   │   │   ├── controller/
                                            │   │   │   │   └── UserController.java
                                            │   │   │   ├── service/
                                            │   │   │   │   └── UserService.java
                                            │   │   │   ├── repository/
                                            │   │   │   │   └── UserRepository.java
                                            │   │   │   ├── model/
                                            │   │   │   │   └── User.java
                                            │   │   │   ├── dto/
                                            │   │   │   │   └── UserDTO.java
                                            │   │   │   ├── security/
                                            │   │   │   │   └── SecurityConfig.java
                                            │   │   │   ├── feign/
                                            │   │   │   │   └── OrderServiceClient.java
                                            │   │   │   └── config/
                                            │   │   │       └── AppConfig.java
                                            │   │   └── resources/
                                            │   │       └── application.yml
                                            │   └── test/
                                            │       ├── java/
                                            │       └── resources/
                                            └── pom.xml
                                            The folder structure for each microservice must be unique—no blind repetition.)
                                            -a highly detailed breakdown, including its architecture, dependencies and technologies.
                                            -folder structure
                                            -In each folder how many and what java classes should be there ?
                                        It must not repeat anything or overlook by saying "same as previous one"  , it must smartly provide everything what each microservice need , Its obvious the no. of folders, classes and need will change with each microservice, so do not leave behind any details.
                                        
                                        Make sure that the analysis is not too shallow—it must not assume that we already know the monolith's structure.
                                        I mean what if the monolith has more complexities?
                                        so it should also tell about actual method-level insights ,Domain Models & Entities (actual class structure with key relationships).
                                        
                            			
                                        
                            			API Endpoints
                                             - Provide all the needed API endpoints for each microservice(API Endpoints (ALL required APIs per service)
                                            Clearly list each API with:
                                            -HTTP Method(all )
                                            -URL Path(all)
                                            -Request Body (if applicable)
                                            -Response Format
                                            -Status Codes)
                
                
                
                            			Database & Data Management Strategy
                                            -Should services share a database or have independent DBs?
                                            -Provide key tables and relationships per service.
                                        
                                        
                                        Inter-Service Communication & Event Handling
                                           -REST vs. Kafka: Clearly define which services use synchronous REST and which use asynchronous messaging.
                                           -Feign Client Usage: If a service calls another, explain:
                                           -When and where to use Feign.
                                           -How to implement Feign Clients.
                            			
                            			
                            			
                            			Service Dependencies & Interactions
                                            -Clearly list which microservices need to communicate and for what reason.
                                            -Example:
                                            Order Service needs to communicate with:
                                            -User Service to verify user identity.
                                            -Product Service to check stock availability.
                
                
                
                
                
    """ + codeAnalysis;

        try {
            logger.info("Sending structured prompt to OpenAI...");

            Prompt prompt = new Prompt(promptText);
            ChatResponse response = aiClient.call(prompt);

            String result = response.getResults().get(0).getOutput().getContent();
            logger.info("OpenAI Response: " + result);

            return result;
        } catch (Exception e) {
            logger.severe("OpenAI API request failed: " + e.getMessage());
            throw new RuntimeException("Failed to communicate with OpenAI API.", e);
        }
    }



}
