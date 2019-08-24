package com.bdqn.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User2 extends User {
    private String roleName;
    private Role role;
    private List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getRoleName() {
        return roleName;
    }

    public User2() {
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }




    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public User2(Integer age, String roleName, Role role) {
//        this.age = age;
//        this.roleName = roleName;
//        this.role = role;
//    }

//    public User2(String userNmae, String userPassword, Integer age, String roleName, Role role) {
//        super(userNmae, userPassword);
//        this.age = age;
//        this.roleName = roleName;
//        this.role = role;
//    }

    public User2(String userName, Integer userRole) {
        this.setUserName(userName);
        this.setUserRole(userRole);
    }

//    public User2(int id, String userCode, String userNmae, String userPassword, int gender, String birthday, String phone, String address, int userRole, int createdBy, Date creationDate, int modifyBy, Date modifyDate, Integer age, String roleName, Role role) {
//        super(id, userCode, userNmae, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate);
//        this.roleName = roleName;
//        this.role = role;
//    }
}
