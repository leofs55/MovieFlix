package lest.dev.MovieFlix.service;

import lest.dev.MovieFlix.controller.request.StreamingRequest;
import lest.dev.MovieFlix.controller.response.StreamingResponse;
import lest.dev.MovieFlix.entity.Movie;
import lest.dev.MovieFlix.entity.Streaming;
import lest.dev.MovieFlix.mapper.StreamingMapper;
import lest.dev.MovieFlix.repository.CategoryRepository;
import lest.dev.MovieFlix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<StreamingResponse> list() {
        List<Streaming> streamingList = repository.findAll();
        return streamingList.stream()
                .map(StreamingMapper::map)
                .toList();
    }

    public StreamingResponse find(Long id) {
        Optional<Streaming> streaming = repository.findById(id);
        return StreamingMapper.map(streaming.orElse(null));
    }

    public StreamingResponse create(StreamingRequest body) {
        Streaming streaming = StreamingMapper.map(body);
        return StreamingMapper.map(repository.save(streaming));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
