
package com.mihir.minix.contactlistapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private Geo geo;

    public final static Creator<Address> CREATOR = new Creator<Address>() {

        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    };

    protected Address(Parcel in) {
        this.street = ((String) in.readValue((String.class.getClassLoader())));
        this.suite = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.zipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.geo = ((Geo) in.readValue((Geo.class.getClassLoader())));
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(street);
        dest.writeValue(suite);
        dest.writeValue(city);
        dest.writeValue(zipcode);
        dest.writeValue(geo);
    }

    public int describeContents() {
        return 0;
    }

}
