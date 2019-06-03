package com.shenkangyun.healthcenter.LoginPage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgreementActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black));
        ButterKnife.bind(this);
        sp = getSharedPreferences("Agreement", MODE_PRIVATE);
        editor = sp.edit();
    }

    @OnClick({R.id.btn_accept, R.id.btn_decline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_accept:
                editor.putString("accept", "1");
                editor.commit();
                finish();
                break;
            case R.id.btn_decline:
                editor.putString("accept", "0");
                editor.commit();
                finish();
                break;
        }
    }
}
