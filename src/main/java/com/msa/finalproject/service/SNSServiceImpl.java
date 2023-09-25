//package com.msa.finalproject.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sns.model.*;
//
//@Service
//public class SNSServiceImpl implements SNSService {
//
//    private final SnsClient snsClient;
//
//    public SNSServiceImpl(
//            @Value("${AWS_ACCESS-KEY}") String accessKey,
//            @Value("${AWS_SECRET-KEY}") String secretKey,
//            @Value("${AWS_REGION}") String region
//    ) {
//
//
//        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
//
//    }
//
//
//
//    @Override
//    public String createTopic(String topicName) {
//        CreateTopicResponse createTopicResponse;
//        try (SnsClient snsClient = SnsClient.builder()
//                .region(Region.AP_NORTHEAST_2) // AWS 리전 선택
//                .credentialsProvider(credentialsProvider) // AWS 자격 증명 제공자 설정
//                .build()) {
//
//            CreateTopicRequest createTopicRequest = CreateTopicRequest.builder()
//                    .name(topicName)
//                    .build();
//
//            createTopicResponse = snsClient.createTopic(createTopicRequest);
//        }
////        catch (Exception e){
////            throw new RuntimeException(e);
////        }
//
//        // 생성된 주제의 ARN을 반환
//        return createTopicResponse.topicArn();
//    }
//
//    @Override
//    public void deleteTopic(String topicArn) {
//        DeleteTopicResponse deleteTopicResponse;
//        try (SnsClient snsClient = SnsClient.builder()
//                .region(Region.AP_NORTHEAST_2) // AWS 리전 선택
//                .credentialsProvider(credentialsProvider) // AWS 자격 증명 제공자 설정
//                .build()) {
//
//            DeleteTopicRequest deleteTopicRequest = DeleteTopicRequest.builder()
//                    .topicArn(topicArn)
//                    .build();
//
//            deleteTopicResponse = snsClient.deleteTopic(deleteTopicRequest);
//        }
//    }
//
//    @Override
//    public String subscribe(String topicArn, String email) {
//        SubscribeResponse subscribeResponse;
//        try (SnsClient snsClient = SnsClient.builder()
//                .region(Region.AP_NORTHEAST_2) // AWS 리전 선택
//                .credentialsProvider(credentialsProvider) // AWS 자격 증명 제공자 설정
//                .build()) {
//
//            SubscribeRequest subscribeRequest = SubscribeRequest.builder()
//                    .topicArn(topicArn)
//                    .protocol("email")
//                    .endpoint(email)
//                    .build();
//
//            subscribeResponse = snsClient.subscribe(subscribeRequest);
//        }
//
//        return subscribeResponse.subscriptionArn();
//    }
//
//    @Override
//    public void unsubscribe(String subscriptionArn) {
//        UnsubscribeResponse unsubscribeResponse;
//        try (SnsClient snsClient = SnsClient.builder()
//                .region(Region.AP_NORTHEAST_2) // AWS 리전 선택
//                .credentialsProvider(credentialsProvider) // AWS 자격 증명 제공자 설정
//                .build()) {
//
//            UnsubscribeRequest unsubscribeRequest = UnsubscribeRequest.builder()
//                    .subscriptionArn(subscriptionArn)
//                    .build();
//
//            unsubscribeResponse = snsClient.unsubscribe(unsubscribeRequest);
//        }
//    }
//
//    @Override
//    public void publish(String topicArn, String message) {
//        PublishResponse publishResponse;
//        try (SnsClient snsClient = SnsClient.builder()
//                .region(Region.AP_NORTHEAST_2) // AWS 리전 선택
//                .credentialsProvider(credentialsProvider) // AWS 자격 증명 제공자 설정
//                .build()) {
//
//            PublishRequest publishRequest = PublishRequest.builder()
//                    .topicArn(topicArn)
//                    .message(message)
//                    .build();
//
//            publishResponse = snsClient.publish(publishRequest);
//        }
//    }
//}
