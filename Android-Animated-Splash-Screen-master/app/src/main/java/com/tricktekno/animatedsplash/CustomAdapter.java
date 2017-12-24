package com.tricktekno.animatedsplash;

/**
 * Created by root on 29/11/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;
/**
 * Created by Oclemy on 12/7/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter extends BaseAdapter {
    private ArrayList<Spaceship> spaceships;
    private int lastPos = -1;
    private Context c;
    public CustomAdapter(Context c,ArrayList<Spaceship> spaceships) {
        this.spaceships = spaceships;
        this.c = c;
    }
    @Override
    public int getCount() {
        return spaceships.size();
    }
    @Override
    public Object getItem(int i) {
        return spaceships.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    /*
    INFLATE XML LAYOUT TO VIEW
     */
    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }
        TextView nameTxt= (TextView) view.findViewById(R.id.nameTxt);
        ImageView img= (ImageView) view.findViewById(R.id.spacecraftImage);
       // SimpleRatingBar ratingBar= (SimpleRatingBar) view.findViewById(R.id.ratingBarID);
        SimpleRatingBar whatsapp_ratingBar= (SimpleRatingBar) view.findViewById(R.id.whatsapp_rating);
        SimpleRatingBar gmaps_ratingBar= (SimpleRatingBar) view.findViewById(R.id.gmaps_rating);
        SimpleRatingBar facebook_ratingBar= (SimpleRatingBar) view.findViewById(R.id.facebook_rating);
        SimpleRatingBar webbrowsing_ratingBar= (SimpleRatingBar) view.findViewById(R.id.webbrowsing_rating);
        SimpleRatingBar youtube_ratingBar= (SimpleRatingBar) view.findViewById(R.id.youtube_rating);
        SimpleRatingBar hdvideo_ratingBar= (SimpleRatingBar) view.findViewById(R.id.hdvideo_rating);
        final Spaceship s= (Spaceship) this.getItem(pos);


        nameTxt.setText(s.getName());
        //ratingBar.setRating(s.getRating());
        img.setImageResource(s.getImage());
        whatsapp_ratingBar.setRating(s.getWhatsapp_rating());
        facebook_ratingBar.setRating(s.getFb_rating());
        gmaps_ratingBar.setRating(s.getGmpas_rating());
        webbrowsing_ratingBar.setRating(s.getWebbrowsing_rating() );
        youtube_ratingBar.setRating(s.getYoutube_rating());
        hdvideo_ratingBar.setRating(s.getHd_rating());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, s.getName()+ " Rating : "+String.valueOf(s.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        //Animation animation = AnimationUtils.loadAnimation(NewActivity.getAppContext(), (pos > lastPos) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //view.startAnimation(animation);
        //lastPos = pos;
        return view;
    }
}

//https://stackoverflow.com/questions/35919060/animating-display-of-items-in-listview-in-android