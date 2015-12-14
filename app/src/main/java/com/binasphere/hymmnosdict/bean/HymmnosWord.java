package com.binasphere.hymmnosdict.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class HymmnosWord implements Parcelable{
    private String hymmnos;
    private String exp_jp;
    private String katakana;
    private String dialect;
    private String id;
    public HymmnosWord(){

    }
    protected HymmnosWord(Parcel in) {
        hymmnos = in.readString();
        exp_jp = in.readString();
        katakana = in.readString();
        dialect = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hymmnos);
        dest.writeString(exp_jp);
        dest.writeString(katakana);
        dest.writeString(dialect);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HymmnosWord> CREATOR = new Creator<HymmnosWord>() {
        @Override
        public HymmnosWord createFromParcel(Parcel in) {
            return new HymmnosWord(in);
        }

        @Override
        public HymmnosWord[] newArray(int size) {
            return new HymmnosWord[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHymmnos() {
        return hymmnos;
    }

    public void setHymmnos(String hymmnos) {
        this.hymmnos = hymmnos;
    }

    public String getExp_jp() {
        return exp_jp;
    }

    public void setExp_jp(String exp_jp) {
        this.exp_jp = exp_jp;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String source) {
        this.dialect = source;
    }

    @Override
    public String toString() {
        return hymmnos+"\n读音:"+katakana+"\n解释:"+ exp_jp+"\n出处:"+dialect;
    }
}
