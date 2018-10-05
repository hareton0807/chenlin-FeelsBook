package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class launchingActivity extends Activity {

    private String myFeeling;
    private String bodyText;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        final Button love = (Button) findViewById(R.id.loveButton);
        final Button joy = (Button) findViewById(R.id.joyButton);
        final Button surprise = (Button) findViewById(R.id.surpriseButton);
        final Button anger = (Button) findViewById(R.id.angerButton);
        final Button sadness = (Button) findViewById(R.id.sadnessButton);
        final Button fear = (Button) findViewById(R.id.fearButton);

        comment = (EditText) findViewById(R.id.commentArea);

        final Button history = (Button) findViewById(R.id.historyList);

        love.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Love";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);

            }
        });

        joy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Joy";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);

            }
        });

        surprise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Surprise";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);
            }
        });

        anger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Joy";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);
            }
        });

        sadness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Sadness";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);
            }
        });

        fear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myFeeling = "Fear";
                bodyText = comment.getText().toString();
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                intent.putExtra("myFeeling",myFeeling);
                intent.putExtra("comment",bodyText);
                startActivity(intent);
            }
        });



        history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(launchingActivity.this,LonelyTwitterActivity.class);
                startActivity(intent);
            }
        });


    }
}




