package com.shenkangyun.healthcenter.MainPage.Adapter;

import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.EditEPDSBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/11/22.
 */

public class EditQuestionAdapter extends BaseQuickAdapter<EditEPDSBean.DataBean.FieldListBean.ChildListBeanX, BaseViewHolder> {


    public EditQuestionAdapter() {
        super(R.layout.item_question_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditEPDSBean.DataBean.FieldListBean.ChildListBeanX item) {

        RadioGroup rg = helper.getView(R.id.option);

        if (!item.getChildList().get(0).isChecked() && !item.getChildList().get(1).isChecked() && !item.getChildList().get(2).isChecked() && !item.getChildList().get(3).isChecked()) {
            rg.clearCheck();
        } else {
            if (item.getChildList().get(0).isChecked()) {
                helper.setChecked(R.id.option_A, true);
            }

            if (item.getChildList().get(1).isChecked()) {
                helper.setChecked(R.id.option_B, true);
            }

            if (item.getChildList().get(2).isChecked()) {
                helper.setChecked(R.id.option_C, true);
            }

            if (item.getChildList().get(3).isChecked()) {
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
