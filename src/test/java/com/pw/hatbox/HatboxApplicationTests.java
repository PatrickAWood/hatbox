package com.pw.hatbox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("!gcp")
class HatboxApplicationTests {

	@Test
	void contextLoads() {
	}

}
