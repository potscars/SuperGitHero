package com.example.msdhainizam.loginschoolapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IGWMobileTeam on 11/01/2016.
 */
public class Data implements Parcelable {

    public static final Creator<Data> CREATOR
            = new Creator<Data>() {
        public Data createFromParcel(Parcel in) {
            return new  Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    private String title;
    private String content;
    private String schoolName;
    private String schoolImage;

    public Data() { }

    public Data(Parcel input) {
        title = input.readString();
        content = input.readString();
        schoolName = input.readString();
        schoolImage = input.readString();
    }

    public Data(String title,
                String content,
                String schoolName,
                String schoolImage) {
        this.title = title;
        this.content = content;
        this.schoolName = schoolName;
        this.schoolImage = schoolImage;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolImage(String schoolImage) {
        this.schoolImage = schoolImage;
    }

    public String getSchoolImage() {
        return schoolImage;
    }

    @Override
    public String toString() {
        return "\nTitle: "+ title +
                "\nContent: "+ content +
                "\nSchool Name: "+ schoolName +
                "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(schoolName);
        dest.writeString(schoolImage);
    }
}
