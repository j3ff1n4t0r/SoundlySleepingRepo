package com.example.soundlysleeping;

import android.os.Parcel;
import android.os.Parcelable;

public class Theme implements Parcelable{
	public String themeName;
	public int themePicId;
	public String themeTrack1;
	public String themeTrack2;
	
	//all 4 params
	public Theme(String name, int picId, String trackId1, String trackId2){
		themeName = name;
		themePicId = picId;
		themeTrack1 = trackId1;
		themeTrack2 = trackId2;
	}
	
	//first 2 params; obvious fill for .mp3 file names
	public Theme(String name, int picId){
		themeName = name;
		themePicId = picId;
		themeTrack1 = name + "_track_1";
		themeTrack2 = name + "_track_2";
	}
	
	public Theme(Parcel parcelIn){
		themeName = parcelIn.readString();
		themePicId = parcelIn.readInt();
		themeTrack1 = parcelIn.readString();
		themeTrack2 = parcelIn.readString();
	}
	
	//according to StackOverflow I don't need this atm?
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel(Parcel parcelOut, int flags){
		parcelOut.writeString(themeName);
		parcelOut.writeInt(themePicId);
		parcelOut.writeString(themeTrack1);
		parcelOut.writeString(themeTrack2);
	}
	
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Theme> CREATOR = new Parcelable.Creator<Theme>() {
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };
}
