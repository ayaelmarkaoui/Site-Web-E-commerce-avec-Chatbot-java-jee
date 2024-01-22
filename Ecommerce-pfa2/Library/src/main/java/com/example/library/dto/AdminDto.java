package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//cette classe concerne les utilisateurs qui sont vient d'enregistrer
public class AdminDto {
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String repeatPassword;
}
