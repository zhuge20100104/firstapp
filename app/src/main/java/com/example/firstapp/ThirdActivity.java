package com.example.firstapp;



import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ThirdActivity extends Activity {
    public Button btnDown;
    public ImageView ivImage;
    public static String image_path = "http://pic37.nipic.com/20140113/8800276_184927469000_2.png";
    public ProgressDialog dialog;
    public static int IS_FINISH = 1;

    public Bitmap rawBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnDown = (Button) findViewById(R.id.btnDown);
        ivImage = (ImageView) findViewById(R.id.ivSinaImage);

        dialog = new ProgressDialog(this);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setCancelable(false);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }


    public void loadData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(image_path)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("hehe","on Failure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到从网上获取资源，转换成我们想要的类型
                InputStream inputStream = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                rawBitmap = bitmap;
                //通过handler更新UI
                Message message = handler.obtainMessage();
                message.obj = bitmap;
                message.what = IS_FINISH;
                handler.sendMessage(message);
            }
        });
        dialog.show();
    }

    private  Handler handler = new Handler() {
        // 在Handler中获取消息，重写handleMessage()方法
        @Override
        public void handleMessage(Message msg) {
            // 判断消息码是否为1
            if(msg.what==IS_FINISH){
                Bitmap bmp =(Bitmap)msg.obj;
                ivImage.setImageBitmap(bmp);
                dialog.dismiss();
            }
        }
    };

}