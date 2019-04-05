package ru.tai.testrestclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class Hello{
    private long id;
    private String content;

    public Hello(){}

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
