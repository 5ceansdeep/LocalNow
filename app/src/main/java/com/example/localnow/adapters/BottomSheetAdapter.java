package com.example.localnow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localnow.R;

import java.util.List;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {

    private final List<PageData> pageDataList;

    public BottomSheetAdapter(List<PageData> pageDataList) {
        this.pageDataList = pageDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PageData data = pageDataList.get(position);
        holder.pageTitle.setText(data.pageTitle);
        
        if (data.pageSubtitle != null && !data.pageSubtitle.isEmpty()) {
            holder.pageSubtitle.setText(data.pageSubtitle);
            holder.pageSubtitle.setVisibility(View.VISIBLE);
        } else {
            holder.pageSubtitle.setVisibility(View.GONE);
        }

        holder.itemTitle.setText(data.itemTitle);
        holder.itemDescription.setText(data.itemDescription);
        holder.itemIcon.setImageResource(data.iconResId);
        
        // Handle click if needed (e.g., open detail)
        holder.itemCard.setOnClickListener(v -> {
            if (data.onClickListener != null) {
                data.onClickListener.onClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pageTitle, pageSubtitle, itemTitle, itemDescription;
        ImageView itemIcon;
        View itemCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pageTitle = itemView.findViewById(R.id.pageTitle);
            pageSubtitle = itemView.findViewById(R.id.pageSubtitle);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemIcon = itemView.findViewById(R.id.itemIcon);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }

    public static class PageData {
        String pageTitle;
        String pageSubtitle;
        String itemTitle;
        String itemDescription;
        int iconResId;
        View.OnClickListener onClickListener;

        public PageData(String pageTitle, String pageSubtitle, String itemTitle, String itemDescription, int iconResId, View.OnClickListener onClickListener) {
            this.pageTitle = pageTitle;
            this.pageSubtitle = pageSubtitle;
            this.itemTitle = itemTitle;
            this.itemDescription = itemDescription;
            this.iconResId = iconResId;
            this.onClickListener = onClickListener;
        }
    }
}
