package com.kingmonkey.fire2road;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.github.johnpersano.supertoasts.SuperToast;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Jiovany on 21/03/2016.
 */
public class MarkerItemizedOverlay extends ItemizedIconOverlay<OverlayItem> {
    protected Context mContext;
    protected Utilities utilities;


    public MarkerItemizedOverlay(final Context context, final List<OverlayItem> aList, ArrayList<MecanicoObject> mecanicoObjects) {
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
        new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(item.getTitle())
                .setContentText(item.getSnippet())
                .setConfirmText("Detalles")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();
        return true;
    }
}
