package com.github.luecy1.nhkbangumi;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by you on 2017/12/25.
 */

public class Loading {
    Context mContext;
    ProgressDialog mProgressDialog;

    public Loading(Context context){
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
    }
    public void show(){
        mProgressDialog.setContentView(R.layout.loading);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }
    public void close(){
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
