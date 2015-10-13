package com.konvy.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.konvy.okhttpdemo.util.OkHttpClientManager;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.id_iv);

        Glide.get(getApplicationContext()).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientManager.getClinet()));
        Glide.with(this).load("http://ww2.sinaimg.cn/mw690/e5298966gw1evyjrd4iqcj208c03c0t0.jpg").into(iv);

        /*
        tv = (TextView) findViewById(R.id.id_tv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkHttpClientManager.getInstance().getGetDelegate().get("http://www.hao224.com");
                    final String htmlStr = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(htmlStr);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */


        /*
        OkHttpClientManager.getAsyn("http://www.hao224.com", new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                tv.setText(response);
            }
        });
        */

        /*
        OkHttpClient mClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.hao224.com").build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String htmlStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.id_tv);
                        tv.setText(htmlStr);
                    }
                });
            }
        });
        */
    }

}
