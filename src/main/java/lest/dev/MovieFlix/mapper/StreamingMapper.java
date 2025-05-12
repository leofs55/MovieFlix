package lest.dev.MovieFlix.mapper;

import lest.dev.MovieFlix.controller.request.StreamingRequest;
import lest.dev.MovieFlix.controller.response.StreamingResponse;
import lest.dev.MovieFlix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming map(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse map(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
