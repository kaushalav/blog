package com.java.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.blog.entities.User;
import com.java.blog.payloads.UserDto;
import com.java.blog.repositories.UserRepo;
import com.java.blog.services.UserService;
import com.java.blog.exception.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
            
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAbout(userDto.getAbout());
            user.setPassword(userDto.getPassword());

            User updatedUser = userRepo.save(user);
            return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);    
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtoList = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        // using constructor
        // User user = new User(
        //         userDto.getId(),
        //         userDto.getName(),
        //         userDto.getEmail(),
        //         userDto.getPassword(),
        //         userDto.getAbout()
        //     );
        //     return user;
        
        // using ModelMapper
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        // UserDto userDto = new UserDto(
        //         user.getId(),
        //         user.getName(),
        //         user.getEmail(),
        //         user.getPassword(),
        //         user.getAbout()
        //     );
        // return userDto;

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    
    
}
