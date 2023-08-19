package com.notvk.server.service;

import com.notvk.server.model.UserInfo;
import com.notvk.server.model.WallText;
import com.notvk.server.repository.UserRepository;
import com.notvk.server.repository.WallTextRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository, WallTextRepository wallTextRepository) {
        this.userRepository = userRepository;
        this.wallTextRepository = wallTextRepository;
    }

    private final UserRepository userRepository;

    private final WallTextRepository wallTextRepository;


    public List<WallText> getWallTextById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            UserInfo userInfo = userRepository.findById(id).get();
            List<WallText> texts;
            texts = userInfo.getWallText();
            return texts;
        }else{
            new EntityNotFoundException("User with id " + id + " not found");
        }
        return null;
    }

    public void addWallTextById(Long id, WallText wallText) {
        if (userRepository.findById(id).isPresent()) {
            UserInfo userInfo = userRepository.findById(id).get();
            List<WallText> texts;
            wallTextRepository.save(wallText);
            texts = userInfo.getWallText();
            texts.add(wallText);
            texts.add(wallText);
            userInfo.setWallText(texts);
            userInfo.setId(id);

        } else {
            new EntityNotFoundException("User with id " + id + " not found");
        }

    }

    public UserInfo getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public UserInfo registerNewUser(UserInfo newUser){
        return userRepository.save(newUser);
    }
}
