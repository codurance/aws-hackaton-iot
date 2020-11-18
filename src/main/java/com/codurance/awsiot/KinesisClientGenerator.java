package com.codurance.awsiot;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

@Component
public class KinesisClientGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(KinesisClientGenerator.class);

    private AmazonKinesisFirehose amazonKinesisFirehose;
    private String CONSUMER_ARN = "https://firehose.eu-west-1.amazonaws.com";

    @PostConstruct
    private void init(){
        AwsClientBuilder.EndpointConfiguration endpointConfiguration
                = new AwsClientBuilder.EndpointConfiguration(CONSUMER_ARN, Regions.EU_WEST_1.getName());
//        AWSCredentialsProvider awsCredentialsProvider = new AwsC
        AmazonKinesisFirehoseClientBuilder builder = AmazonKinesisFirehoseClientBuilder.standard();
        builder.setEndpointConfiguration(endpointConfiguration);
//        builder.setCredentials();
        amazonKinesisFirehose = builder.build();

        LOG.info("Kinesis Client Generator Initialized!");
    }

    public void writeToKinesis(String data, String streamName){
        LOG.info("Sending new data stream to " + streamName + ": \\n" + data);

        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setDeliveryStreamName(streamName);

        Record record = new Record().withData(ByteBuffer.wrap(data.getBytes()));
        putRecordRequest.setRecord(record);

        amazonKinesisFirehose.putRecord(putRecordRequest);
    }

}
