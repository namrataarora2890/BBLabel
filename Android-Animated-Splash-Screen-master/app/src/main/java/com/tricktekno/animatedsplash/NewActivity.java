package com.tricktekno.animatedsplash;

/**
 * Created by root on 29/11/17.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tricktekno.animatedsplash.SpaceshipCollection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class NewActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    private static final String TAG = "your activity name :::";
    ArrayList<Spaceship> spaceships=new ArrayList<>();
    ListView lv;
    String baseUrl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////////////////////
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        ////////////////////////////////////////////////////////////
        //getActionBar().setIcon(R.drawable.my_icon);
        setupWindowAnimations();
        Intent intent = getIntent();
        /*****************************************************/
        String circle = intent.getStringExtra("circle");
        String technology = intent.getStringExtra("technology");
        baseUrl= "http://10.0.2.2:8000/star_rating?"+"circle="+circle+"&tech="+technology ;
        getstarrating();//if it's a string you stored.
        String st= circle+" "+technology;
        //setTitle(st);
        setupToolbar(st);

        /*****************************************************/
        lv= (ListView) findViewById(R.id.lv);
        //Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);

        //this.getActionBar().setDisplayShowCustomEnabled(true);
        //this.getActionBar().setDisplayShowTitleEnabled(false);



        //lv.setAdapter(new CustomAdapter(this,SpaceshipCollection.getSpaceships() )); // create an array list of spaceships and pass here
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String circle = intent.getStringExtra("circle");
        String technology = intent.getStringExtra("technology");
        String st = circle + " " + technology;
        //setTitle(st);
        setupToolbar(st);
    }
    @Override
    public void onPause() {
        super.onPause();
        Intent intent = getIntent();
        String circle = intent.getStringExtra("circle");
        String technology = intent.getStringExtra("technology");
        String st = circle + " " + technology;
        //setTitle(st);
        setupToolbar(st);
    }


    public void getstarrating() {
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest arrReq = new JsonArrayRequest(
                Request.Method.GET,
                baseUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            // Loop through the array elements

                            for(int i=0;i<response.length();i++){

                                Spaceship s=new Spaceship();
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);
                                // Get the current student (json object) data
                                String operator_name = student.getString("circle_operator");
                                s.setName(operator_name );
                                JSONObject score = student.getJSONObject("score");

                                String whatsapp_mos = score.getString("whatsapp_mos");
                                s.setWhatsapp_rating(Float.parseFloat(whatsapp_mos));

                                String facebook_mos = score.getString("facebook_mos");
                                s.setFb_rating(Float.parseFloat(facebook_mos));

                                String gmaps_mos = score.getString("gmaps_mos");
                                s.setGmpas_rating(Float.parseFloat(gmaps_mos));

                                String webbrowsing_mos = score.getString("webbrowsing_mos");
                                s.setWebbrowsing_rating(Float.parseFloat(webbrowsing_mos));

                                String youtube_mos = score.getString("youtube_mos");
                                s.setYoutube_rating(Float.parseFloat(youtube_mos));

                                String hdvideo_mos = score.getString("hdvideo_mos");
                                s.setHd_rating(Float.parseFloat(hdvideo_mos));

                                //s.setImage(R.drawable.spitzer);




                                spaceships.add(s);





                                //arrayList.add(map);
                                Log.d(TAG,operator_name);

                                Log.d(TAG,whatsapp_mos);

                            }
                        }catch (JSONException e){
                            Log.e("on my god exception  ::", e.toString());
                            e.printStackTrace();
                        }

                        lv.setAdapter(new CustomAdapter(getApplicationContext(),spaceships));
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("hell you Volley ::", error.toString());
                    }
                }
        );

        requestQueue.add(arrReq);

        Log.d(TAG,"after set adapter .....");

    }
    private void setupWindowAnimations() {
        boolean x=FALSE;
        Slide slide = new Slide();
        slide.setDuration(1000);
        //Slide slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);
        //Fade fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);

        Fade fade = new Fade();
        fade.setDuration(1000);
        //getWindow().setEnterTransition(slide);
        //getWindow().setReenterTransition(slide);
        getWindow().setReturnTransition(slide);
        //getWindow().setAllowReturnTransitionOverlap(x);
        //getWindow().setBackgroundDrawableResource(R.drawable.logo);


    }
    public static Context getAppContext() {
        return getAppContext();
    }
    private void setupToolbar(CharSequence s) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(s);
    }


}