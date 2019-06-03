package com.shenkangyun.healthcenter.MainPage.Adapter;

import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.AddMilkBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/11/23.
 */

public class BreastMilkAdapter extends BaseMultiItemQuickAdapter<AddMilkBean.DataBean.FieldListBean.ChildListBeanX, BaseViewHolder> {


    public BreastMilkAdapter() {
        super(null);
        addItemType(AddMilkBean.DataBean.FieldListBean.ChildListBeanX.TWO, R.layout.item_milk_two);
        addItemType(AddMilkBean.DataBean.FieldListBean.ChildListBeanX.THREE, R.layout.item_milk_three);
        addItemType(AddMilkBean.DataBean.FieldListBean.ChildListBeanX.FOUR, R.layout.item_milk_four);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddMilkBean.DataBean.FieldListBean.ChildListBeanX item) {
        RadioGroup rg = helper.getView(R.id.option);
        switch (helper.getItemViewType()) {
            case 1:
                if (!item.getChildList().get(0).isChecked() && !item.getChildList().get(1).isChecked()) {
                    rg.clearCheck();
                } else {
                    if (item.getChildList().get(0).isChecked()) {
                        helper.setChecked(R.id.option_A, true);
                    }

                    if (item.getChildList().get(1).isChecked()) {
                        helper.setChecked(R.id.option_B, true);
                    }
                }
                helper.setText(R.id.question_title, item.getDisplayName());
                helper.setText(R.id.option_A, item.getChildList().get(0).getDisplayName());
                helper.setText(R.id.option_B, item.getChildList().get(1).getDisplayName());

                helper.addOnClickListener(R.id.option_A);
                helper.addOnClickListener(R.id.option_B);
                break;
            case 2:
                if (!item.getChildList().get(0).isChecked() && !item.getChildList().get(1).isChecked() && !item.getChildList().get(2).isChecked()) {
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
                }
                helper.setText(R.id.question_title, item.getDisplayName());
                helper.setText(R.id.option_A, item.getChildList().get(0).getDisplayName());
                helper.setText(R.id.option_B, item.getChildList().get(1).getDisplayName());
                helper.setText(R.id.option_C, item.getChildList().get(2).getDisplayName());

                helper.addOnClickListener(R.id.option_A);
                helper.addOnClickListener(R.id.option_B);
                helper.addOnClickListener(R.id.option_C);
                break;
            case 3:
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
                break;
        }
    }
}

