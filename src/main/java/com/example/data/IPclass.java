package com.example.data;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class IPclass {
    OkHttpClient client = new OkHttpClient();

    public  String run() throws IOException {
        String url="http://ip-api.com/json/?lang=zh-CN";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getString("city");
        }
    }

//    public static void main(String[] args) {
//        String add = "";
//
//        IPclass tst1 = new IPclass();
//        try {
//            add = tst1.run();
//            add = add.substring(0, add.length() - 1);//删除最后一个字
//            System.out.println(add);
//        } catch (IOException e) {
//            //e.printStackTrace();
//        }
//
//
//    }
}
