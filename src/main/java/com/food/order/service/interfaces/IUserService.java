package com.food.order.service.interfaces;

import com.food.order.dto.UserDto;
import com.food.order.data.entity.User;
import com.food.order.data.entity.VerificationToken;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User registerUser(UserDto userDto, String origin) throws Exception;
    VerificationToken getVerificationToken(String token);
    void enableRegisteredUser(User user, VerificationToken token);
    VerificationToken generateVerificationToken(User user);
    VerificationToken extendVerificationToken(String token);
    void sendEmail(VerificationToken token, String origin);
}
