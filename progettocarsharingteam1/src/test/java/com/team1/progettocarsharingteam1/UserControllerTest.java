package com.team1.progettocarsharingteam1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.progettocarsharingteam1.controllers.UserController;
import com.team1.progettocarsharingteam1.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createUser() throws Exception {
        User user = new User(18L,"Filippo","Rossi", LocalDate.parse("1995-05-01"),"gugdurhgugurgurgui@mail.com","fhfjiefirvnr45u6785g9","male","via Roma 1","h56757585",true,new ArrayList<>());

        String userJSON = objectMapper.writeValueAsString(user);

        MvcResult resultActions = this.mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(2)
    void updateStudentById() throws Exception {
        Long userId = 22L;
        User updatedUser = new User(22L,"Filippo","Bianchi", LocalDate.parse("1995-05-01"),"gugdurhgugurgurgui@mail.com","fhfjiefirvnr45u6785g9","male","via Roma 1","h56757585",true,new ArrayList<>());
        String userJSON = objectMapper.writeValueAsString(updatedUser);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/user/edit/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON).content(userJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);
    }

    @Test
    @Order(3)
    void readUserList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/all"))
                .andDo(print()).andReturn();

        List<User> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }

    @Test
    @Order(4)
    void getUser() throws Exception {
        Long userId = 22L;
        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/user/find/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId)).andReturn();
    }



    @Test
    @Order(5)
    void deleteUser() throws Exception {
        Long userId = 17L;
        MvcResult result = mockMvc.perform(delete("/user/delete/{id}",userId)
                        .param("id", String.valueOf(15L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    @Order(6)
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

}