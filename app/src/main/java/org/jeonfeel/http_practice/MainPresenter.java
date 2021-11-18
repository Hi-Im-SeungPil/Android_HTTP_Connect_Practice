package org.jeonfeel.http_practice;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements contract.Presenter{

    @Override
    public void getOkHttp(Context context) {

        //객체 생성
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.d("response ","시작");
        //API 받을 url
        String url = "https://api.upbit.com/v1/ticker?markets=KRW-XRP";
        // client가 요청할 request 생성함.
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();

        //enqueue는 비동기 처리
        //execute는 동기
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String s = response.body().string();
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                    String market = (String) jsonObject.get("market");
                    String trade_date = (String) jsonObject.get("trade_date");
                    Double trade_price = (Double) jsonObject.get("trade_price");
                    Double highest_52_week_price = (Double) jsonObject.get("highest_52_week_price");

                    Model model = new Model(market,trade_date,trade_price,highest_52_week_price);

                    ((MainActivity)context).setTextView(model);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getRestful(Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("https://api.upbit.com/v1/ticker?markets=KRW-ADA");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuffer builder = new StringBuffer();

                    String inputString = null;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }

                    String s = builder.toString();
                    JSONArray jsonArray = new JSONArray(s);

                    conn.disconnect();
                    bufferedReader.close();
                    inputStream.close();

                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                    String market = (String) jsonObject.get("market");
                    String trade_date = (String) jsonObject.get("trade_date");
                    Double trade_price = (Double) jsonObject.get("trade_price");
                    Double highest_52_week_price = (Double) jsonObject.get("highest_52_week_price");

                    Model model = new Model(market,trade_date,trade_price,highest_52_week_price);

                    ((MainActivity)context).setTextView(model);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void getRetrofit(Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.upbit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        upbitService service = retrofit.create(upbitService.class);

        retrofit2.Call<List<Model>> call = service.getCoin("KRW-BORA");

        call.enqueue(new retrofit2.Callback<List<Model>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Model>> call, retrofit2.Response<List<Model>> response) {
                if (response.isSuccessful()){
                    List<Model> result = response.body();
                    ((MainActivity)context).setTextView(result.get(0));
                }else{
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Model>> call, Throwable t) {
                Log.d("response", "fail");
                Log.d("response", t.toString());
            }
        });

    }
}
