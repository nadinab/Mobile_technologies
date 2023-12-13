package com.bstu.ryzzax.lab2secondedition;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CommentModel implements Parcelable {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
    
    protected CommentModel(Parcel in) {
        postId = in.readInt();
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }
    
    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel in) {
            return new CommentModel(in);
        }
        
        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };
    
    public int getPostId() {
        return postId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getBody() {
        return body;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    
        dest.writeInt(postId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(body);
    }
}
