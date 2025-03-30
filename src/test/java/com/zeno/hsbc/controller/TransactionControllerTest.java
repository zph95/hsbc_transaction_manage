package com.zeno.hsbc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTransactions() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transactions/all")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void testPageQueryTransactions() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/transactions/page")
                .param("page", "1")
                .param("size", "10")
                .param("isAsc", "true")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void testCreateTransaction() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \" payment123456345 \", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

        //transaction already exists
        mockMvc.perform(post("/transactions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

    }

    @Test
    public void testCreateTransactionAndReturnExists() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"payment123456\", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";


        //transaction already exists
        mockMvc.perform(post("/transactions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Transaction already exists"));

    }



    @Test
    public void testModifiedTransaction() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"payment123456\", \"type\": \"deposit\", \"amount\": 200.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

        //assert
        mockMvc.perform(post("/transactions/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

    }

    @Test
    public void testModifiedTransactionNotExist() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"payment9876632\", \"type\": \"deposit\", \"amount\": 200.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

        //assert
        mockMvc.perform(post("/transactions/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Transaction not found"));

    }

    @Test
    public void testDeleteTransaction() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"payment12345698978\", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

        //create transaction
        mockMvc.perform(post("/transactions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

        String tid = "payment12345698978";

        //delete and assert
        mockMvc.perform(delete("/transactions/delete/" + tid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("payment12345698978"));
    }

    @Test
    public void testDeleteTransactionReturnNotExist() throws Exception {

        String tid = "payment9876632";

        //assert
        mockMvc.perform(delete("/transactions/delete/" + tid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Transaction not found"));
    }

}