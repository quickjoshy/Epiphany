package com.example.MessageSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
public class MessageController {


    public MessageController(MessageRepository repository){
        this.repository = repository;
    }

    private final MessageRepository repository;

    @PostMapping("/CreateMessage")
    @CrossOrigin(origins = "http://localhost:3030")
    public Message CreateMessage(@RequestBody String text){
        Message message = new Message(text);
        return repository.save(message);
    }

    @GetMapping({"/RandomMessage", "/"})
    @CrossOrigin(origins = "http://localhost:3030")
    public Message RandomMessage(){
        Random random = new Random();
        long size = repository.count();
        long index = random.nextLong(1, size + 1);
        System.out.printf("Data entry count: %d\n Index: %d\n", size, index);
        return repository.findById(index).orElseThrow(() -> new RuntimeException(String.format("index: %d", index)));
    }

    @PutMapping("AddLike/{id}")
    @CrossOrigin(origins = "http://localhost:3030")
    public Message AddLike(@PathVariable long id){
        Message message = repository.findById(id).orElseThrow(() -> new RuntimeException("Can't add like."));
        message.setLikes(message.getLikes() + 1);
        return repository.save(message);
    }

    @PutMapping("AddDislike/{id}")
    @CrossOrigin(origins = "http://localhost:3030")
    public Message AddDislike(@PathVariable long id){
        Message message = repository.findById(id).orElseThrow(() -> new RuntimeException("Can't add like."));
        message.setDislikes(message.getDislikes() + 1);
        return repository.save(message);
    }

}
