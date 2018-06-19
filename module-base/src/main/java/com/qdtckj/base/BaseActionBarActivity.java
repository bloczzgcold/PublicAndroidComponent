package com.qdtckj.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhangzhenguo on 2017/8/10.
 * 利农ActionBar BaseActivity
 */

public abstract class BaseActionBarActivity extends AppCompatActivity {
    private View mParent;
    private FrameLayout mContainer;

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivTool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = getLayoutInflater().inflate(R.layout.pos_activity_actionbar, null);
        mContainer = (FrameLayout) mParent.findViewById(R.id.actionbar_container);
        ivBack = (ImageView) mParent.findViewById(R.id.iv_pos_actionbar_back);
        tvTitle = (TextView) mParent.findViewById(R.id.tv_pos_actionbar_title);
        ivTool = (ImageView) mParent.findViewById(R.id.iv_pos_actionbar_tool);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        ivTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTool();
            }
        });
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View content = LayoutInflater.from(this).inflate(layoutResID, null);
        mContainer.addView(content);
        super.setContentView(mParent);
    }

    /**
     * 返回按钮监听
     */
    public void onBack(){
        onBackPressed();
    }

    /**
     * 右侧按钮监听
     */
    public void onTool(){

    }

    /**
     * 设置标题
     * @param title 字符串
     */
    public void setActionbarTitle(CharSequence title){
        tvTitle.setText(title == null ? "" : title);
    }

    /**
     * 设备标题
     * @param resId string 资源id
     */
    public void setActionbarTitle(@StringRes int resId){
        tvTitle.setText(resId);
    }

    /**
     * 设置右侧按钮显示状态
     * @param visibility
     */
    public void setToolVisibility(int visibility){
        ivTool.setVisibility(visibility);
    }

    /**
     * 设备右侧按钮图标资源
     * @param resId
     */
    public void setToolSrc(@DrawableRes int resId){
        ivTool.setImageResource(resId);
    }

    public ImageView getToolImageView(){
        return ivTool;
    }

    /**
     * 设置左侧图标visibility
     * @param visibility View.VISIBLE View.INVISIBLE View.GONE
     */
    public void setLeftVisibility(int visibility){
        ivBack.setVisibility(visibility);

    }

}
