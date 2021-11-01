package live;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
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

    public WeatherData weather(String cityname){
        WeatherData data = new WeatherData();

        String path = "http://gwgp-h4bqkmub7dg.n.bdcloudapi.com/day";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.GET, path);
        request.setCredentials("347bd553e4ea48f386fe767aebd7878c", "714d681bef8c46db9f675497251f48e8");

        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        request.addQueryParameter("cityid", "");
        request.addQueryParameter("city", cityname);
        request.addQueryParameter("province", "");
        request.addQueryParameter("ip", "");


        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());


            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(response.getResult());
            //System.out.println(            jsonObject.getStr("city"));
            data.weather= jsonObject.getStr("wea");
            data.date= jsonObject.getStr("date");
            data.week= jsonObject.getStr("week");
            data.update_time= jsonObject.getStr("update_time");


        } catch (Exception e) {
            //e.printStackTrace();
        }

        return data;
    }

    public void Movie(String cityname){

        // 按城市获取电影院
        MovieData movieData = new MovieData();
        String[] aa = new String[30];


        String path = "http://gwgp-qvaqxkx3eoc.n.bdcloudapi.com/movie/theater";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials("347bd553e4ea48f386fe767aebd7878c", "714d681bef8c46db9f675497251f48e8");

        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        request.addQueryParameter("cityid", "");
        request.addQueryParameter("city", cityname);
        request.addQueryParameter("keyword", "");



        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(response.getResult());
            String re=jsonObject.getStr("result");
            jsonObject = JSONUtil.parseObj(re);
            re=jsonObject.getStr("list");
            re=re.substring(1,re.length()-1);//删除最后一个字和第一个字
            aa=getStringList(re);

           //System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String s:aa){
            System.out.println(s);

        }


        //return movieData;
    }

    public String[] getStringList(String str) {
        int i=0;
        String[] strlist = str.split("},*");
        System.out.println(strlist.length);
        for (String s : strlist) {
            strlist[i] = s.substring(1);//删除和第一个字{
            i++;
            //System.out.println(s);}
        }
        return strlist;
        }
    public static void main(String[] args) {
        String add="";
        WeatherData data=new WeatherData();
        IPclass tst1 =new IPclass();
        try {
            add=tst1.run();
            add=add.substring(0,add.length()-1);//删除最后一个字
            System.out.println(add);
        } catch (IOException e) {
            //e.printStackTrace();
        }

//测试天气
//        data=tst1.weather(add);
//        System.out.println(data.date);
//        System.out.println(data.weather);


        //测试当前城市上映的电影
       //tst1.Movie(add);


}
}