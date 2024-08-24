package com.stream.app.spring_stream_backend;

import com.stream.app.spring_stream_backend.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStreamBackendApplicationTests {
	@Autowired
	VideoService videoService;

	@Test
	void contextLoads() {

		//videoService.processVideo("Hemlata_Jha.png", null);
	}

}
