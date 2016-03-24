package com.kingmonkey.fire2road;

import android.content.Context;
import android.content.Intent;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import java.util.List;

/**
 * Created by Jiovany on 21/03/2016.
 */
public class MarkerItemizedOverlay extends ItemizedIconOverlay<OverlayItem> {
    protected Context mContext;
    protected Utilities utilities;


    public MarkerItemizedOverlay(final Context context, final List<OverlayItem> aList) {
        super(context, aList, new OnItemGestureListener<OverlayItem>() {
            @Override public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                return false;
            }
            @Override public boolean onItemLongPress(final int index, final OverlayItem item) {
                return false;
            }
        } );
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    @Override
    protected boolean onSingleTapUpHelper(final int index, final OverlayItem item, final MapView mapView) {
        //Toast.makeText(mContext, "Item " + index + " has been tapped!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext,MecanicosDetailActivity.class);
        intent.putExtra("mecanicoJSONExtra",item.getSnippet().split("/")[1]);
        mContext.startActivity(intent);
        return true;
    }
}
