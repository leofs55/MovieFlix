package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.controller.request.StreamingRequest;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.mapper.StreamingMapper;
import lest.dev.MovieFlix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> list() {
        List<Streaming> streamingList = repository.findAll();
        return streamingList;
    }

    public Optional<Streaming> find(Long id) {
        Optional<Streaming> streaming = repository.findById(id);
        return streaming;
    }

    public Streaming create(StreamingRequest body) {
        Streaming streaming = StreamingMapper.map(body);
        return repository.save(streaming);
    }

    public boolean delete(Long id) {
        Optional<Streaming> streaming = repository.findById(id);
        if (streaming.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
