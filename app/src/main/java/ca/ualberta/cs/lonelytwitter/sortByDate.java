package ca.ualberta.cs.lonelytwitter;

import java.util.Comparator;
import java.util.Date;

/**
 * Define a criterion for the app to sort all records of emotion by date.
 */

public class sortByDate implements Comparator<Tweet> {

    public int compare(Tweet a,Tweet b){
        Date a_date = a.getDate();
        Date b_date = b.getDate();
        if(a_date.before(b_date)){
            return -1;
        }
        else if(a_date.after(b_date)){
            return 1;
        }
        else{
            return 0;
        }
    }

}
