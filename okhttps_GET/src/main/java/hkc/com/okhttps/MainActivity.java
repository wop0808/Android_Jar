package hkc.com.okhttps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = "crazyK";
    //实例化客户Client
    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    /**
     * 同步的方式操作网络，故需要开一个线程
     * GET
     * */
    public void startBySyn(String murl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //实例化请求request
                Request request = new Request.Builder().url(murl).build();
                //实例化回应response
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(response!= null && response.isSuccessful()){
                    int code = response.code();
                    String body = response.body().toString();
                    Log.i(TAG, "code: " +code + " , body: "+body );
                }
            }
        }).start();

    }

    /**
     * 异步的方式操作网路
     * GET
     * */
    public void startByBi(String murl){
        //实例化请求
        Request request = new Request.Builder().url(murl).build();
        //发起请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!= null && response.isSuccessful()){
                    int code = response.code();
                    String body = response.body().toString();
                    Log.i(TAG, "code: " +code + " , body: "+body );
                }
                //// TODO: 2016/8/22
                //此处是非UI线程，若要操作UI需自行传值
            }
        });
    }

    /**
     * POST请求
     * header哪里是什么意思
     * */
    public void startByPOST(String murl){
        Request request = new Request.Builder()
                .url(murl)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        // TODO: 2016/8/22 RequestBody的构造方法与网上不同
        RequestBody body = RequestBody.create()



    }



}
