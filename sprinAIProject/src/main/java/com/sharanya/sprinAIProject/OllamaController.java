package com.sharanya.sprinAIProject;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {
    private ChatClient chatClient;

    public OllamaController(OllamaChatModel ollamaChatModel){ 
        this.chatClient =ChatClient.create(ollamaChatModel);
          }
 // public OllamaController(ChatClient.Builder builder){ 
           
  //              InMemoryChatMemoryRepository memoryRepository = new InMemoryChatMemoryRepository();

   //     ChatMemory chatMemory = MessageWindowChatMemory
   //         .builder()
   //         .chatMemoryRepository(memoryRepository)
    //          .maxMessages(50) 
      //        .build();

      //    MessageChatMemoryAdvisor memoryAdvisor = 
        //      MessageChatMemoryAdvisor.builder(chatMemory).build();

       //   this.chatClient = builder
        //      .defaultAdvisors(memoryAdvisor)
        //      .build();
       //         }

    @GetMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
      ChatResponse chatResponse=chatClient.prompt(message).call().chatResponse();
      System.out.println(chatResponse.getMetadata().getModel());
      String response =chatResponse.getResult().getOutput().getText();
        return ResponseEntity.ok(response);
    }
}
