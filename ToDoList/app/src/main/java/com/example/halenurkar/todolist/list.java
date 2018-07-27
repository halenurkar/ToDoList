package com.example.halenurkar.todolist;

public class list {

 private int id;
 private String head;
 private String detail;

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public list(int id, String head, String detail) {
        this.id = id;
        this.head = head;
        this.detail = detail;
    }
    public String toString(){
        return ""+id+"-"+head+"-"+detail;
    }

    public list() {
    }
}
