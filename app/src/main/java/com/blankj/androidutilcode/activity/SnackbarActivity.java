package com.blankj.androidutilcode.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.androidutilcode.R;
import com.blankj.androidutilcode.base.BaseActivity;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/17
 *     desc  : Snackbar工具类Demo
 * </pre>
 */
public class SnackbarActivity extends BaseActivity {

    private View snackBarRootView;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_snackbar;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        snackBarRootView = findViewById(android.R.id.content);
        findViewById(R.id.btn_short_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_short_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_add_view).setOnClickListener(this);
        findViewById(R.id.btn_add_view_with_action).setOnClickListener(this);
        findViewById(R.id.btn_cancel_snackbar).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_short_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_short))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_short_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_short))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setAction(getActionText(), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_long_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_long))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_LONG)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_long_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_long))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setDuration(SnackbarUtils.LENGTH_LONG)
                        .setAction(getActionText(), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_indefinite_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_indefinite))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_indefinite_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_indefinite))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setAction(getActionText(), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_add_view:
                SnackbarUtils.with(snackBarRootView)
                        .setBgColor(Color.TRANSPARENT)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .show();
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                SnackbarUtils.addView(R.layout.snackbar_custom, -1, params);
                break;

            case R.id.btn_add_view_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setBgColor(Color.TRANSPARENT)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .show();
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                SnackbarUtils.addView(R.layout.snackbar_custom, -1, params);
                View snackbarView = SnackbarUtils.getView();
                if (snackbarView != null) {
                    TextView tvSnackbarCustom = (TextView) snackbarView.findViewById(R.id.tv_snackbar_custom);
                    tvSnackbarCustom.setText("点我可消失");
                    tvSnackbarCustom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SnackbarUtils.dismiss();
                        }
                    });
                }
                break;

            case R.id.btn_cancel_snackbar:
                SnackbarUtils.dismiss();
                break;
        }
    }

    private SpannableStringBuilder getMsg(@StringRes int resId) {
        return new SpanUtils()
                .append(getString(resId))
                .setFontSize(24, true)
                .setIconMargin(R.mipmap.ic_launcher, 32, SpanUtils.ALIGN_CENTER)
                .create();
    }

    private SpannableStringBuilder getActionText() {
        return new SpanUtils()
                .append("Click")
                .setFontSize(100, true)
                .setBold()
                .create();
    }
}
