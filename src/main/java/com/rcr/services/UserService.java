package com.rcr.services;

import com.rcr.model.User;

public interface UserService {
    public User findUserByJwToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;

}
