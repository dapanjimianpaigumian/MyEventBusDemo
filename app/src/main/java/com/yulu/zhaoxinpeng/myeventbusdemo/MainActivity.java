package com.yulu.zhaoxinpeng.myeventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//监听事件的类
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv)
    TextView mMainTv;
    @BindView(R.id.main_btn)
    Button mMainBtn;
    private Unbinder bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    //相当于initactivity();
    @Override
    public void onContentChanged() {
        super.onContentChanged();

        //注册 EventBus
        EventBus.getDefault().register(this);

    }

    //订阅者（接收消息）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventBus(EventMessage eventMessage) {
        String string = eventMessage.getString();

        mMainTv.setText(string);

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁 EventBus
        EventBus.getDefault().unregister(this);

        //bind.unbind();
    }

    @OnClick(R.id.main_btn)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
