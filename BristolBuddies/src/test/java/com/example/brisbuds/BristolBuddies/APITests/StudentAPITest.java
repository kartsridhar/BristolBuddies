package com.example.brisbuds.BristolBuddies.APITests;

import com.example.brisbuds.BristolBuddies.BristolBuddiesApplicationTests;
import com.example.brisbuds.BristolBuddies.controllers.StudentController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class StudentAPITest extends BristolBuddiesApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentController studentController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testStudentControllerNull() {
        assertThat(studentController).isNotNull();
    }

    // Application starts then the test call will be made
    @Test
    public void testGetRespectiveStudent() throws Exception {
        mockMvc.perform(get("/students")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("Tony"))
                .andExpect(jsonPath("$[0].lastName").value("Stark"))
                .andExpect(jsonPath("$[0].userName").value("ab12312"))
                .andExpect(jsonPath("$[0].password").value("starkintdustreks"))
                .andExpect(jsonPath("$[0].department").value("Arts"));
    }
}
