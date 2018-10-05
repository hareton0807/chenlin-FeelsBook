package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

    private static final String FILENAME = "file2.sav";
    private EditText bodyText;
    private ListView feelingList;

    private ArrayList<ImportantTweet> tweets = new ArrayList<ImportantTweet>(); /* global var, it is everywhere */

    private ArrayAdapter<ImportantTweet> adapter;

    private int item_id;

    private String feeling5,comment5;
    private Boolean intentFromEdit = false;


    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        feelingList = (ListView) findViewById(R.id.feelingList);
        final Button backButton = (Button) findViewById(R.id.back);
        final Button countButton = (Button) findViewById(R.id.count);

        Log.d("onCreate","on create!");
        Collections.sort(tweets,new sortByDate());


        countButton.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View view) {
            int loveCount = 0, joyCount = 0, surpriseCount = 0, angerCount = 0, sadnessCount = 0, fearCount = 0;
            Iterator<ImportantTweet> tweetIterator = tweets.iterator();

            while (tweetIterator.hasNext()) {

                Log.d("arrive spot", "arrived here!");

                String feelingElement = tweetIterator.next().getFeeling();

                if (feelingElement.compareTo("Love") == 0) {
                    loveCount += 1;
                } else if (feelingElement.compareTo("Joy") == 0) {
                    joyCount += 1;
                } else if (feelingElement.compareTo("Surprise") == 0) {
                    surpriseCount += 1;
                } else if (feelingElement.compareTo("Anger") == 0) {
                    angerCount += 1;
                } else if (feelingElement.compareTo("Sadness") == 0) {
                    sadnessCount += 1;
                } else if (feelingElement.compareTo("Fear") == 0) {
                    fearCount += 1;
                }
            }
            Log.d("t", "arrived here");
            Intent countIntent = new Intent(LonelyTwitterActivity.this, countActivity.class);
            countIntent.putExtra("loveCount", loveCount);
            countIntent.putExtra("joyCount", joyCount);
            countIntent.putExtra("surpriseCount", surpriseCount);
            countIntent.putExtra("angerCount", angerCount);
            countIntent.putExtra("sadnessCount", sadnessCount);
            countIntent.putExtra("fearCount", fearCount);
            startActivity(countIntent);


                                           }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent beBackIntent = new Intent(LonelyTwitterActivity.this,launchingActivity.class);
                startActivity(beBackIntent);
            }
        });



        /*saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
                String selection = mySpinner.getSelectedItem().toString();

                String myFeeling = "";

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

                ImportantTweet newTweet = new ImportantTweet();

                newTweet.setFeeling(myFeeling);

                String text = bodyText.getText().toString();

                try {
                    newTweet.setMessage(text);
                    newTweet.setDate(new Date());
                    tweets.add(newTweet);
                    saveInFile();
                    adapter.notifyDataSetChanged();
                }catch(TooLongTweetException e){

                }

            }
        });                                 */
    }

    @Override
    protected void onStart() {

        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<ImportantTweet>(this, R.layout.list_item, tweets);
        feelingList.setAdapter(adapter);
        this.registerForContextMenu(feelingList);

        Collections.sort(tweets,new sortByDate());
        adapter.notifyDataSetChanged();

        if (intentFromEdit == false) {
            Log.d("tag", "aaaaaaaaa");
            Intent intent5 = getIntent();
            feeling5 = intent5.getStringExtra("myFeeling");
            System.out.println("intent5-feeling: " + feeling5);
            comment5 = intent5.getStringExtra("comment");
            System.out.println("intent5-comment" + comment5);
        }
        else{
            feeling5 = null;
            comment5 = null;
        }

        if (feeling5 != null) {
            ImportantTweet newTweet = new ImportantTweet();
            newTweet.setFeeling(feeling5);


            try {
                newTweet.setMessage(comment5);
                newTweet.setDate(new Date());
                tweets.add(newTweet);
                saveInFile();
                adapter.notifyDataSetChanged();
            } catch (TooLongTweetException e) {
                //
            }
            Collections.sort(tweets, new sortByDate());
            saveInFile();
            adapter.notifyDataSetChanged();
            //Toast.makeText(getBaseContext(),"Record of emotion is successfully added.",Toast.LENGTH_LONG).show();
        }
        intentFromEdit = false;







    }

    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);

        // Find the position of  item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        item_id = info.position;


        //Set the display content of the menu
        menu.setHeaderTitle("Please select an option");
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.add(1,1,1,"View");
        menu.add(1,2,1,"Edit");
        menu.add(1,3,1,"Delete");

    }

    //Set response for items in the menu mentioned above
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1:
                Toast.makeText(this,"clicked View option",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(LonelyTwitterActivity.this,viewActivity.class);
                String dateString = tweets.get(item_id).getDate().toString();
                String comment = tweets.get(item_id).getMessage();
                String myFeeling = tweets.get(item_id).getFeeling();
                intent1.putExtra("dateString",dateString);
                intent1.putExtra("comment",comment);
                intent1.putExtra("feeling",myFeeling);
                startActivity(intent1);
                break;
            case 2:
                Toast.makeText(this,"clicked Edit option",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LonelyTwitterActivity.this,editActivity.class);
                tweets.remove(item_id);
                saveInFile();

                startActivityForResult(intent2,7);
                break;
            case 3:
                Toast.makeText(this,"clicked Delete option",Toast.LENGTH_SHORT).show();
                tweets.remove(item_id);
                saveInFile();
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 2){
            if(requestCode == 7){

                adapter = new ArrayAdapter<ImportantTweet>(this, R.layout.list_item, tweets);
                feelingList.setAdapter(adapter);

                String feeling = data.getStringExtra("emotion");
                String comment = data.getStringExtra("text");
                String dateContent = data.getStringExtra("dateContent");
                String specificTime = data.getStringExtra("specificTime");
                String modifiedTime = dateContent+" "+specificTime;
                Log.d("comment",comment);
                Log.d("modifiedTime",modifiedTime);
                ImportantTweet modifiedTweet = new ImportantTweet();
                try{
                    modifiedTweet.setFeeling(feeling);
                    Log.d("received feeling:",feeling);
                    modifiedTweet.setMessage(comment);
                    Log.d("comments from newTweet",comment);
                    Date myDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        myDate = sdf.parse(modifiedTime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    modifiedTweet.setDate(myDate);

                    Log.d("dates from newTweet",myDate.toString());
                }catch (TooLongTweetException e){
                    //
                }
                tweets.add(modifiedTweet);

                /*
                Iterator<ImportantTweet> tweetIterator = tweets.iterator();
                while(tweetIterator.hasNext()){
                    Log.d("tweets b4 set method: ",tweetIterator.next().toString());
                }
                Log.d("item",String.valueOf(item_id));
                feelingList.setAdapter(adapter);
                tweets.set(item_id,newTweet);
                adapter.notifyDataSetChanged();

                Log.d("tag:","arrived here!aaa");
                Iterator<ImportantTweet> tweetIterator2 = tweets.iterator();


                while(tweetIterator2.hasNext()){
                    Log.d("tweets b5 set method: ",tweetIterator2.next().toString());
                }

                Iterator<ImportantTweet> tweetIterator3 = tweets.iterator();


                while(tweetIterator3.hasNext()){
                    Log.d("tweets b6 set method: ",tweetIterator3.next().toString());
                }
                */

                Collections.sort(tweets,new sortByDate());

                adapter.notifyDataSetChanged();
                saveInFile();

                intentFromEdit = true;
            }
        }


    }



    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listTweetType = new TypeToken<ArrayList<ImportantTweet>>(){}.getType();
            /* It creats a type which is the arraylist containing I.*/
            tweets = gson.fromJson(reader,listTweetType);
            /* WE want the return type to be listTweetType.*/

        } catch (FileNotFoundException e) {
            tweets = new ArrayList<ImportantTweet>();
            e.printStackTrace();
        }

    }

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(tweets,writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}