package br.edu.ufersa.cc.lpoo.scale_flow.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserDto;

@Service
public class AuthUtils {

    public UserDto getLoggedUser() {
        return (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
