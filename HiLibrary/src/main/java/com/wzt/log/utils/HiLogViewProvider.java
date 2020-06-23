package com.wzt.log.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wzt.util.HiDisplayUtil;

public class HiLogViewProvider {
    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private boolean isOpen;
    private View floatingView;
    private View logView;
    private final String TAG_FLOAT_VIEW = "tag_float_view";
    private final String TAG_LOG_VIEW = "tag_log_view";

    public HiLogViewProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    public void showFloatingView() {
        if (rootView.findViewWithTag(TAG_FLOAT_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        params.bottomMargin = HiDisplayUtil.dp2px(100, rootView.getResources());
        View floatView = genFloatingView();
        floatView.setLayoutParams(params);
        rootView.addView(floatView);
    }

    public void hideFloatingView() {
        rootView.removeView(genFloatingView());
    }

    @SuppressLint("SetTextI18n")
    private View genFloatingView() {
        if (floatingView != null) {
            return floatingView;
        }
        TextView textView = new TextView(rootView.getContext());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    showLogView();
                }
            }
        });
        textView.setText("HiLog");
        textView.setTag(TAG_FLOAT_VIEW);
        textView.setBackgroundColor(Color.BLACK);
        textView.setAlpha(0.7f);
        return floatingView = textView;
    }

    /**
     * 显示logView
     */
    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(160, rootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setLayoutParams(params);
        rootView.addView(logView);
        isOpen = true;
    }

    @SuppressLint("SetTextI18n")
    public View genLogView() {
        if (logView != null) {
            return logView;
        }
        FrameLayout frameLayout = new FrameLayout(rootView.getContext());
        frameLayout.setBackgroundColor(Color.BLACK);
        frameLayout.setAlpha(0.7f);
        frameLayout.addView(recyclerView);
        frameLayout.setTag(TAG_LOG_VIEW);

        TextView tvClose = new TextView(rootView.getContext());
        tvClose.setText("close");
        tvClose.setGravity(Gravity.END);
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLogView();
            }
        });
        frameLayout.addView(tvClose);
        return logView = frameLayout;
    }

    /**
     * 关闭LogView
     */
    private void closeLogView() {
        isOpen = false;
        rootView.removeView(genLogView());
    }
}
