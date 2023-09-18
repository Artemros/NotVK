package com.notvk.server;

import com.notvk.server.model.UserInfo;
import com.notvk.server.repository.UserRepository;
import com.notvk.server.service.FriendsService;
import com.notvk.server.service.RegistrationService;
import com.notvk.server.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
//public class FriendSystemTests {
////    private final UserService userService;
//
//    private final FriendsService friendsService;
//
//    @Autowired
//    FriendSystemTests(FriendsService friendsService) {
//        this.friendsService = friendsService;
//    }
//
//    @Test
//    void addNewFriend(){
//        List<UserInfo> testingFriends = new ArrayList<>();
//        UserInfo user = new UserInfo();
//        user.setUsername("Artem");
//        user.setStatus("online");
//        testingFriends.add(user);
//        UserInfo friend = new UserInfo();
//        friend.setName("Denis");
//        friend.setStatus("online");
//        friendsService.addFriend(user.getId(),friend.getId());
//        testingFriends.add(friend);
//        Assertions.assertEquals(testingFriends,user.getFriendList());
//
//    }
//}
@SpringBootTest
public class FriendSystemTests {
    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private FriendsService friendsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository repository;

    @Test
    void addNewFriend() {
        List<UserInfo> testingFriends = new ArrayList<>();
        UserInfo user = new UserInfo();
        user.setName("Artem");
        user.setUsername("Artem");
        user.setPassword("123");
        UserInfo userInfo = userService.registerNewUser(user);
        registrationService.register(userInfo.getName(),userInfo.getPassword());
        UserInfo friend = new UserInfo();
        friend.setName("Denis");
        friend.setUsername("Denis");
        friend.setPassword("123");
        UserInfo friendInfo = userService.registerNewUser(friend);
        registrationService.register(friendInfo.getName(),friendInfo.getPassword());
        friendsService.addFriend(userService.getUserByUsername("Artem").getId(), userService.getUserByUsername("Denis").getId());
        testingFriends.add(userService.getUserByUsername("Denis"));
        Assertions.assertEquals(testingFriends, repository.getUserByUsername("Artem").get().getFriendList());
    }

    @Test
    void addTwoNewFriend() {
        List<UserInfo> testingFriends = new ArrayList<>();
        UserInfo user = new UserInfo();
        user.setName("Artem");
        user.setUsername("Artem");
        user.setPassword("123");
        UserInfo userInfo = userService.registerNewUser(user);
        registrationService.register(userInfo.getName(),userInfo.getPassword());
        UserInfo friend = new UserInfo();
        friend.setName("Denis");
        friend.setUsername("Denis");
        friend.setPassword("123");
        UserInfo friendInfo = userService.registerNewUser(friend);
        registrationService.register(friendInfo.getName(),friendInfo.getPassword());
        UserInfo friend2 = new UserInfo();
        friend2.setName("Denis2");
        friend2.setUsername("Denis2");
        friend2.setPassword("123");
        UserInfo friendInfo2 = userService.registerNewUser(friend2);
        registrationService.register(friendInfo2.getName(),friendInfo2.getPassword());
        friendsService.addFriend(userService.getUserByUsername("Artem").getId(), userService.getUserByUsername("Denis").getId());
        friendsService.addFriend(userService.getUserByUsername("Artem").getId(), userService.getUserByUsername("Denis2").getId());
        testingFriends.add(userService.getUserByUsername("Denis"));
        testingFriends.add(userService.getUserByUsername("Denis2"));
        Assertions.assertEquals(testingFriends, repository.getUserByUsername("Artem").get().getFriendList());
    }

    @Test
    void deleteFriend() {
        List<UserInfo> testingFriends = new ArrayList<>();
        UserInfo user = new UserInfo();
        user.setName("Artem");
        user.setUsername("Artem");
        user.setPassword("123");
        UserInfo userInfo = userService.registerNewUser(user);
        registrationService.register(userInfo.getName(),userInfo.getPassword());
        UserInfo friend = new UserInfo();
        friend.setName("Denis");
        friend.setUsername("Denis");
        friend.setPassword("123");
        UserInfo friendInfo = userService.registerNewUser(friend);
        registrationService.register(friendInfo.getName(),friendInfo.getPassword());
        friendsService.addFriend(userService.getUserByUsername("Artem").getId(), userService.getUserByUsername("Denis").getId());
        friendsService.deleteFriend(userService.getUserByUsername("Artem").getId(), userService.getUserByUsername("Denis").getId());
        Assertions.assertEquals(testingFriends, repository.getUserByUsername("Artem").get().getFriendList());

    }

}
