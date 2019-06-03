package com.shenkangyun.healthcenter.MainPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.ResultBean;
import com.shenkangyun.healthcenter.BeanFolder.EditMilkBean;
import com.shenkangyun.healthcenter.MainPage.Adapter.EditMilkAdapter;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditMilkActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.breastMilkRecycler)
    RecyclerView breastMilkRecycler;

    private String moduleCode;
    private String moduleName;
    private String fieldRecordID;

    private String id;
    private String json;
    private String displayName;

    private EditMilkAdapter editMilkAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Gson gson = new Gson();
    private List<HashMap<String, String>> filter = new ArrayList<>();
    private HashMap<String, List<HashMap<String, String>>> listHashMap = new HashMap<>();
    private List<EditMilkBean.DataBean.FieldListBean.ChildListBeanX> allChildList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_milk);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("母乳喂养情况调查表");
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

        editMilkAdapter = new EditMilkAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        breastMilkRecycler.setLayoutManager(linearLayoutManager);
        breastMilkRecycler.setAdapter(editMilkAdapter);
        GetInfoFromNet();
    }

    private void GetInfoFromNet() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "editPatientsfieldNew")
                .addParams("moduleCode", moduleCode)
                .addParams("moduleName", moduleName)
                .addParams("patientID", String.valueOf(Base.getUserID()))
                .addParams("appType", "1")
                .addParams("rootUrl", "")
                .addParams("diseasesid", "")
                .addParams("fieldRecordID", fieldRecordID)
                .addParams("data", new editPatientsfieldNew("1", Base.appKey, Base.timeSpan).toJson())
                .build()
                .execute(new GsonCallBack<EditMilkBean>() {
                    @Override
                    public void onSuccess(EditMilkBean response) throws JSONException {
                        for (int i = 0; i < response.getData().getFieldList().size(); i++) {//这是最外层的集合
                            for (int j = 0; j < response.getData().getFieldList().get(i).getChildList().size(); j++) {//这是第二层的ChildList
                                EditMilkBean.DataBean.FieldListBean.ChildListBeanX childListBeanX = null;
                                for (int k = 0; k < response.getData().getFieldList().get(i).getChildList().get(j).getChildList().size(); k++) {
                                    if (response.getData().getFieldList().get(i).getChildList().get(j).getChildList().size() == 2) {
                                        childListBeanX = new EditMilkBean.DataBean.FieldListBean.ChildListBeanX(1);
                                    } else if (response.getData().getFieldList().get(i).getChildList().get(j).getChildList().size() == 3) {
                                        childListBeanX = new EditMilkBean.DataBean.FieldListBean.ChildListBeanX(2);
                                    } else {
                                        childListBeanX = new EditMilkBean.DataBean.FieldListBean.ChildListBeanX(3);
                                    }

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
                        editMilkAdapter.setNewData(allChildList);
                        initClick();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void initClick() {
        editMilkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemType = ((EditMilkAdapter) adapter).getItem(position).getItemType();
                switch (itemType) {
                    case 1:
                        switch (view.getId()) {
                            case R.id.option_A:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                break;
                            case R.id.option_B:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(true);
                                break;
                        }
                        break;
                    case 2:
                        switch (view.getId()) {
                            case R.id.option_A:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                                break;
                            case R.id.option_B:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                                break;
                            case R.id.option_C:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(true);
                                break;
                        }
                        break;
                    case 3:
                        switch (view.getId()) {
                            case R.id.option_A:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);
                                break;
                            case R.id.option_B:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);
                                break;
                            case R.id.option_C:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(true);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(3).setChecked(false);

                                break;
                            case R.id.option_D:
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(0).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(1).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(2).setChecked(false);
                                ((EditMilkAdapter) adapter).getItem(position).getChildList().get(3).setChecked(true);
                                break;
                        }
                        break;
                }
                adapter.notifyDataSetChanged();
            }

        });
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        boolean Empty = false;
        for (int i = 0; i < breastMilkRecycler.getLayoutManager().getItemCount(); i++) {
            displayName = "";
            HashMap filterObj = new HashMap();
            for (int j = 0; j < editMilkAdapter.getItem(i).getChildList().size(); j++) {
                if (editMilkAdapter.getItem(i).getChildList().get(j).isChecked()) {
                    id = editMilkAdapter.getItem(i).getChildList().get(j).getId();
                    displayName = editMilkAdapter.getItem(i).getChildList().get(j).getDisplayName();
                    break;
                }
            }
            filterObj.put("fieldID", id);
            if (TextUtils.isEmpty(displayName)) {
                Empty = true;
                break;
            } else {
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
                .addParams("act", "editFieldContents")
                .addParams("moduleCode", moduleCode)
                .addParams("appType", "1")
                .addParams("patientID", String.valueOf(Base.getUserID()))
                .addParams("filter", json)
                .addParams("fieldRecordID", fieldRecordID)
                .addParams("scores", "")
                .addParams("data", new editFieldContents("1", Base.appKey, Base.timeSpan).toJson())
                .build()
                .execute(new GsonCallBack<ResultBean>() {
                    @Override
                    public void onSuccess(ResultBean response) throws JSONException {
                        boolean message = response.isMessage();
                        if (message) {
                            Toast.makeText(EditMilkActivity.this, response.getData(), Toast.LENGTH_SHORT).show();
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


    static class editPatientsfieldNew {
        private String mobileType;
        private String appKey;
        private String timeSpan;

        public editPatientsfieldNew(String mobileType, String appKey, String timeSpan) {
            this.mobileType = mobileType;
            this.appKey = appKey;
            this.timeSpan = timeSpan;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class editFieldContents {
        private String mobileType;
        private String appKey;
        private String timeSpan;

        public editFieldContents(String mobileType, String appKey, String timeSpan) {
            this.mobileType = mobileType;
            this.appKey = appKey;
            this.timeSpan = timeSpan;

        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
