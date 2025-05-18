package lest.dev.MovieFlix.controller;

import jakarta.validation.Valid;
import lest.dev.MovieFlix.controller.request.StreamingRequest;
import lest.dev.MovieFlix.controller.response.StreamingResponse;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.mapper.StreamingMapper;
import lest.dev.MovieFlix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streamings")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        List<StreamingResponse> list = service.list().stream()
                .map(StreamingMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreaming(@PathVariable Long id) {
        return service.find(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.map(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> postStreaming(@Valid
                                                           @RequestBody StreamingRequest body) {
        Streaming streaming = service.create(body);
        if (streaming != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.map(streaming));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StreamingResponse> patch(@PathVariable Long id, @RequestBody StreamingRequest body) {
        Streaming streaming = StreamingMapper.map(body);
        return service.patch(id, streaming)
                .map(streamingSaved -> ResponseEntity.ok(StreamingMapper.map(streamingSaved)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStreaming(@PathVariable Long id) {
        boolean res = service.delete(id);
        if (res) {
            return ResponseEntity.ok("Streaming deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
