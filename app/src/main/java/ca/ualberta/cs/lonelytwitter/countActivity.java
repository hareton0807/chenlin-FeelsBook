package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

    public class countActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        TextView t1 = (TextView) findViewById(R.id.loveCount);
        TextView t2 = (TextView) findViewById(R.id.joyCount);
        TextView t3 = (TextView) findViewById(R.id.surpriseCount);
        TextView t4 = (TextView) findViewById(R.id.angerCount);
        TextView t5 = (TextView) findViewById(R.id.sadnessCount);
        TextView t6 = (TextView) findViewById(R.id.fearCount);

        Intent intent = getIntent();

        int c1 = intent.getIntExtra("loveCount",0);
        int c2 = intent.getIntExtra("joyCount",0);
        int c3 = intent.getIntExtra("surpriseCount",0);
        int c4 = intent.getIntExtra("angerCount",0);
        int c5 = intent.getIntExtra("sadnessCount",0);
        int c6 = intent.getIntExtra("fearCount",0);

        String t1Text = "Love count: "+String.valueOf(c1);
        String t2Text = "Joy count: "+String.valueOf(c2);
        String t3Text = "Surprise count: "+String.valueOf(c3);
        String t4Text = "Anger Count: "+String.valueOf(c4);
        String t5Text = "Sadness Count: "+String.valueOf(c5);
        String t6Text = "Fear count: "+String.valueOf(c6);

        t1.setText(t1Text);
        t2.setText(t2Text);
        t3.setText(t3Text);
        t4.setText(t4Text);
        t5.setText(t5Text);
        t6.setText(t6Text);

    }
}
