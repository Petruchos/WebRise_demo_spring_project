package com.example.web_rise.demo.service;

import com.example.web_rise.demo.constants.SubscriptionType;
import com.example.web_rise.demo.dto.*;
import com.example.web_rise.demo.entity.SubscriptionEntity;
import com.example.web_rise.demo.entity.UserEntity;
import com.example.web_rise.demo.repository.SubscriptionRepository;
import com.example.web_rise.demo.repository.UserRepository;
import com.example.web_rise.demo.utils.converter.SubscriptionDTOConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public void createUser(CreateUserPayload payload) {
        log.info("Creating new user with email: {}", payload.getEmail());

        UserEntity user = new UserEntity();
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setEmail(payload.getEmail());

        userRepository.save(user);
        log.info("Successfully created user with email: {}", payload.getEmail());
    }

    public UserEntity getUser(Long id) {
        log.debug("Retrieving user with id: {}", id);

        UserEntity user = userRepository.getReferenceById(id);

        log.debug("Successfully retrieved user with id: {}", id);
        return user;
    }

    public void editUser(EditUserPayload payload, Long id) {
        log.info("Editing user with id: {}", id);

        UserEntity user = userRepository.getReferenceById(id);

        Optional.ofNullable(payload.getEmail())
                .ifPresent(email -> {
                    log.debug("Updating email for user id: {} to: {}", id, email);
                    user.setEmail(email);
                });

        Optional.ofNullable(payload.getFirstName())
                .ifPresent(firstName -> {
                    log.debug("Updating first name for user id: {}", id);
                    user.setFirstName(firstName);
                });

        Optional.ofNullable(payload.getLastName())
                .ifPresent(lastName -> {
                    log.debug("Updating last name for user id: {}", id);
                    user.setLastName(lastName);
                });

        userRepository.save(user);
        log.info("Successfully updated user with id: {}", id);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        userRepository.deleteById(id);

        log.info("Successfully deleted user with id: {}", id);
    }

    public void addSubscription(AddSubscriptionPayload payload, Long id) {
        log.info("Adding subscription of type {} to user with id: {}", payload.getSubscriptionType(), id);

        UserEntity user = userRepository.getReferenceById(id);

        List<SubscriptionEntity> subscriptions = user.getSubscriptions();
        SubscriptionEntity subscriptionToAdd = new SubscriptionEntity();
        subscriptionToAdd.setUser(user);
        subscriptionToAdd.setSubscriptionType(payload.getSubscriptionType());
        subscriptionToAdd.setStartDate(payload.getStartDate());
        subscriptionToAdd.setRenewDate(payload.getRenewDate());
        subscriptionToAdd.setExpirationDate(payload.getExpirationDate());
        subscriptions.add(subscriptionToAdd);

        user.setSubscriptions(subscriptions);
        userRepository.save(user);

        log.info("Successfully added subscription of type {} to user with id: {}",
                payload.getSubscriptionType(), id);
    }

    public List<Subscription> getSubscriptions(Long id) {
        log.debug("Retrieving subscriptions for user with id: {}", id);

        UserEntity user = userRepository.getReferenceById(id);
        List<Subscription> subscriptions = user.getSubscriptions().stream()
                .map(SubscriptionDTOConverter::toDto)
                .collect(Collectors.toList());

        log.debug("Retrieved {} subscriptions for user with id: {}", subscriptions.size(), id);
        return subscriptions;
    }

    @Transactional
    public void deleteSubscription(Long userId, Long subscriptionId) {
        log.info("Deleting subscription with id: {} for user with id: {}", subscriptionId, userId);

        try {
            SubscriptionEntity subscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

            UserEntity user = subscription.getUser();

            if (!user.getId().equals(userId)) {
                log.warn("Attempt to delete subscription {} by unauthorized user {}", subscriptionId, userId);
                throw new IllegalArgumentException("User with id '" + userId +
                        "' doesn't have subscription with id '" + subscriptionId + "'");
            }

            user.getSubscriptions().remove(subscription);
            subscriptionRepository.delete(subscription);

            log.info("Successfully deleted subscription with id: {} for user with id: {}",
                    subscriptionId, userId);
        } catch (EntityNotFoundException e) {
            log.error("Subscription with id {} not found", subscriptionId);
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("Unauthorized attempt to delete subscription: {}", e.getMessage());
            throw e;
        }
    }

    public List<SubscriptionStatistics> getTop3SubscriptionTypes() {
        log.debug("Retrieving top 3 subscription types statistics");

        Pageable topThree = PageRequest.of(0, 3);
        List<SubscriptionStatistics> statistics = subscriptionRepository.findTop3SubscriptionTypesDto(topThree);

        log.debug("Retrieved {} subscription type statistics", statistics.size());
        return statistics;
    }
}