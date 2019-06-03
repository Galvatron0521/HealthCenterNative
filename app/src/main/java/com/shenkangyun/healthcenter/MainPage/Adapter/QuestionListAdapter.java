package com.shenkangyun.healthcenter.MainPage.Adapter;

import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.AddEPDSBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/11/16.
 */

public class QuestionListAdapter extends BaseQuickAdapter<AddEPDSBean.DataBean.FieldListBean.ChildListBeanX, BaseViewHolder> {


    public QuestionListAdapter() {
        super(R.layout.item_question_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddEPDSBean.DataBean.FieldListBean.ChildListBeanX item) {

        RadioGroup rg = helper.getView(R.id.option);
        if (!item.getChildList().get(0).isCheck() && !item.getChildList().get(1).isCheck() && !item.getChildList().get(2).isCheck() && !item.getChildList().get(3).isCheck()) {
            rg.clearCheck();
        } else {
            if (item.getChildList().get(0).isCheck()) {
                helper.setChecked(R.id.option_A, true);
            }

            if (item.getChildList().get(1).isCheck()) {
                helper.setChecked(R.id.option_B, true);
            }

            if (item.getChildList().get(2).isCheck()) {
                helper.setChecked(R.id.option_C, true);
            }

            if (item.getChildList().get(3).isCheck()) {
                helper.setChecked(R.id.option_D, true);
            }
        }
        helper.setText(R.id.question_title, item.getDisplayName());
        helper.setText(R.id.option_A, item.getChildList().get(0).getDisplayName());
        helper.setText(R.id.option_B, item.getChildList().get(1).getDisplayName());
        helper.setText(R.id.option_C, item.getChildList().get(2).getDisplayName());
        helper.setText(R.id.option_D, item.getChildList().get(3).getDisplayName());

        helper.addOnClickListener(R.id.option_A);
        helper.addOnClickListener(R.id.option_B);
        helper.addOnClickListener(R.id.option_C);
        helper.addOnClickListener(R.id.option_D);
    }

}