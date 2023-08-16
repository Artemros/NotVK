package com.notvk.server.service;

import com.notvk.server.model.UserInfo;
import com.notvk.server.model.WallText;
import com.notvk.server.repository.UserRepository;
import com.notvk.server.repository.WallTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository, WallTextRepository wallTextRepository) {
        this.userRepository = userRepository;
        this.wallTextRepository = wallTextRepository;
    }

    private final UserRepository userRepository;

    private final WallTextRepository wallTextRepository;


    public List<WallText> getWallTextById(Long id){
        Optional<UserInfo> userInfo = userRepository.findById(id);
        List<WallText> texts = new ArrayList<>();
        if(userInfo.isPresent()) {
            texts = userRepository.findById(id).get().getWallText();
        }
        return texts;
    }

    public void addWallTextById(Long id,WallText wallText){
        Optional<UserInfo> userInfo = userRepository.findById(id);
        Iterable<UserInfo> all = userRepository.findAll();
        List<WallText> texts = new ArrayList<>();
        wallTextRepository.save(wallText);
        if(userInfo.isPresent()) {
            texts = userInfo.get().getWallText();
            texts.add(wallText);
            UserInfo newUserInfo = new UserInfo();
            texts.add(wallText);
            newUserInfo.setWallText(texts);
            newUserInfo.setId(id);
            userRepository.save(newUserInfo);

        } else{
            UserInfo newUserInfo = new UserInfo();
            List<WallText> wallTextListNewUser = new ArrayList<>();
            wallTextListNewUser.add(wallText);
            newUserInfo.setWallText(wallTextListNewUser);
            newUserInfo.setId(id);
            userRepository.save(newUserInfo);
        }

    }
}
