package com.tricktekno.animatedsplash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.transition.Slide;
import android.transition.Fade;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import static android.R.attr.value;
import static java.lang.Boolean.TRUE;

public class SecondActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    String url; // This will hold the full URL which will include the username entered in the etGitHubUser.
    List<String> list1 ;
    List<String> list2 ;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        requestQueue = Volley.newRequestQueue(this);
        setupToolbar();
        setupWindowAnimations();
        addItemsOnSpinner1();
       // addItemsOnSpinner2();
       addListenerOnSpinnerItemSelection();
        addListenerOnButton();
        bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



    }
    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        String baseUrl = "http://10.0.2.2:8000/circle_detail/";
        
        this.url = baseUrl ;
        list1 = new ArrayList<String>();

         final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();

        JsonArrayRequest arrReq = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    // Loop through the array elements
                                    for(int i=0;i<response.length();i++){
                                        // Get current json object
                                        JSONObject student = response.getJSONObject(i);
                                        // Get the current student (json object) data
                                        String circle_name = student.getString("circle_name");
                                        list1.add(circle_name);
                                        Log.e("circle_name\n", circle_name);
                                    }
                                }catch (JSONException e){
                                     Log.e("on my god exception  ::", e.toString());
                                    e.printStackTrace();
                                }
                               spinner1.setAdapter(dataAdapter); // here set the adapter on rcving response
                               //addItemsOnSpinner2(); // now set the items on spinner 2 based on prev result
                               //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                               //spinner1.setOnItemSelectedListener(this);
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
        
    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        //String baseUrl = "http://10.0.2.2:8000/technology_detail/"+ String.valueOf(spinner1.getSelectedItem());
        String baseUrl = "http://10.0.2.2:8000/technology_detail?circle="+ String.valueOf(spinner1.getSelectedItem());

        this.url = baseUrl ;
        list2 = new ArrayList<String>();

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, list2);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();

        JsonArrayRequest arrReq = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    // Loop through the array elements
                                    for(int i=0;i<response.length();i++){
                                        // Get current json object
                                        JSONObject tech = response.getJSONObject(i);
                                        // Get the current student (json object) data
                                        String technology = tech.getString("technology");
                                        list2.add(technology);
                                        Log.e("technology\n", technology);
                                    }
                                }catch (JSONException e){
                                     Log.e("on my god exception  ::", e.toString());
                                    e.printStackTrace();
                                }
                               spinner2.setAdapter(dataAdapter); // here set the adapter on rcving response
                               //spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
        /*
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list k");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);*/
        //spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        
    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner1.setOnItemSelectedListener(this);

    }
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*Toast.makeText(SecondActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();*/

                Intent myIntent = new Intent(SecondActivity.this, NewActivity.class);
                myIntent.putExtra("circle", String.valueOf(spinner1.getSelectedItem()));
                myIntent.putExtra("technology", String.valueOf(spinner2.getSelectedItem()));//Optional parameters
                SecondActivity.this.startActivity(myIntent,bundle);
            }

        });
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
            long arg3) {
            addItemsOnSpinner2();

            }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Log.e("hell you item ::","");
        // TODO Auto-generated method stub
    }
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        //Slide slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);
        //Fade fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(slide);
        getWindow().setAllowReturnTransitionOverlap(TRUE);
        getWindow().setAllowEnterTransitionOverlap(TRUE);

    }
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }




}
