package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean)을 주입 받습니다.
    private MockMvc mvc;

    @Test
    public void helloPrint() throws Exception {
         String hello = "hello";

         mvc.perform(get("/hello"))
                 .andExpect(status().isOk())
                 .andExpect(content().string(hello));
    }

    @Test
    public void helloDtoPrint() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform( get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
   }
}
