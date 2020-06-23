package com.wzt.log.printer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wzt.log.HiLogConfig;
import com.wzt.log.HiLogType;
import com.wzt.log.R;
import com.wzt.log.model.HiLogMo;
import com.wzt.log.utils.HiLogViewProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * log可视化打印
 */
public class HiViewPrinter implements HiLogPrinter {
    private final LogAdapter adapter;
    private HiLogViewProvider viewProvider;
    private final RecyclerView recyclerView;

    public HiViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(activity));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new HiLogViewProvider(rootView, recyclerView);
    }

    /**
     * 获取ViewProvider，通过其可控制log视图的展示与隐藏
     * @return ViewProvider
     */
    public HiLogViewProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull HiLogConfig config, int type, String tag, String printString) {
        HiLogMo hiLogMo = new HiLogMo(System.currentTimeMillis(), type, tag, printString);
        adapter.addItem(hiLogMo);
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<HiLogViewHolder> {

        private LayoutInflater inflater;
        private List<HiLogMo> logs = new ArrayList<>();

        LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(HiLogMo hiLogMo) {
            logs.add(hiLogMo);
            notifyItemInserted(logs.size() - 1);
        }

        /**
         * 根据log级别显示不同颜色
         * @param logLevel log级别
         * @return 高亮颜色
         */
        private int getHighLightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case HiLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case HiLogType.D:
                    highlight = 0xffffffff;
                    break;
                case HiLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case HiLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case HiLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

        @NonNull
        @Override
        public HiLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.view_log_item, parent, false);
            return new HiLogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HiLogViewHolder holder, int position) {
            HiLogMo hiLogMo = logs.get(position);
            int color = getHighLightColor(hiLogMo.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(hiLogMo.getFlattened());
            holder.messageView.setText(hiLogMo.log);
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private static class HiLogViewHolder extends RecyclerView.ViewHolder {
        private TextView tagView;
        private TextView messageView;

        HiLogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tv_tag);
            messageView = itemView.findViewById(R.id.tv_message);
        }
    }
}
