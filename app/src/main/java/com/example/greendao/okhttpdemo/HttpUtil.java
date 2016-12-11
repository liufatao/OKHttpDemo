package com.example.greendao.okhttpdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class HttpUtil {
    OkHttpClient okHttpClient;
    Context context;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

     public HttpUtil(Context context){
         this.context=context;
     }

    /***
     * okhttp post请求
     */
    public void PostHttp(){
        okHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request=new Request.Builder().url("").post(formBody).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               //成功

            }
        });
    }

    /***
     * okhttp
     * get请求
     */
    public  void GetHttp(){

        okHttpClient=new OkHttpClient();
        Request.Builder builder=new Request.Builder().url("");
        builder.method("GET",null);
        Request request=builder.build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功
            }
        });
    }

    /***
     * okhttp 上传文件
     */
    public void postFile(){
        okHttpClient=new OkHttpClient();
        File file = new File("/sdcard/wangshu.txt");
        Request request=new Request.Builder().url("").post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file)).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public void imageLoad(){

        //初始化OkHttpClient
        final OkHttpClient client = new OkHttpClient();

        //创建OkHttpClient针对某个url的数据请求
        Request request = new Request.Builder().url("图片路径").build();

        Call call = client.newCall(request);

        //请求加入队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //此处处理请求失败的业务逻辑
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //我写的这个例子是请求一个图片
                //response的body是图片的byte字节
                byte[] bytes = response.body().bytes();
                //response.body().close();

                //把byte字节组装成图片
                final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                //回调是运行在非ui主线程，
                //数据请求成功后，在主线程中更新
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //网络图片请求成功，更新到主线程的ImageView
//                        image.setImageBitmap(bmp);
//                    }
//                });
            }
        });
    }
    public void geGlideImageLoad(){
        ImageView imageView=new ImageView(context);
        Glide.with(context).load("").placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);
    }

}
