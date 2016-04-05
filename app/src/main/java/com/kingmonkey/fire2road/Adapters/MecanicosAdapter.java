package com.kingmonkey.fire2road.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.kingmonkey.fire2road.Domain.MecanicoObject;
import com.kingmonkey.fire2road.R;
import com.kingmonkey.fire2road.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jiovany on 20/03/2016.
 */
public class MecanicosAdapter  extends ArrayAdapter<MecanicoObject> {
    Context mContext;
    int layoutResourceId;
    MecanicoObject data[] = null;
    Utilities utilities ;

    public MecanicosAdapter(Context mContext, int layoutResourceId, MecanicoObject[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        utilities = Utilities.getInstance(mContext);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        RatingBar ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating(4.0f);

        CircleImageView iconoMecanico = (CircleImageView)convertView.findViewById(R.id.mecanicoIcon);
        Picasso.with(mContext).load("http://www.motoscasademont.com/wp-content/uploads/2012/01/click-mecanico1.jpg").into(iconoMecanico);

        // object item based on the position
        final MecanicoObject objectItem = data[position];
        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.titulo);
        CardView cardView = (CardView)convertView.findViewById(R.id.card_view);
        textViewItem.setText(objectItem.getNombre());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilities.makeSimpleToast(objectItem.getNombre(), SuperToast.Duration.MEDIUM);
            }
        });


        return convertView;

    }
}