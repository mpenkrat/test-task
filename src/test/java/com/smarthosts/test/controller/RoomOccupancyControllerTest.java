package com.smarthosts.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomOccupancyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetRoomOccupancy_Test1() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?economy=3&premium=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'ECONOMY','roomsCount':3,'total':{'amount':167,'currency':'EUR'}}," +
                                "{'roomType':'PREMIUM','roomsCount':3,'total':{'amount':738,'currency':'EUR'}}]]"));
    }

    @Test
    public void testGetRoomOccupancy_Test2() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?premium=7&economy=5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'ECONOMY','roomsCount':4,'total':{'amount':189,'currency':'EUR'}}," +
                                "{'roomType':'PREMIUM','roomsCount':6,'total':{'amount':1054,'currency':'EUR'}}]]"));
    }

    @Test
    public void testGetRoomOccupancy_Test3() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?premium=2&economy=7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'ECONOMY','roomsCount':4,'total':{'amount':189,'currency':'EUR'}}," +
                                "{'roomType':'PREMIUM','roomsCount':2,'total':{'amount':583,'currency':'EUR'}}]]"));
    }

    @Test
    public void testGetRoomOccupancy_Test4() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?premium=7&economy=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'ECONOMY','roomsCount':1,'total':{'amount':45,'currency':'EUR'}}," +
                                "{'roomType':'PREMIUM','roomsCount':7,'total':{'amount':1153,'currency':'EUR'}}]]"));
    }

    @Test
    public void testGetRoomOccupancy_OnlyEconomy() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?economy=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'ECONOMY','roomsCount':3,'total':{'amount':167,'currency':'EUR'}}]"));
    }

    @Test
    public void testGetRoomOccupancy_OnlyPremium() throws Exception {
        mvc.perform(get("/api/v1/rooms/occupancy?premium=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{'roomType':'PREMIUM','roomsCount':2,'total':{'amount':583,'currency':'EUR'}}]"));
    }
}

