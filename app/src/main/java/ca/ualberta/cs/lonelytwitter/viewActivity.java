package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class viewActivity extends Activity {

    private TextView textView1,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textView1 = (TextView) findViewById(R.id.emotionTextView);
        textView2 = (TextView) findViewById(R.id.dateTextView);
        textView3 = (TextView) findViewById(R.id.commentTextView);

        Intent intent = getIntent(); // Return the intent that started this activity

        String myDateString = "Date: " + intent.getStringExtra("dateString");
        String myComment = "Comment: " + intent.getStringExtra("comment");
        String myFeeling = "Feeling: " + intent.getStringExtra("feeling");
        textView2.setText(myDateString);
        textView3.setText(myComment);

        textView2.setEnabled(false);
        textView2.setClickable(false);
        textView3.setEnabled(false);
        textView3.setClickable(false);

        textView1.setText(myFeeling);
        textView1.setEnabled(false);
        textView1.setClickable(false);



    }
}
