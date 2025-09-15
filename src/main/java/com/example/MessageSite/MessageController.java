package com.example.MessageSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    public MessageController(MessageRepository repository){
        this.repository = repository;
    }

    private final MessageRepository repository;

    @PostMapping("/CreateMessage")
    public Message CreateMessage(@RequestBody String text){
        Message message = new Message(text);
        return repository.save(message);
    }

}
