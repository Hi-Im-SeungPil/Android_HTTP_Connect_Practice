package org.jeonfeel.http_practice;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public interface contract {

    interface View{
        void setTextView(Model model);
    }

    interface Presenter{

        void getOkHttp(Context context);
        void getRestful(Context context);
        void getRetrofit(Context context);

    }


}
