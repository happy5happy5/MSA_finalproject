package com.msa.finalproject.service;

import org.springframework.stereotype.Service;

public interface SNSService {

    String createTopic(String topicName);

    void deleteTopic(String topicArn);

    String subscribe(String topicArn, String email);

    void unsubscribe(String subscriptionArn);

    void publish(String topicArn, String message);

//    void publish(String topicArn, String message);

//    void delete(String topicArn);


}
