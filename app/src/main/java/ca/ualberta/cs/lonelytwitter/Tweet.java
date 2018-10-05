package ca.ualberta.cs.lonelytwitter;

/**
 * Created by chenlin on 9/20/18.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public abstract class Tweet {
    private String message;
    private Date date;
    private String feeling;

    public void setMessage(String message) throws TooLongTweetException {
        Log.d("setMessage",message);
        if (message.length()>100){
            throw new TooLongTweetException();
        }
        this.message = message;
    }

    public void setFeeling(String myFeeling){
        Log.d("myFeeling",myFeeling);
        this.feeling = myFeeling;
    }

    public String getFeeling(){
        return this.feeling;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public String getMessage(){
        return this.message;
    }
    public Date getDate(){
        return this.date;
    }
    public abstract Boolean isImportant();

    public String toString(){
        return this.date.toString()+" | "+this.feeling+" | "+this.message;
    }


}
