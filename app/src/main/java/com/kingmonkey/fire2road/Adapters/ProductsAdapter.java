package com.kingmonkey.fire2road.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.kingmonkey.fire2road.Domain.MecanicoObject;
import com.kingmonkey.fire2road.Domain.ProductObject;
import com.kingmonkey.fire2road.R;
import com.kingmonkey.fire2road.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jiovany on 04/04/2016.
 */
public class ProductsAdapter  extends ArrayAdapter<ProductObject> {
        Context mContext;
        int layoutResourceId;
        ProductObject data[] = null;
        Utilities utilities ;

        public ProductsAdapter(Context mContext, int layoutResourceId, ProductObject[] data) {

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

            ImageView productImage = (ImageView)convertView.findViewById(R.id.productImage);
            Picasso.with(mContext).load("http://www.cycleworld.com/sites/cycleworld.com/files/Arai-Corsair-X-RX-7X_HAYDEN_P.jpg").fit().centerInside().into(productImage);

            // object item based on the position
            final ProductObject objectItem = data[position];

            TextView productName = (TextView) convertView.findViewById(R.id.productName);
            TextView productShortDescription = (TextView) convertView.findViewById(R.id.productDescription);
            TextView productPrice = (TextView) convertView.findViewById(R.id.productPrice);


            return convertView;

        }
}
