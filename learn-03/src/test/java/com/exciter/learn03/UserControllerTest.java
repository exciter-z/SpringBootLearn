package com.exciter.learn03;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testList() throws Exception {
        //查询用户列表
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/users"));
        //相应状态码 200
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        //响应结果
        resultActions.andExpect(MockMvcResultMatchers.content().json("[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"username\": \"陈仲甫1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"username\": \"李守常1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"username\": \"胡适之1\"\n" +
                "    }\n" +
                "]"));
    }

    @Test
    public void testGet() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/users/1"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().json("{\n" +
                "    \"id\": 10,\n" +
                "    \"username\": \"userName:10\"\n" +
                "}"));
        resultActions.andDo(MockMvcResultHandlers.print());
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("拦截器数量：" + Objects.requireNonNull(mvcResult.getInterceptors()).length);
    }

    @Test
    public void testAdd() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/users")
                .param("username", "张铁柱")
                .param("password", "nicai"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    public void testUpdate() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.put("/users/1")
                .param("username", "niubi")
                .param("password", "123"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void testDelete() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.delete("users/1"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string("false"));
    }

}
