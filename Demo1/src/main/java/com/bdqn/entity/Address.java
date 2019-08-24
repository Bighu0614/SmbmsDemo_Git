package com.bdqn.entity;

public class Address {
    private String contact;
    private  String tel;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Address() {
    }

    public Address(String contact, String tel) {
        this.contact = contact;
        this.tel = tel;
    }
}
