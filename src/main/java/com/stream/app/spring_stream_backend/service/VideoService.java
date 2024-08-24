package com.stream.app.spring_stream_backend.service;

import com.stream.app.spring_stream_backend.entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    //save video
    Video save(Video video, MultipartFile file);

    Video get(String videoId);
    // get video by id

    // get video by title
    Video getByTitle(String title);

    List<Video> getAll();

    //video Processing
    String processVideo(String videoId);
}
