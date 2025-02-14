package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.response.UserResponse;
import com.rabobank.lostandfoundapi.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserBOImpl {

    private static final Map<Long, UserResponse> users = Map.of(
            1001L, UserResponse.builder().id(1001L).name("Tathagata").build(),
            1002L, UserResponse.builder().id(1002L).name("Manna").build()
    );

    public UserResponse getUser(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else throw new UserNotFoundException("User not found");
    }
}
