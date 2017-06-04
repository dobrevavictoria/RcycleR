package com.example.pc.rcycler;

/**
 * Created by Victoria on 3.6.2017 г..
 */

public class User_activity {

    //private variables
    int _id;
    String _date;
    String _time;
    String _material;

    // Empty constructor
    public User_activity(){

    }
    // constructor
    public User_activity(int id, String date, String time, String material){
        this._id = id;
        this._date = date;
        this._time = time;
        this._material = material;
    }

    // constructor
    public User_activity(String date, String time, String material){
        this._date = date;
        this._time = time;
        this._material = material;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting date
    public String getDate(){
        return this._date;
    }

    // setting date
    public void setDate(String date){
        this._date = date;
    }

    // getting time
    public String getTime(){
        return this._time;
    }

    // setting time
    public void setTime(String time){
        this._time = time;
    }

    public void setMaterial(String material){
        this._material = material;
    }

    public String getMaterial(){
        return this._material;
    }
}