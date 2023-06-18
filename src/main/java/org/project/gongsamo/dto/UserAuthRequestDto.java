package org.project.gongsamo.dto;

import lombok.Data;

@Data
public class UserAuthRequestDto {
    private String email;
    private String password;
    private String nickname;
}
