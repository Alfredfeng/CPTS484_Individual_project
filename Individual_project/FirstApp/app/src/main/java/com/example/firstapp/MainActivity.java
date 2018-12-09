package com.example.firstapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int num_right_answer = 0;
    private int all_problems = 10;

    //time elements
    private int seconds = 0;

    private TextView timeView;

    //database elements
    private SQLiteOpenHelper mohaDatabaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private TextView textView_answer;
    private TextView textView_question;
    private int time_dedicated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String moha_question = "";//the question to display

        Button buttonA = (Button) findViewById(R.id.choiceA);
        Button buttonB = (Button) findViewById(R.id.choiceB);
        Button buttonC = (Button)findViewById(R.id.choiceC);
        Button buttonD = (Button) findViewById(R.id.choiceD);

        time_dedicated = 0;//initialize time dedicated to 0
        runTimer();//run timer


        textView_question = (TextView) findViewById(R.id.question);//TextView for displaying the question
        mohaDatabaseHelper = new MohaDatabaseHelper(this);


        try {
            db = mohaDatabaseHelper.getReadableDatabase();//get a database
            //Code to read data from the database
            //use Integer.toString() to convert int _id to a String
            cursor = db.query("MOHA",new String[] {"_id","QUESTION", "ANSWER"}, null, null, null, null, null);
            //cursor.moveToFirst();

            if(cursor.moveToFirst()){ //initialize the question
                moha_question = cursor.getString(1);//get the question
                System.out.println(moha_question);//print the string
                textView_question.setText(moha_question);
                //textView_question.setText(moha_question);
            }


        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void runTimer(){
        //runTimer Method
        timeView = (TextView) findViewById(R.id.time_view);//get the text view
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds%3600)/59;
                int secs = seconds%59;
                String time = String.format(Locale.getDefault(),
                        "%02d:%02d elapsed",  minutes, secs);
                time += " you dedicated "+Integer.toString(time_dedicated)+"s";
                if(secs == 0)
                    time_dedicated ++;//increment time dedicated
                timeView.setText(time);
                seconds++;
                handler.postDelayed(this, 1000);
            }
        });

    }

    public void onClickNext(View view){
        // this function shows the next questsion
        //update cursor
        if(cursor.moveToNext()){//if there's another entry in the database ,update the entry
            textView_question.setText(cursor.getString(1));
        }
    }

    public void onClickPrev(View  view){
        //this function shows the previous question
        //update cursor
        if(cursor.moveToPrevious()){//if there's another entry in the database ,update the entry
            textView_question.setText(cursor.getString(1));
        }
    }

    public void onClickA(View view){
        String answer = cursor.getString(2);
        textView_answer = (TextView) findViewById(R.id.AnswerStatus);
        textView_question = (TextView) findViewById(R.id.question);

        if(answer.equals("A")){
            num_right_answer++;//increment the number of right answers
            textView_answer.setText("恭喜你，答对了! Excited!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems));
        }
        else{
            textView_answer.setText("Angry!还要学习一个!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems)+"\n正确答案是"+answer);
        }


    }

    public void onClickB(View view){
        String answer = cursor.getString(2);
        textView_answer = (TextView) findViewById(R.id.AnswerStatus);

        if(answer.equals("B")){
            num_right_answer++;//increment the number of right answers
            textView_answer.setText("恭喜你，答对了! Excited!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems));
        }
        else{
            textView_answer.setText("Angry!还要学习一个!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems)+"\n正确答案是"+answer);
        }

    }

    public void onClickC(View view){
        String answer = cursor.getString(2);
        textView_answer = (TextView) findViewById(R.id.AnswerStatus);


        if(answer.equals("C")){
            num_right_answer++;//increment the number of right answers
            textView_answer.setText("恭喜你，答对了! Excited!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems));
        }
        else{
            textView_answer.setText("Angry!还要学习一个!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems)+"\n正确答案是"+answer);
        }


    }

    public void onClickD(View view){
        //code for clicking choice D
        String answer = cursor.getString(2);
        textView_answer = (TextView) findViewById(R.id.AnswerStatus);

        if(answer.equals("D")){
            num_right_answer++;//increment the number of right answers
            textView_answer.setText("恭喜你，答对了! Excited!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems));
        }
        else{
            textView_answer.setText("Angry!还要学习一个!\n当前答对题目:"+Integer.toString(num_right_answer)+"/"+Integer.toString(all_problems));
        }
    }
}
