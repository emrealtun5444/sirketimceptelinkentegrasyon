package com.aymer.sirketimcepte.linkentegrasyon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CariVisitorFactory {

    @Autowired
    private ApplicationContext context;

    public List<CariKartVisitor> getCariVisitorList() {
        List<CariKartVisitor> cariKartVisitorList = new ArrayList<>();
        cariKartVisitorList.add(context.getBean("cariBorcAlacakVisitor", CariBorcAlacakVisitor.class));
        cariKartVisitorList.add(context.getBean("cariFaturaVisitor", CariFaturaVisitor.class));
        return cariKartVisitorList;
    }
}
