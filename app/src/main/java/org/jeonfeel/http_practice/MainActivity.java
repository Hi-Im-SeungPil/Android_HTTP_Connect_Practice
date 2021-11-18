package org.jeonfeel.http_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,contract.View{

    Button btn_restful,btn_okhttp,btn_retrofit;
    TextView tv_result;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();
        presenter = new MainPresenter();
        btn_okhttp.setOnClickListener(this);
        btn_restful.setOnClickListener(this);
        btn_retrofit.setOnClickListener(this);
    }

    private void FindViewById(){
        btn_okhttp = findViewById(R.id.btn_okhttp);
        btn_restful = findViewById(R.id.btn_restful);
        btn_retrofit = findViewById(R.id.btn_retrofit);
        tv_result = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        int ButtonId = view.getId();
        if (ButtonId == R.id.btn_okhttp){
            presenter.getOkHttp(MainActivity.this);
        }else if(ButtonId == R.id.btn_restful){
            presenter.getRestful(MainActivity.this);
        }else if(ButtonId == R.id.btn_retrofit){
            presenter.getRetrofit(MainActivity.this);
        }
    }

    @Override
    public void setTextView(Model model) {
        tv_result.setText(model.makeString());
    }
}