/*
 * Copyright (c) 2015. Diederich Kroeske - dkroeske@gmail.com -
 */

package com.appsfromholland.track_3_sqlite_2;

/**
 * Created by dkroeske on 9/9/15.
 */
public class Person {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String imageUrl;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
