package com.yulu.zhaoxinpeng.myeventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//发送事件的类
public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.main2_btn)
    Button mMain2Btn;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        bind.unbind();
        //销毁 EventBus
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.main2_btn)
    public void onViewClicked() {

        EventMessage message = new EventMessage();

        message.setString("123123");

        EventBus.getDefault().post(message);
    }
    //另一种方法：利用构造传值
    //EventBus.getDefault().post(new EventMessage("直面现实吧"));

}
