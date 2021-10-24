package com.example._1123;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.data.JSONRootBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML
    private TextField qq;
    @FXML
    private ImageView ans;
    @FXML
    protected void onHelloButtonClick() {
        String number=qq.getText();
        String BaseUrl="https://tenapi.cn/qqtx/?qq=";
        String result = HttpUtil.get(BaseUrl+number);

        JSONRootBean res= JSONUtil.toBean(result, JSONRootBean.class);
        Image img=new Image(res.getData().getUrl());

        ans.setImage(img);
    }
}