package com.notvk.server.service;

import com.notvk.server.model.UserInfo;
import com.notvk.server.repository.UserRepository;
import com.notvk.server.repository.WallTextRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FriendsService {

    private final UserRepository userRepository;

    private final WallTextRepository wallTextRepository;

    @Autowired
    public FriendsService(UserRepository userRepository, WallTextRepository wallTextRepository) {
        this.userRepository = userRepository;
        this.wallTextRepository = wallTextRepository;
    }

    public boolean addFriend(Long userId, Long addedFriendId) {
        UserInfo userById = userRepository.getUserById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found  in addFriend method(user)"));
        UserInfo friendById = userRepository.getUserById(addedFriendId).orElseThrow(() -> new EntityNotFoundException("User with id " + addedFriendId + " not found in addFriend method(friend)"));
        List<UserInfo> ids = userById.getFriendList();
        ids.add(friendById);
        userById.setFriendList(ids);
        UserInfo saved = userRepository.save(userById);
        return true;
    }

    public boolean deleteFriend(Long userId, Long deletedFriendId) {
        UserInfo userById = userRepository.getUserById(userId).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found  in addFriend method(user)"));
        UserInfo friendById = userRepository.getUserById(deletedFriendId).orElseThrow(() -> new EntityNotFoundException("User with id " + deletedFriendId + " not found in addFriend method(friend)"));
        List<UserInfo> ids = userById.getFriendList();
        ids.remove(friendById);
        userById.setFriendList(ids);
        UserInfo saved = userRepository.save(userById);
        return true;
    }

    public List<Long> showOnlineFriends() {
        return new ArrayList<>();
    }
}
