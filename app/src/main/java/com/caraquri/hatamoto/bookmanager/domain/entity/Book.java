package com.caraquri.hatamoto.bookmanager.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book implements Parcelable {
    private int id;
    private String imageUrl;
    private String name;
    private int price;
    private String purchaseDate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imageUrl);
        dest.writeString(this.name);
        dest.writeInt(this.price);
        dest.writeString(this.purchaseDate);
    }

    protected Book(Parcel in) {
        this.id = in.readInt();
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
        this.purchaseDate = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}