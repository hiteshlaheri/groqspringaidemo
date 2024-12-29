package in.mbs.groq.groqdemo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class GroqRestApi {

    private final ChatClient chatClient;

    public GroqRestApi(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/airesponse")
    public Map<String,String> getaiResponse(@RequestParam String message){
        var response = chatClient.prompt()
                .user(message)
                .functions("weatherFunction") // reference by bean name.
                .call()
                .content();

        System.out.println(response);
        return Map.of("response",response);
    }
}
