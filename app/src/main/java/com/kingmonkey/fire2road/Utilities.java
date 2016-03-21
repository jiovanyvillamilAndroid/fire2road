package com.kingmonkey.fire2road;

import android.app.Activity;
import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Jiovany on 20/03/2016.
 */
public class Utilities {
    private static Utilities mInstance = null;
    Context context;
    private Utilities(Context c) {
        context = c;
    }

    public static Utilities getInstance(Context c){
        if(mInstance == null)
        {
            mInstance = new Utilities(c);
        }
        return mInstance;
    }

    public void makeSimpleToast(String m, int duration){
        SuperToast superToast = new SuperToast(context);
        superToast.setDuration(duration);
        superToast.setAnimations(SuperToast.Animations.POPUP);
        superToast.setText(m);
        superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
        superToast.show();
    }

    public void showDialogError(String m){
        if(!((Activity) context).isFinishing())
        {
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(m)
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }

    }
}
