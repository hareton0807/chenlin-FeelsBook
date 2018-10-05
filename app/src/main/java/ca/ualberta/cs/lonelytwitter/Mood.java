package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by caochenlin on 2018-01-16.
 */

public abstract class Mood {

    public Date date;

    public abstract Date getDate();

    public abstract void setDate(Date date_value);


}

class Love extends Mood {

    public Date getDate(){
        return date;
    }

    public void setDate(Date date_value) {
        this.date = date_value;
    }

    public Love() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
    }

    public void express_love() {
        String happiness = "I am feelling happy!";
        System.out.println(happiness);
    }

}

class Sad extends Mood {

    public Date getDate() {
        return date;
    }

    public void setDate(Date date_value){
        this.date = date_value;
    }

    public Sad() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
    }

    public void express_sad() {
        String sadness = "I am feeling very sad!";
        System.out.println(sadness);
    }
}