package com.java.back.end.userapi.convert;

import com.java.back.end.userapi.dto.UserDTO;
import com.java.back.end.userapi.model.User;

public class DTOConvert {
    public static UserDTO convert(User user){
        UserDTO	userDTO	=	new	UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setKey(user.getKey());
        return	userDTO;
    }
}
