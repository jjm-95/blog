package com.example.blog.Hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Return Hello")
    void hello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    } // hello

    @Test
    @DisplayName("Return HelloDto")
    void ReturnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        
        // 테스트 내용
        // /hello/dto를 get 한후 param에 값을 필드에서 선언된 값을 넣음
        // 1. 정상적으로 호출 되는지 확인 
        // 2. 호출된 json 값과 필드의 값을 대조하여 올바르게 전달 됬는지 확인
        
    } // ReturnHelloDto


} // HelloControllerTest
