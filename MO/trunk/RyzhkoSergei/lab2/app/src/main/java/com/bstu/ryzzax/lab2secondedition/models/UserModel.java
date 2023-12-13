package com.bstu.ryzzax.lab2secondedition.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserModel implements Parcelable {
    private int id;
    private String name;
    private String username;
    private String phone;
    private String website;
    private String email;
    
    private Company company;
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Company getCompany() {
        return company;
    }
    
    protected UserModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        phone = in.readString();
        website = in.readString();
        email = in.readString();
        company = in.readParcelable(Company.class.getClassLoader());
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(email);
        dest.writeParcelable(company, flags);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }
        
        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}

class Company implements Parcelable {
    private String name;
    private String catchPhrase;
    private String bs;
    
    public String getName() {
        return name;
    }
    
    public String getCatchPhrase() {
        return catchPhrase;
    }
    
    public String getBs() {
        return bs;
    }
    
    protected Company(Parcel in) {
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }
    
    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }
        
        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    
        dest.writeString(name);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }
}
