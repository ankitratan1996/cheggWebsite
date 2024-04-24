package com.CheggWebsite.model.request;

import com.CheggWebsite.model.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    @NotBlank
    String name;
    @NotBlank
    String email;
    public Users usersBuilder()
    {
        return Users.builder().name(name).email(email).build();
    }

}
