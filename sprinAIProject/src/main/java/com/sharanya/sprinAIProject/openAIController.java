package com.sharanya.sprinAIProject;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class openAIController {
    //private OpenAiChatModel chatModel;
    private ChatClient chatClient;

    public  openAIController(OpenAiChatModel chatModel){
        this.chatClient =ChatClient.create(chatModel);
   }

   //  //public openAIController(ChatClient.Builder builder){ 
           //InMemoryChatMemoryRepository memoryRepository =
              //  new InMemoryChatMemoryRepository(); //when we use one model
    //advisor will help to fetch old message from memory
    //this.chatClient=builder.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
    //this.chatClient = builder
               // .chatMemory(memoryRepository)
               // .defaultAdvisors(new MessageChatMemoryAdvisor())
                //.build();
           

            //  // InMemoryChatMemoryRepository memoryRepository = new InMemoryChatMemoryRepository();

        // 2️⃣ Create a ChatMemory using the message-window strategy
 //     //  ChatMemory chatMemory = MessageWindowChatMemory
      //  //  .builder()
         //  //    .chatMemoryRepository(memoryRepository)
         //  //    .maxMessages(50)   // optional: how many messages to keep
         //  //    .build();

        // 3️⃣ Build the advisor with the correct builder API
       //  //  MessageChatMemoryAdvisor memoryAdvisor = 
       //  //      MessageChatMemoryAdvisor.builder(chatMemory).build();

        // 4️⃣ Build ChatClient with the advisor
       //  //  this.chatClient = builder
       //  //      .defaultAdvisors(memoryAdvisor)
        //  //     .build();
         //  //      }

   //  //  @GetMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
      //  String response =chatClient.prompt(message).call().content();
      ChatResponse chatResponse=chatClient.prompt(message).call().chatResponse();
      System.out.println(chatResponse.getMetadata().getModel());
      String response =chatResponse.getResult().getOutput().getText();
        return ResponseEntity.ok(response);
    }
}

