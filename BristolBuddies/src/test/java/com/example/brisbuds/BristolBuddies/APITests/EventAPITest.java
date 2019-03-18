package com.example.brisbuds.BristolBuddies.APITests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.brisbuds.BristolBuddies.BristolBuddiesApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class EventAPITest extends BristolBuddiesApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Application starts and then the test call will be made
    @Test
    public void testEvent() throws Exception {
        mockMvc.perform(get("/events")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.date").value("2019-06-15"))
                .andExpect(jsonPath("$.title").value("Table Tennis Socials"))
                .andExpect(jsonPath("$.description").value("Love ping ping? test your reaction time and show your experience to win exciting prizes!"))
                .andExpect(jsonPath("$.venue").value("The Courtrooms, BS1 2AF"))
                .andExpect(jsonPath("$.time").value("10am onwards"));
    }
}
