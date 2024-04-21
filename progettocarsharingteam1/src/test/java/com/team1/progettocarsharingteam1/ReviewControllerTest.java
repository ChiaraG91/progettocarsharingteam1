package com.team1.progettocarsharingteam1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.progettocarsharingteam1.controllers.RentController;
import com.team1.progettocarsharingteam1.controllers.ReviewController;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.RatingEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewControllerTest {

    @Autowired
    private ReviewController reviewController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createReview() throws Exception {

        Review review = new Review(1L,"Marco","ghfjoingingirn", RatingEnum.QUATTRO,new Rent());

        String reviewJSON = objectMapper.writeValueAsString(review);

        MvcResult resultActions = this.mockMvc.perform(post("/review/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(2)
    void updateReviewById() throws Exception {
        Long rentId = 1L;
        Review updatedReview = new Review(1L,"Marco","ghfjoingingirn", RatingEnum.QUATTRO,new Rent());
        String reviewJSON = objectMapper.writeValueAsString(updatedReview);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/review/edit/{id}", rentId)
                        .contentType(MediaType.APPLICATION_JSON).content(reviewJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);
    }

    @Test
    @Order(3)
    void readReviewList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/review/all"))
                .andDo(print()).andReturn();

        List<Review> reviewFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(reviewFromResponseList.size()).isNotZero();
    }

    @Test
    @Order(4)
    void getReview() throws Exception {
        Long reviewId = 1L;
        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/review/find/{id}", reviewId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(reviewId)).andReturn();
    }



    @Test
    @Order(5)
    void deleteReview() throws Exception {
        Long reviewId = 1L;
        MvcResult result = mockMvc.perform(delete("/review/delete/{id}",reviewId)
                        .param("id", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    @Order(6)
    void contextLoads() {
        assertThat(reviewController).isNotNull();
    }
}
