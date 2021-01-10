package com.tcd.configuration;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String ADD_TOPIC = "listaAdd";
    private static final String REMOVE_TOPIC = "listaRemove";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendAddMessage(String message) {
        logger.info(String.format("#### -> Producing add message -> %s", message));
        this.kafkaTemplate.send(ADD_TOPIC, message);
    }
    
    public void sendRemoveMessage(String message) {
    	logger.info(String.format("#### -> Producing remove message -> %s", message));
        this.kafkaTemplate.send(REMOVE_TOPIC, message);
    }
}
