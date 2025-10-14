package com.example.ms_auth.service;

import com.example.ms_auth.dto.AuthUserDto;
import com.example.ms_auth.dto.TokenDto;
import com.example.ms_auth.entity.AuthUser;


public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);


    public TokenDto login(AuthUserDto authUserDto);


    public TokenDto validate(String token);
}
