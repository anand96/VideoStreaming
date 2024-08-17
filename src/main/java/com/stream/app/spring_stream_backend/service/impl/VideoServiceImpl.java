package com.stream.app.spring_stream_backend.service.impl;

import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.repository.VideoRepository;
import com.stream.app.spring_stream_backend.service.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Value("${files.video}")
    String DIR;

    private VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostConstruct
    public void init(){
        File file = new File(DIR);
        if(!file.exists())
        {
            file.mkdir();
            System.out.println("Folder created");
        }
        else{
            System.out.println("Folder older created");
        }

    }

    @Override
    public Video save(Video video, MultipartFile file) {

        try {
            //Original File name
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            //folder path : create
            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(DIR);

             Path path = Paths.get(cleanFolder,cleanFileName);
             System.out.println(path);
             System.out.println(contentType);


            //folder path with filename

            //copy file to the folder
            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);

            //video meta data
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            videoRepository.save(video);

            return videoRepository.save(video);

            //metadata data
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video get(String videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        return video;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}
