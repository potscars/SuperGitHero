package com.example.msdhainizam.loginschoolapp;

/**
 * Created by MSD Hainizam on 1/26/2016.
 */
public class DataObject {
    private String mText1;
    private String mText2;
    private int mImage;

    DataObject(String text1, String text2, int image1) {
        mText1 = text1;
        mText2 = text2;
        mImage = image1;

    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
}
