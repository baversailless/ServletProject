package com.example.ServletPro;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Organization {
    private long id;
    private String title;
    private String address;
    private LocalDate creationDate;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public Organization(String title, String address, LocalDate creationDate){
        this.id = generatorId.getAndIncrement();
        this.title = title;
        this.address = address;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress(){
        return address;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }



}
