package lest.dev.MovieFlix.controller;

import lest.dev.MovieFlix.controller.request.StreamingRequest;
import lest.dev.MovieFlix.controller.response.StreamingResponse;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streamings")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping
    public List<StreamingResponse> getAllStreamings(){
        return service.list();
    }

    @GetMapping("/{id}")
    public StreamingResponse getStreaming(@PathVariable Long id){
        return service.find(id);
    }

    @PostMapping
    public StreamingResponse postStreaming(@RequestBody StreamingRequest body){
        return service.create(body);
    }

    @DeleteMapping("/{id}")
    public void deleteStreaming(@PathVariable Long id){
        service.delete(id);
    }

}
