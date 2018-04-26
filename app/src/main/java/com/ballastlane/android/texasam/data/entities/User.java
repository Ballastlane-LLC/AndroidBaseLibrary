package com.ballastlane.android.texasam.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/17/18.
 */

public class User {

    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("contactEmail")
    private String contactEmail;
    @Expose
    @SerializedName("firstName")
    private String firstName;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("role")
    private String role;
    @Expose
    @SerializedName("phoneNumber")
    private PhoneNumber phoneNumber;
    @Expose
    @SerializedName("group")
    private GroupUser group;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GroupUser getGroup() {
        return group;
    }

    public void setGroup(GroupUser group) {
        this.group = group;
    }
}