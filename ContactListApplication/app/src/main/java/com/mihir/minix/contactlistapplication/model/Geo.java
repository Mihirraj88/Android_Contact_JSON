
package com.mihir.minix.contactlistapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo implements Parcelable {


    private String lat;

    private String lng;
    public final static Creator<Geo> CREATOR = new Creator<Geo>() {

        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        public Geo[] newArray(int size) {
            return (new Geo[size]);
        }

    };

    protected Geo(Parcel in) {
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lng = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Geo() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lng);
    }

    public int describeContents() {
        return 0;
    }

}
