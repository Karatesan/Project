package com.fdmgroup.Confidential_secret_project.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.Confidential_secret_project.ConfidentialSecretProjectApplication;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;


@SpringBootTest(classes = { CouponController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ConfidentialSecretProjectApplication.class)

public class CouponControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;

    @MockBean
    private UserService userService;
    @MockBean	
    private MainController mainController;


    @Test
    public void testGetAllCouponsFromUser() throws Exception {
        // Given
        int userId = 1;
        Optional<Customer> user = Optional.of(new Customer("test", "tezTest"));
        user.get().setUserId(userId);
        when(userService.findById(userId)).thenReturn(user);
        when(couponService.findByOwnerId(userId)).thenReturn(Collections.emptyList());

        // When and Then
        mockMvc.perform(get("/showAllUserCoupons").param("userId", Integer.toString(userId)))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attributeExists("usersCoupons"))
               .andExpect(model().attributeExists("callingUser"))
               .andExpect(model().attributeDoesNotExist("errorMessage"));
    }
}


