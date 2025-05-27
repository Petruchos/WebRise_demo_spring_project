package com.example.web_rise.demo.controller;

import com.example.web_rise.demo.dto.*;
import com.example.web_rise.demo.service.UserService;
import com.example.web_rise.demo.utils.converter.UserDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public void createUser(@RequestBody CreateUserPayload payload){
        userService.createUser(payload);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) { return UserDTOConverter.toDto(userService.getUser(id)) ; }

    @PutMapping("/{id}")
    public void editUser(@RequestBody EditUserPayload payload, @PathVariable Long id) { userService.editUser(payload, id)  ; }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { userService.deleteUser(id)  ; }

    @PostMapping("/{id}/subscriptions")
    public void addSubscription(@RequestBody AddSubscriptionPayload payload, @PathVariable Long id){
        userService.addSubscription(payload, id);
    }

    @GetMapping("/{id}/subscriptions")
    public List<Subscription> getSubscriptions(@PathVariable Long id){
        return userService.getSubscriptions(id);
    }

    @DeleteMapping("/{userId}/subscriptions/{subscriptionId}")
    public void deleteSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId){
        userService.deleteSubscription(userId, subscriptionId);
    }

    @GetMapping("/subscriptions/top")
    public List<SubscriptionStatistics> getTop3SubscriptionTypes(){
        return userService.getTop3SubscriptionTypes();
    }
}
