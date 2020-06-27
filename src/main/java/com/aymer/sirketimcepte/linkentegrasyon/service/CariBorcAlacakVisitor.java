package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartVisitor;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car003;
import com.aymer.sirketimcepte.linkentegrasyon.repository.CariKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CariBorcAlacakVisitor implements CariKartVisitor {

    @Autowired
    private CariKartRepository cariKartRepository;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {
        // hesaplar alınıyor
        List<Car003> car003List = cariKartRepository.findAllCar003ByHesapKodu(cariKartDto.getHesapKodu());
        Map<Integer, List<Car003>> cariMap = generateMap(car003List);

        BigDecimal toplamBorc = getToplamBorc(cariMap);
        cariKartDto.setToplamBorc(toplamBorc);

        BigDecimal toplamAlacak = getToplamAlacak(cariMap);
        cariKartDto.setToplamAlacak(toplamAlacak);
    }

    private BigDecimal getToplamAlacak(Map<Integer, List<Car003>> cariMap) {
        List<Car003> alacakList = cariMap.get(1);
        BigDecimal toplamAlacak = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(alacakList)) {
            for (Car003 item : alacakList) {
                if (item.getIslemTipi().equals(9)) {
                    toplamAlacak = toplamAlacak.subtract(item.getTutar());
                } else {
                    toplamAlacak = toplamAlacak.add(item.getTutar());
                }
            }
        }
        return toplamAlacak;
    }

    private BigDecimal getToplamBorc(Map<Integer, List<Car003>> cariMap) {
        List<Car003> borcList = cariMap.get(0);
        BigDecimal toplamBorc = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(borcList)) {
            for (Car003 item : borcList) {
                toplamBorc = toplamBorc.add(item.getTutar());
            }
        }
        return toplamBorc;
    }


    private Map<Integer, List<Car003>> generateMap(List<Car003> car003List) {
        Map<Integer, List<Car003>> cariMap = new HashMap<>();
        car003List.forEach(cariKalem -> {
            List<Car003> itemList;
            if (cariMap.containsKey(cariKalem.getBaTipi())) {
                itemList = cariMap.get(cariKalem.getBaTipi());
            } else {
                itemList = new ArrayList<>();
            }
            itemList.add(cariKalem);
            cariMap.put(cariKalem.getBaTipi(),itemList);
        });
        return cariMap;
    }
}
