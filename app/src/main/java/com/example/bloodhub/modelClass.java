package com.example.bloodhub;

public class modelClass {
    String age;
    String email;
    String name;
    String number;

    public modelClass(String age, String email, String name, String number) {
        this.age = age;
        this.email = email;
        this.name = name;
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
