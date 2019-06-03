package com.shenkangyun.healthcenter.MainPage.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.ExampleUtil;
import com.shenkangyun.healthcenter.UtilFolder.LocalBroadcastManager;
import com.shenkangyun.healthcenter.UtilFolder.NetworkChangeReceiver;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;

    private int age;
    private IntentFilter filter;
    private IntentFilter intentFilter;
    private NetworkChangeReceiver changeReceiver;
    public static boolean isForeground = false;

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        registerMessageReceiver();
        initView();
    }

    private void initView() {
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            name = all.get(i).getName();
            age = all.get(i).getAge();
        }

        if (!TextUtils.isEmpty(name)) {
            String firstWord = name.substring(0, 1);
            StringBuilder builder = new StringBuilder();
            builder.append(String.valueOf(age)).append("岁");
            tvName.setText(name);
            tvWord.setText(firstWord);
            tvAge.setText(builder.toString());
        }
    }

    @OnClick({R.id.cv_interactive, R.id.cv_knowledge, R.id.cv_survey, R.id.cv_axis, R.id.cv_record, R.id.cv_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_interactive:
                Toast.makeText(this, "功能暂未开放！", Toast.LENGTH_SHORT).show();
//                Intent intentMine = new Intent(this, FriendsListActivity.class);
//                startActivity(intentMine);
                break;
            case R.id.cv_knowledge:
                Intent intentKnowledge = new Intent(this, KnowledgeActivity.class);
                startActivity(intentKnowledge);
                break;
            case R.id.cv_survey:
                Intent intentQuestion = new Intent(this, QuestionnaireActivity.class);
                startActivity(intentQuestion);
                break;
            case R.id.cv_axis:
                Intent intentPatient = new Intent(this, PatientMessageActivity.class);
                startActivity(intentPatient);
                break;
            case R.id.cv_record:
                Intent intentMedical = new Intent(this, MedicalRecordsActivity.class);
                startActivity(intentMedical);
                break;
            case R.id.cv_user:
                Intent intentPersonal = new Intent(this, PersonalActivity.class);
                startActivityForResult(intentPersonal, 0);
                break;
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(changeReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    public void registerMessageReceiver() {
        intentFilter = new IntentFilter();
        changeReceiver = new NetworkChangeReceiver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(changeReceiver, intentFilter);

        mMessageReceiver = new MessageReceiver();
        filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        JPushInterface.init(getApplicationContext());
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    Log.i("JIGUANG-Example", "onReceive: " + messge);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                        Log.i("1234567", "onReceive: " + showMsg.toString());
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            finish();
        }
        if (requestCode == 0 && resultCode == 2) {
            finish();
        }
    }
}
