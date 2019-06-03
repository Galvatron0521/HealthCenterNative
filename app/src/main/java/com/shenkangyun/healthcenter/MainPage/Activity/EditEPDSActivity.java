package com.shenkangyun.healthcenter.MainPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.ResultBean;
import com.shenkangyun.healthcenter.BeanFolder.EditEPDSBean;
import com.shenkangyun.healthcenter.MainPage.Adapter.EditQuestionAdapter;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EditEPDSActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.questionRecycler)
    RecyclerView questionRecycler;

    private String id;
    private int scores;
    private int scoresSum;
    private String json;
    private String displayName;

    private String moduleCode;
    private String moduleName;
    private String fieldRecordID;

    private EditQuestionAdapter editQuestionAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Gson gson = new Gson();
    private List<HashMap<String, String>> filter = new ArrayList<>();
    private HashMap<String, List<HashMap<String, String>>> listHashMap = new HashMap<>();
    private List<EditEPDSBean.DataBean.FieldListBean.ChildListBeanX> allChildList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_epds);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("编辑量表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        moduleCode = intent.getStringExtra("moduleCode");
        moduleName = intent.getStringExtra("moduleName");
        fieldRecordID = intent.getStringExtra("fieldRecordID");

        editQuestionAdapter = new EditQuestionAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        questionRecycler.setLayoutManager(linearLayoutManager);
        questionRecycler.setAdapter(editQuestionAdapter);
        GetInfoFromNet();
    }

    private void GetInfoFromNet() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "editScaleNew")
                .addParams("moduleCode", moduleCode)
                .addParams("moduleName", moduleName)
                .addParams("patientID", String.valueOf(Base.getUserID()))
                .addParams("appType", "1")
                .addParams("rootUrl", "")
                .addParams("diseasesid", "")
                .addParams("fieldRecordID", fieldRecordID)
                .addParams("data", new editScaleNew("1", Base.appKey, Base.timeSpan).toJson())
                .build()
                .execute(new GsonCallBack<EditEPDSBean>() {
                    @Override
                    public void onSuccess(EditEPDSBean response) throws JSONException {
                        for (int i = 0; i < response.getData().getFieldList().size(); i++) {//这是最外层的集合
                            for (int j = 0; j < response.getData().getFieldList().get(i).getChildList().size(); j++) {//这是第二层的ChildList
                                EditEPDSBean.DataBean.FieldListBean.ChildListBeanX childListBeanX = new EditEPDSBean.DataBean.FieldListBean.ChildListBeanX();
                                childListBeanX.setId(response.getData().getFieldList().get(i).getChildList().get(j).getId());
                                for (int k = 0; k < response.getData().getFieldList().get(i).getChildList().get(j).getChildList().size(); k++) {
                                    String contents = response.getData().getFieldList().get(i).getChildList().get(j).getChildList().get(k).getContents();
                                    if (TextUtils.isEmpty(contents)) {
                                        response.getData().getFieldList().get(i).getChildList().get(j).getChildList().get(k).setChecked(false);
                                    } else {
                                        response.getData().getFieldList().get(i).getChildList().get(j).getChildList().get(k).setChecked(true);
                                    }
                                }
                                childListBeanX.setDisplayName(response.getData().getFieldList().get(i).getChildList().get(j).getDisplayName());
                                childListBeanX.setChildList(response.getData().getFieldList().get(i).getChildList().get(j).getChildList());
                                allChildList.add(childListBeanX);
                            }
                        }
                        editQuestionAdapter.setNewData(allChildList);
                        initClick();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void initClick() {
        editQuestionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.option_A:
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(0).setChecked(true);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);
                        break;
                    case R.id.option_B:
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(1).setChecked(true);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);
                        break;
                    case R.id.option_C:
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(2).setChecked(true);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);
                        break;
                    case R.id.option_D:
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(3).setChecked(true);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                        ((EditQuestionAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                        break;
                }
                adapter.notifyDataSetChanged();
            }

        });
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        scoresSum = 0;
        boolean Empty = false;
        for (int i = 0; i < questionRecycler.getLayoutManager().getItemCount(); i++) {
            displayName = "";
            HashMap filterObj = new HashMap();
            for (int j = 0; j < editQuestionAdapter.getItem(i).getChildList().size(); j++) {
                if (editQuestionAdapter.getItem(i).getChildList().get(j).isChecked()) {
                    id = editQuestionAdapter.getItem(i).getChildList().get(j).getId();
                    displayName = editQuestionAdapter.getItem(i).getChildList().get(j).getDisplayName();
                    scores = Integer.valueOf(editQuestionAdapter.getItem(i).getChildList().get(j).getScores());
                    break;
                }
            }
            filterObj.put("fieldID", id);
            if (TextUtils.isEmpty(displayName)) {
                Empty = true;
                break;
            } else {
                scoresSum = scoresSum + scores;
                filterObj.put("contents", displayName);
            }
            filter.add(filterObj);
        }
        if (Empty) {
            filter.clear();
            Toast.makeText(this, "请将问题全部回答", Toast.LENGTH_SHORT).show();
        } else {
            listHashMap.put("filterObj", filter);
            json = gson.toJson(listHashMap);
            PutInfoToNet();
        }
    }

    private void PutInfoToNet() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "editFieldContentsJson")
                .addParams("moduleCode", moduleCode)
                .addParams("appType", "1")
                .addParams("patientID", String.valueOf(Base.getUserID()))
                .addParams("filter", json)
                .addParams("fieldRecordID", fieldRecordID)
                .addParams("scores", String.valueOf(scoresSum))
                .addParams("data", new editFieldContentsJson("1", Base.appKey, Base.timeSpan).toJson())
                .build()
                .execute(new GsonCallBack<ResultBean>() {
                    @Override
                    public void onSuccess(ResultBean response) throws JSONException {
                        boolean message = response.isMessage();
                        if (message) {
                            Toast.makeText(EditEPDSActivity.this, response.getData(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            setResult(2, intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    static class editScaleNew {
        private String mobileType;
        private String appKey;
        private String timeSpan;

        public editScaleNew(String mobileType, String appKey, String timeSpan) {
            this.mobileType = mobileType;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class editFieldContentsJson {
        private String mobileType;
        private String appKey;
        private String timeSpan;

        public editFieldContentsJson(String mobileType, String appKey, String timeSpan) {
            this.mobileType = mobileType;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
