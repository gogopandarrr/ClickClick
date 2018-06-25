package com.q1.clickclick;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class MainActivity extends AppCompatActivity {



long basetime;
LinearLayout windowF, windowB;
ImageView[] iv = new ImageView[12];
ImageView start;
TextView top, tv_stage, tv_currentRecord;
int[]ran = new int[12];
int count;
int count_stage;
boolean clear =false;
CountDownTimer countDownTimer;
RelativeLayout bg;
int count_total=4*1000;
int count_interval = 1000;
ListView listView;
ListAdapter adapter;
    ArrayList<Records> recordsArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bg = findViewById(R.id.bg);
        listView = findViewById(R.id.listView);
        adapter = new ListAdapter(recordsArrayList,getLayoutInflater());
        listView.setAdapter(adapter);


        tv_currentRecord = findViewById(R.id.tv_currentRecord);
        windowF = findViewById(R.id.window_front);
        windowB = findViewById(R.id.window_back);
        start = findViewById(R.id.btn_start);
        top = findViewById(R.id.tv_Top);
        tv_stage = findViewById(R.id.tv_stage);
        for(int i=0;i<12;i++){

            iv[i] = findViewById(R.id.btn_01+i);
            iv[i].setVisibility(View.VISIBLE);

        }


        AdView adView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        countDownTimer = new CountDownTimer(count_total, count_interval) {
            @Override
            public void onTick(long l) {

                tv_stage.setText((int)(l/1000)+"");


            }

            @Override
            public void onFinish() {

                stage1();

            }
        };







    }//oncreat


public void shuffle(){


for(int i=0;i<12;i++){



    Random rnd = new Random();
    int n = rnd.nextInt(12);

    ran[i]=n;

    iv[i].setTag(ran[i]);


    for(int k =0; k<i;k++){


        if(ran[i]==ran[k]){

            i--;
            break;

        }


    }

    }

    }//sett

    public void stage1(){


        bg.setBackgroundResource(R.drawable.bg1);

        basetime = SystemClock.elapsedRealtime();

        timer.sendEmptyMessage(0);

        tv_stage.setText("Stage 1");
        count =0;
        shuffle();

        for(int i=0;i<12;i++){

        iv[i].setVisibility(View.VISIBLE);
        iv[i].setImageResource(R.drawable.num01+ran[i]);


    }



}
    public void stage2(){

        tv_stage.setText("Stage 2");
        bg.setBackgroundResource(R.drawable.bg2);
        count =0;
        shuffle();

        for(int i=0;i<12;i++){

            iv[i].setVisibility(View.VISIBLE);
            iv[i].setImageResource(R.drawable.alpa01+ran[i]);


        }



    }

    public void stage3(){

        tv_stage.setText("Stage 3");
        bg.setBackgroundResource(R.drawable.bg3);
        count =0;
        shuffle();

        for(int i=0;i<12;i++){

            iv[i].setVisibility(View.VISIBLE);
            iv[i].setImageResource(R.drawable.cha01+ran[i]);


        }



    }
    public void stage4(){

        tv_stage.setText("Stage 4");
        bg.setBackgroundResource(R.drawable.bg4);
        count =0;
        shuffle();

        for(int i=0;i<12;i++){

            iv[i].setVisibility(View.VISIBLE);
            iv[i].setImageResource(R.drawable.han01+ran[i]);


        }



    }
    public void stage5(){

        tv_stage.setText("Stage 5");
        bg.setBackgroundResource(R.drawable.bg5);
        count =0;
        shuffle();

        for(int i=0;i<12;i++){

            iv[i].setVisibility(View.VISIBLE);
            iv[i].setImageResource(R.drawable.rom01+ran[i]);


        }



    }
    @Override
    protected void onDestroy() {

        timer.removeMessages(0);


        super.onDestroy();

    }

    Handler timer = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            timer.sendEmptyMessage(0);

            top.setText(timeFormat(getTime()));






        }
    };


    long getTime(){

        long now = SystemClock.elapsedRealtime();

        long timestart = now - basetime;


        return timestart;


    }



    static String timeFormat(long gettime){


       long timestart = gettime;


        int start_sec = (int) (timestart / 1000);

        int minutes = start_sec / 60;


        int seconds = start_sec % 60;

        long millis = (timestart/10)%100;


        String formated = String.format("%02d:%02d:%02d", minutes, seconds,millis);

        return formated;


    }






public void startClick(View v){




    ((ImageView)v).setImageResource(R.drawable.ing);

    windowB.setVisibility(View.INVISIBLE);
    windowF.setVisibility(View.VISIBLE);

    tv_currentRecord.setText("wait..");
    count_stage = 0;

    countDownTimer.start();

    timer.removeMessages(0);




}

public void onClick(View v){

    String s = v.getTag().toString();

    int tag = Integer.parseInt(s);

    if(count == tag){

        v.setVisibility(View.INVISIBLE);


        count++;
        count_stage++;

    }

    if(count > 11){

        clear = true;

    }


    if(count_stage == 12 && clear){
        clear = false;
        stage2();


    }

   else if(count_stage == 24 && clear){

        clear =false;
        stage3();
    }
    if(count_stage == 36 && clear){

        clear =false;
        stage4();

   }  if(count_stage == 48 && clear){

        clear =false;
        stage5();

    }  if(count_stage == 60 && clear){

        setgameover();

    }








}


public void setgameover(){

    bg.setBackgroundResource(R.drawable.bg_ed);

    start.setImageResource(R.drawable.start);

    tv_stage.setText("RECORD");


    windowB.setVisibility(View.VISIBLE);

    windowF.setVisibility(View.INVISIBLE);


    timer.removeMessages(0);


    String data = timeFormat(getTime());


    Records s = new Records(getTime());

    tv_currentRecord.setText(data);

    recordsArrayList.add(s);





    try {
        FileOutputStream fos = openFileOutput("data1.txt",MODE_APPEND);

        PrintWriter writer = new PrintWriter(fos);

        writer.println(getTime()+"");
        writer.flush();
        writer.close();
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }




    try {
        FileInputStream fis = openFileInput("data1.txt");

        InputStreamReader isr = new InputStreamReader(fis);

        BufferedReader reader = new BufferedReader(isr);

        StringBuffer buffer = new StringBuffer();

        String line = reader.readLine();


        while(line!=null){


            buffer.append(line+"\n");


           // Log.i("tag", "a"+line+"b");
            recordsArrayList.add(new Records(Long.parseLong(line)));
            line = reader.readLine();

        }

        Comparator<Records> cmpAsc = new Comparator<Records>() {
            @Override
            public int compare(Records t0, Records t1) {

                int ret;

                if(t0.getTimeRecord() < t1.getTimeRecord()){

                    ret = -1;
                }
                else if(t0.getTimeRecord()==t1.getTimeRecord()){


                    ret = 0;
                }
                else ret = 1;



                return ret;
            }
        };

        Collections.sort(recordsArrayList, cmpAsc);



        for(int i=0; i<recordsArrayList.size();i++){



            if(recordsArrayList.get(i).equals(s)){

                listView.setSelection(i);


            }

        }





        adapter.notifyDataSetChanged();

        Toast.makeText(this, "loaded", Toast.LENGTH_SHORT).show();



    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


}//gameover

public void clickDel(View v){




}


    }//class
