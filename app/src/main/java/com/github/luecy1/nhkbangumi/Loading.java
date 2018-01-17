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
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("処理を実行中しています");
        mProgressDialog.setCancelable(true);
    }
    public void show(){
        mProgressDialog.show();
    }
    public void close(){
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
