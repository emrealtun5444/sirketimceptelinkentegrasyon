package com.aymer.sirketimcepte.linkentegrasyon.producer;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 20:19
 */
@Service
public class CariKartProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${cari.kart.rabbit.routing.name}")
    private String routingName;

    @Value("${exchange.direct}")
    private String exchangeName;

    public void sendToQueue(CariKartDto cariKartDto) {
        String jsonStr = null;
        try {
            jsonStr = new ObjectMapper().writeValueAsString(cariKartDto);
            rabbitTemplate.convertAndSend(exchangeName, routingName, jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
