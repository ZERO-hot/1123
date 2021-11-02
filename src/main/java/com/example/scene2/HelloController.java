package com.example.scene2;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.data.IPclass;
import com.example.data.JsonRootBean;
import com.example.data.MyBean;
import com.example.jdbc.DB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.HashMap;

public class HelloController {


    @FXML
    private Label show,ipclass;
    @FXML
    private TextField number;
    @FXML
    private TableView table;

    @FXML
    protected void onHelloButtonClick() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                String baseUrl="https://widget.cifuwu.com/ip/";
                String BVId=number.getText();
                String res="";
                DB db = new DB("jdbc:mysql://localhost:3306/ips?characterEncoding=UTF-8","root","mysql");
//                if((res=db.get(BVId))!="null"){
//                }
//                else{
//                    res="";
//                    baseUrl+=BVId;
//                    String result = HttpUtil.get(baseUrl);
//                    JsonRootBean bWebSiteData = JSONUtil.toBean(result, JsonRootBean.class);
//                    res+=bWebSiteData.getData().getRef_2().getCountry();
//                    res+=bWebSiteData.getData().getRef_2().getProvince();
//                    res+=bWebSiteData.getData().getRef_2().getCity();
//                    db.add(BVId,res);
//                }
                res="";
                baseUrl+=BVId;
                String result = HttpUtil.get(baseUrl);
                JsonRootBean bWebSiteData = JSONUtil.toBean(result, JsonRootBean.class);
                res+=bWebSiteData.getData().getRef_2().getCountry();
                res+=bWebSiteData.getData().getRef_2().getProvince();
                res+=bWebSiteData.getData().getRef_2().getCity();
                db.put(res);
                show.setText(res);
            }

        });
    }

    @FXML
    protected void onMyClick(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                DB db = new DB("jdbc:mysql://127.0.0.1:3306/ips?characterEncoding=UTF-8","root","mysql");
                HashMap hashMap = db.getAll();
                ObservableList<MyBean> data=FXCollections.observableArrayList();
                TableColumn id = new TableColumn("IP");
                id.setCellValueFactory(new PropertyValueFactory<>("ip"));
                TableColumn url = new TableColumn("location");
                url.setCellValueFactory(new PropertyValueFactory<>("des"));
                table.getColumns().addAll(id,url);
                table.setItems(data);
                for (Object key:hashMap.keySet()
                ) {
                    String k=(String)key;
                    data.add(new MyBean(k,(String)hashMap.get(k)));
                }
                table.setVisible(true);
            }
        });
    }
    @FXML
    protected void click(){
        DB db = new DB("jdbc:mysql://localhost:3306/ips?characterEncoding=UTF-8","root","mysql");
        IPclass iPclass = new IPclass();
        String res="";
        try {
            res=iPclass.run();
            db.put(res);
            ipclass.setText(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
