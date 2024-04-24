package com.CheggWebsite.sevice;
import com.CheggWebsite.exception.InvalidInputException;
import com.CheggWebsite.exception.UserExistException;
import com.CheggWebsite.exception.UserInvalidException;
import com.CheggWebsite.model.Users;
import com.CheggWebsite.model.enums.StatusCode;
import com.CheggWebsite.model.request.CreateUserRequestDto;
import com.CheggWebsite.model.request.UpdateUserRequest;
import com.CheggWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users CreateUser(CreateUserRequestDto createUserRequestDto)
    {
        Users user=createUserRequestDto.usersBuilder();
        Optional<Users> existingUser = userRepository.findByEmail(createUserRequestDto.getEmail());
        if(existingUser.isPresent())
        {
            throw new UserExistException(StatusCode.USER_ALREADY_EXIST);
        }
        return saveOrUpdate(user);
    }

    private Users saveOrUpdate(Users user) {
        return userRepository.save(user);
    }

    public Users UpdateUser(UpdateUserRequest updateUserRequest)
    {
         Users userInfo=findExistingUserByEmail(updateUserRequest.getEmail());
         userInfo.setName(updateUserRequest.getName());
         return saveOrUpdate(userInfo);
    }

    public Users findExistingUserByEmail(String email) {
        Optional<Users> existingUser = findUserIfExistsByEmail(email);

        if(Objects.isNull(existingUser))
        {
            throw  new UserInvalidException(StatusCode.USER_DOES_NOT_EXIST);
        }
        return existingUser.get();
    }

    private Optional<Users> findUserIfExistsByEmail(String email) {

        if(Objects.isNull(email) || email.isBlank())
        {
            throw new InvalidInputException(StatusCode.INVALID_INPUT_EXCEPTION);
        }
        return userRepository.findByEmail(email);
    }

    public void deleteUser(String email)
    {
       Users user= findExistingUserByEmail(email);
       if(Objects.isNull(user))
       {
           throw new UserInvalidException(StatusCode.USER_DOES_NOT_EXIST);
       }
       userRepository.delete(user);
    }


}
