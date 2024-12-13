package com.example.cursovoi_test;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalityType implements Parcelable {
    public int id;
    public String name;
    public String description;

    public PersonalityType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected PersonalityType(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<PersonalityType> CREATOR = new Creator<PersonalityType>() {
        @Override
        public PersonalityType createFromParcel(Parcel in) {
            return new PersonalityType(in);
        }

        @Override
        public PersonalityType[] newArray(int size) {
            return new PersonalityType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
    }
}
