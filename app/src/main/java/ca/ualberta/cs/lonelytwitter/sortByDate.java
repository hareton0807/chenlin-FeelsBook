package ca.ualberta.cs.lonelytwitter;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by caochenlin on 2018/10/4.
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
