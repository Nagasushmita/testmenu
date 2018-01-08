package com1.example1.controllertest;


import com.example.Service.MenuService;
import com.example.controller.MenuController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.json.JSONObject;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@RunWith(SpringRunner.class)
@WebMvcTest(value = MenuController.class,secure = false)
public class testMenuController {
    @InjectMocks
    MenuController menuController;
    @Mock
    MenuService menuService;
private MockMvc mockMvc;
String json="{\"id\":1,\"name\":\"pizza\",\"price\":120}";
@Before
    public void setup() throws Exception{
    initMocks(this);
    mockMvc=standaloneSetup(menuController).build();

}

    @Test
    public void testGetAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/list")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void testToGetOnlyOneItem() throws Exception{
        MvcResult result=mockMvc.perform(get("/list/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response=result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void testToGetByPriceRange() throws Exception {
        MvcResult result=mockMvc.perform(get("/list/5/20")
                .accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response=result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void testUpdate() throws Exception {
        MvcResult result = mockMvc.perform(put("/list/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testDelete() throws Exception {
        MvcResult result = mockMvc.perform(delete("/list/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testPost() throws Exception {
        MvcResult result = mockMvc.perform(post("/list")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
       System.out.println(HttpStatus.OK.value());
        System.out.println(response.getStatus());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

/*

    @Test
    public void testToPost() throws Exception{
        MvcResult result=mockMvc.perform(post("/items")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
*/


}
