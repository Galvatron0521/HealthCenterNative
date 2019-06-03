package com.shenkangyun.healthcenter.MainPage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.UpDateBean;
import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.ed_age)
    EditText edAge;
    @BindView(R.id.tv_profession)
    TextView tvProfession;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.ed_height)
    EditText edHeight;
    @BindView(R.id.ed_weight)
    EditText edWeight;
    @BindView(R.id.ed_week)
    EditText edWeek;
    @BindView(R.id.ed_day)
    EditText edDay;
    @BindView(R.id.ed_husbandAge)
    EditText edHusbandAge;
    @BindView(R.id.tv_husbandProfession)
    TextView tvHusbandProfession;
    @BindView(R.id.tv_complication)
    TextView tvComplication;
    @BindView(R.id.ed_complication)
    EditText edComplication;
    @BindView(R.id.complication_plus)
    LinearLayout complicationPlus;

    private String age;
    private String name;
    private String husbandAge;
    private String week;
    private String day;
    private String birth;
    private String complication;
    private String degree;
    private String husbandProfession;
    private String profession;
    private ListPopupWindow mListVocations;

    private ListPopupWindow mListEducations;
    private ListPopupWindow mListComplications;
    private List<String> vocations = new ArrayList<>();
    private List<String> educations = new ArrayList<>();
    private List<String> complications = new ArrayList<>();
    private boolean isNumeric = false;

    private String birthday;
    private String childWeeks;
    private int HusbandProfession;
    private int Weeks;
    private int Degree;
    private String Complication;
    private int Profession;
    private String heightTv;
    private String weightTv;
    private String edComplicationTxt;

    private int ageDa;
    private String idCardDa;
    private String mobileDa;
    private String nameDa;
    private String loginNameDa;
    private String brithdayDa;
    private String heightDa;
    private String weightDa;
    private int degreeDa;
    private int professionDa;
    private int childWeeksDa;
    private int husbandAgeDa;
    private String complicationDa;
    private int husbandProfessionDa;

    private String idCardInt;
    private String mobileInt;
    private String loginNameInt;
    private String provinceIDDa;
    private String cityIDDa;
    private String regionIDDa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("信息修改");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initPopupWindow();
    }

    private void initView() {

        Intent intent = getIntent();
        idCardInt = intent.getStringExtra("idCard");
        mobileInt = intent.getStringExtra("mobile");
        loginNameInt = intent.getStringExtra("loginName");

        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            ageDa = all.get(i).getAge();
            idCardDa = all.get(i).getIdCard();
            mobileDa = all.get(i).getMobile();
            nameDa = all.get(i).getName();
            loginNameDa = all.get(i).getLoginName();
            brithdayDa = all.get(i).getBrithday();
            heightDa = all.get(i).getHeight();
            weightDa = all.get(i).getWeight();
            degreeDa = all.get(i).getDegree();
            provinceIDDa = all.get(i).getProvinceID();
            cityIDDa = all.get(i).getCityID();
            regionIDDa = all.get(i).getRegionID();
            professionDa = all.get(i).getProfession();
            childWeeksDa = all.get(i).getChildWeeks();
            husbandAgeDa = all.get(i).getHusbandAge();
            complicationDa = all.get(i).getComplication();
            husbandProfessionDa = all.get(i).getHusbandProfession();
        }
        vocations.add("政府机关及事业单位");
        vocations.add("个体");
        vocations.add("职员");
        vocations.add("工人");
        vocations.add("农民");
        vocations.add("自由职业");
        vocations.add("其他");

        educations.add("初中及以下");
        educations.add("高中");
        educations.add("中专");
        educations.add("大专");
        educations.add("本科及以上");

        complications.add("妊娠期糖尿病");
        complications.add("妊娠期高血压");
        complications.add("羊水过多");
        complications.add("羊水过少");
        complications.add("子痫前期重度");
        complications.add("其他");
        complications.add("无");
        initData();
    }

    private void initData() {
        if (!TextUtils.isEmpty(String.valueOf(ageDa))) {
            edAge.setText(String.valueOf(ageDa));
        }
        if (!TextUtils.isEmpty(String.valueOf(degreeDa))) {
            tvDegree.setText(educations.get(degreeDa));
            Degree = degreeDa;
        }
        if (!TextUtils.isEmpty(String.valueOf(professionDa))) {
            tvProfession.setText(vocations.get(professionDa));
            Profession = professionDa;
        }
        if (!TextUtils.isEmpty(String.valueOf(husbandProfessionDa))) {
            tvHusbandProfession.setText(vocations.get(husbandProfessionDa));
            HusbandProfession = husbandProfessionDa;
        }
        if (!TextUtils.isEmpty(String.valueOf(husbandAgeDa))) {
            edHusbandAge.setText(String.valueOf(husbandAgeDa));
        }
        if (!TextUtils.isEmpty(complicationDa)) {
            for (int i = 0; i < complicationDa.length(); i++) {
                if (Character.isDigit(complicationDa.charAt(i))) {
                    isNumeric = true;
                }
            }
            if (isNumeric) {
                for (int i = 0; i < complications.size(); i++) {
                    if (Integer.valueOf(childWeeksDa) == i) {
                        tvComplication.setText(complications.get(i).toString());
                        break;
                    } else {
                        complicationPlus.setVisibility(View.VISIBLE);
                        tvComplication.setText("其他");
                        edComplication.setText(complicationDa);
                    }
                }
            } else {
                for (int i = 0; i < complications.size(); i++) {
                    if (complicationDa.equals(complications.get(i))) {
                        tvComplication.setText(complicationDa);
                        complicationPlus.setVisibility(View.GONE);
                        break;
                    } else {
                        complicationPlus.setVisibility(View.VISIBLE);
                        tvComplication.setText("其他");
                        edComplication.setText(complicationDa);
                    }
                }

            }
        }
        if (!TextUtils.isEmpty(String.valueOf(childWeeksDa))) {
            int weekNum = childWeeksDa / 7;
            int dayNum = childWeeksDa % 7;
            edWeek.setText(String.valueOf(weekNum));
            edDay.setText(String.valueOf(dayNum));
        }

        if (!TextUtils.isEmpty(brithdayDa)) {
            tvBirth.setText(brithdayDa);
        }
        if (!TextUtils.isEmpty(nameDa)) {
            edName.setText(nameDa);
        }
        if (!TextUtils.isEmpty(heightDa)) {
            edHeight.setText(heightDa);
        }
        if (!TextUtils.isEmpty(weightDa)) {
            edWeight.setText(weightDa);
        }

    }

    private void initPopupWindow() {
        mListEducations = new ListPopupWindow(this);
        mListEducations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, educations));
        mListEducations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListEducations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListEducations.setAnchorView(tvDegree);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListEducations.setModal(true);//设置是否是模式
        mListEducations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvDegree.setText(educations.get(position));
                Degree = position;
                mListEducations.dismiss();
            }
        });

        mListComplications = new ListPopupWindow(this);
        mListComplications.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, complications));
        mListComplications.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListComplications.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListComplications.setAnchorView(tvComplication);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListComplications.setModal(true);//设置是否是模式
        mListComplications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvComplication.setText(complications.get(position));
                if (complications.get(position).equals("其他")) {
                    complicationPlus.setVisibility(View.VISIBLE);
                } else {
                    complicationPlus.setVisibility(View.GONE);
                }
                Complication = complications.get(position);
                mListComplications.dismiss();
            }
        });

        edDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String dayNum = edDay.getText().toString();
                if (!TextUtils.isEmpty(dayNum) && Integer.valueOf(dayNum) >= 7) {
                    Toast.makeText(ChangeInfoActivity.this, "请正确填入天数格式", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @OnClick({R.id.tv_save, R.id.tv_birth, R.id.tv_profession, R.id.tv_degree, R.id.tv_husbandProfession, R.id.tv_complication})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                age = edAge.getText().toString();
                name = edName.getText().toString();
                husbandAge = edHusbandAge.getText().toString();
                week = edWeek.getText().toString();
                day = edDay.getText().toString();
                birth = tvBirth.getText().toString();
                Complication = tvComplication.getText().toString();
                if (complicationPlus.getVisibility() != View.GONE) {
                    edComplicationTxt = edComplication.getText().toString();
                }
                degree = tvDegree.getText().toString();
                husbandProfession = tvHusbandProfession.getText().toString();
                profession = tvProfession.getText().toString();
                heightTv = edHeight.getText().toString();
                weightTv = edWeight.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(birth) || "请填写".equals(birth)) {
                    Toast.makeText(this, "生日不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(this, "年龄不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(profession) || "请选择".equals(profession)) {
                    Toast.makeText(this, "请选择职业类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(degree) || "请选择".equals(degree)) {
                    Toast.makeText(this, "请选择受教育程度！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(heightTv)) {
                    Toast.makeText(this, "身高不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(weightTv)) {
                    Toast.makeText(this, "体重不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(week)) {
                    Toast.makeText(this, "请填写具体周数！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(day)) {
                    Toast.makeText(this, "请填写具体天数！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(husbandAge)) {
                    Toast.makeText(this, "配偶年龄不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(husbandProfession) || "请选择".equals(husbandProfession)) {
                    Toast.makeText(this, "请选择配偶职业类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Complication) || "请选择".equals(Complication)) {
                    Toast.makeText(this, "请选择并发症类型", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edComplicationTxt) && (complicationPlus.getVisibility() != View.GONE)) {
                    Toast.makeText(this, "并发症内容不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                initSubmit();
                break;
            case R.id.tv_birth:
                DatePickDialog datePickDialog = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        birthday = simpleDateFormat.format(date);
                        tvBirth.setText(birthday);
                    }
                });
                datePickDialog.show();
                break;
            case R.id.tv_profession:
                mListVocations = new ListPopupWindow(this);
                mListVocations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, vocations));
                mListVocations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setAnchorView(tvProfession);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListVocations.setModal(true);//设置是否是模式
                mListVocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        tvProfession.setText(vocations.get(position));
                        Profession = position;
                        mListVocations.dismiss();
                    }
                });
                mListVocations.show();
                break;
            case R.id.tv_degree:
                mListEducations.show();
                break;
            case R.id.tv_husbandProfession:
                mListVocations = new ListPopupWindow(this);
                mListVocations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, vocations));
                mListVocations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setAnchorView(tvHusbandProfession);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListVocations.setModal(true);//设置是否是模式
                mListVocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        tvHusbandProfession.setText(vocations.get(position));
                        HusbandProfession = position;
                        mListVocations.dismiss();
                    }
                });
                mListVocations.show();
                break;
            case R.id.tv_complication:
                mListComplications.show();
                break;
        }
    }

    private void initSubmit() {
        Weeks = Integer.parseInt(week) * 7 + Integer.parseInt(day);
        childWeeks = String.valueOf(Weeks);
        degree = String.valueOf(Degree);
        if (Complication.equals("其他") && !TextUtils.isEmpty(edComplicationTxt)) {
            complication = edComplicationTxt;
        } else {
            complication = Complication;
        }
        husbandProfession = String.valueOf(HusbandProfession);
        profession = String.valueOf(Profession);
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "UpdatePatient")
                .addParams("data", new UpdatePatient(Base.appKey, Base.timeSpan, "1", "1", String.valueOf(Base.getUserID()),
                        idCardInt, mobileInt, loginNameInt, name, age, birth, degree, provinceIDDa, cityIDDa, regionIDDa, profession, husbandAge,
                        husbandProfession, childWeeks, complication, heightTv, weightTv).toJson())
                .build()
                .execute(new GsonCallBack<UpDateBean>() {
                    @Override
                    public void onSuccess(UpDateBean response) throws JSONException {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            initPatientData(response);
                            String data = response.getData().getData();
                            Toast.makeText(ChangeInfoActivity.this, data, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {

                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void initPatientData(UpDateBean response) {
        LitePal.deleteAll(User.class);
        int id = response.getPatient().get(0).getId();
        int ageR = response.getPatient().get(0).getAge();
        String nameR = response.getPatient().get(0).getName();
        String idCard = response.getPatient().get(0).getIdCard();
        String mobile = response.getPatient().get(0).getMobile();
        String brithdayR = response.getPatient().get(0).getBrithday();
        int degreeR = response.getPatient().get(0).getDegree();
        String loginName = response.getPatient().get(0).getLoginName();
        String height = response.getPatient().get(0).getHeight();
        String weight = response.getPatient().get(0).getWeight();
        int childWeeksR = response.getPatient().get(0).getChildWeeks();
        int husbandAgeR = response.getPatient().get(0).getHusbandAge();
        int professionR = response.getPatient().get(0).getProfession();
        String complicationR = response.getPatient().get(0).getComplication();
        int husbandProfessionR = response.getPatient().get(0).getHusbandProfession();

        User user = new User();
        user.setUserID(id);
        user.setName(nameR);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setDegree(degreeR);
        user.setLoginName(loginName);
        user.setAge(ageR);
        user.setHeight(height);
        user.setWeight(weight);
        user.setBrithday(brithdayR);
        user.setChildWeeks(childWeeksR);
        user.setHusbandAge(husbandAgeR);
        user.setHusbandProfession(husbandProfessionR);
        user.setProfession(professionR);
        user.setComplication(complicationR);
        user.save();
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

    static class UpdatePatient {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String appType;
        private String id;
        private String idCard;
        private String mobile;
        private String loginName;
        private String name;
        private String age;
        private String brithday;
        private String degree;
        private String provinceID;
        private String cityID;
        private String regionID;
        private String profession;
        private String husbandAge;
        private String husbandProfession;
        private String childWeeks;
        private String complication;
        private String height;
        private String weight;


        public UpdatePatient(String appKey, String timeSpan, String mobileType, String appType, String id,
                             String idCard, String mobile, String loginName, String name, String age,
                             String brithday, String degree, String provinceID, String cityID, String regionID,
                             String profession, String husbandAge, String husbandProfession,
                             String childWeeks, String complication, String height, String weight) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.appType = appType;
            this.id = id;
            this.idCard = idCard;
            this.mobile = mobile;
            this.loginName = loginName;
            this.name = name;
            this.age = age;
            this.brithday = brithday;
            this.degree = degree;
            this.provinceID = provinceID;
            this.cityID = cityID;
            this.regionID = regionID;
            this.profession = profession;
            this.husbandAge = husbandAge;
            this.husbandProfession = husbandProfession;
            this.childWeeks = childWeeks;
            this.complication = complication;
            this.weight = weight;
            this.height = height;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}