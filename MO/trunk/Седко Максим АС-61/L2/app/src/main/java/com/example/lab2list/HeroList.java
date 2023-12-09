package com.example.lab2list;

import com.google.gson.annotations.SerializedName;

public class HeroList {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    public String localized_name;
    @SerializedName("localized_name")
    public String name;
    @SerializedName("primary_attr")
    public String primary_attr;
    @SerializedName("attack_type")
    public String attack_type;
    @SerializedName("roles")
    public String[] roles;
    @SerializedName("legs")
    public int legs;
}
