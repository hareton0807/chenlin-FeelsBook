package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class editActivity extends Activity  {


    private EditText timeStamp;
    private EditText commentText;
    private EditText specificTime;

    /*Intent intent = getIntent();  It is not intended to create a new intent; Return the intent which started the activity */

    String content,comment,time,myFeeling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        timeStamp = (EditText) findViewById(R.id.editText1);
        timeStamp.setInputType(InputType.TYPE_NULL); 
        timeStamp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    showDatePickerDialog();
                }
            }
        });

        timeStamp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        specificTime = (EditText) findViewById(R.id.editText3);
        specificTime.setInputType(InputType.TYPE_NULL);
        specificTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    showTimePickerDialog();
                }
            }
        });



        commentText = (EditText) findViewById(R.id.editText2);
        Button saveButton = (Button) findViewById(R.id.save2);


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                final Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);
                String selection = mySpinner2.getSelectedItem().toString();

                Log.d("Spinner Selection:",selection);

                if(selection.compareTo("Love") == 0){
                    myFeeling = "Love";
                }

                else if(selection.compareTo("Joy") ==0){
                    myFeeling = "Joy";
                }

                else if(selection.compareTo("Surprise") ==0){
                    myFeeling = "Surprise";
                }
                else if(selection.compareTo("Anger") == 0){
                    myFeeling = "Anger";
                }
                else if(selection.compareTo("Sadness") == 0){
                    myFeeling = "Sadness";
                }
                else if(selection.compareTo("Fear") == 0){
                    myFeeling = "Fear";
                }

                comment = commentText.getText().toString();
                content = timeStamp.getText().toString();
                time = specificTime.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("emotion",myFeeling);
                intent.putExtra("text",comment);
                intent.putExtra("dateContent",content);
                intent.putExtra("specificTime",time);
                setResult(2,intent);
                finish();


            }
        });
    }

    private void showDatePickerDialog(){
        Calendar c= Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                timeStamp.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void showTimePickerDialog() {
        final Calendar myCalendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(editActivity.this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR, i);
                myCalendar.set(Calendar.MINUTE, i1);

                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Toast.makeText(getApplicationContext(), "" + format.format(myCalendar.getTime()), Toast.LENGTH_SHORT).show();
                specificTime.setText(i+":"+i1);
            }
        }, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), true);
        dialog.show();

    }




}
