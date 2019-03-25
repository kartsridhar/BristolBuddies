package com.example.brisbuds.BristolBuddies;

import com.example.brisbuds.BristolBuddies.controllers.EventController;
import com.example.brisbuds.BristolBuddies.controllers.StudentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest({EventController.class, StudentController.class})
@ContextConfiguration(classes = {BristolBuddiesApplication.class})
//@SpringBootTest
public class BristolBuddiesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
