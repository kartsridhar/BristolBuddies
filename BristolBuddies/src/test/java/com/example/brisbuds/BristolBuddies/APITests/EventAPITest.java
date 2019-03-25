package com.example.brisbuds.BristolBuddies.APITests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.brisbuds.BristolBuddies.BristolBuddiesApplicationTests;
import com.example.brisbuds.BristolBuddies.controllers.EventController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class EventAPITest extends BristolBuddiesApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EventController eventController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testControllerNull() {
        assertThat(eventController).isNotNull();
    }

    //Application starts and then the test call will be made
    @Test
    public void testEvent() throws Exception {
        mockMvc.perform(get("/events")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id").value("2"))
                .andExpect(jsonPath("$[0].date").value("2019-06-15"))
                .andExpect(jsonPath("$[0].title").value("Table Tennis Socials"))
                .andExpect(jsonPath("$[0].description").value("Love ping ping? test your reaction time and show your experience to win exciting prizes!"))
                .andExpect(jsonPath("$[0].venue").value("The Courtrooms, BS1 2AF"))
                .andExpect(jsonPath("$[0].time").value("10am onwards"));

    }
}
