package com.fdmgroup.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.fdmgroup.Confidential_secret_project.controller.UserController;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

  @Mock
  private ModelMap model;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @Test
  public void testPickUserId() {
    // Create a mock list of user objects to be returned by the userService
    List<Users> users = Arrays.asList(
      new Users("user1", "password1"),
      new Users("user2", "password2")
    );
    when(userService.findAllUsers()).thenReturn(users);

    // Call the controller method and capture the returned view name
    String viewName = userController.getUserId(model);

    // Verify that the userService was called with the correct method and arguments
    verify(userService).findAllUsers();

    // Verify that the model was called with the correct attribute name and value
    verify(model).addAttribute("usersFromDB", users);

    // Verify that the view name returned by the controller method is "users"
    assertThat(viewName).isEqualTo("users");
  }
}
