package com.lly.rangelistdemo;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lly.library.RangeView;

/**
 * ListAdapter[v 1.0.0]
 * classes:com.lly.rangelistdemo.ListAdapter
 *
 * @author lileiyi
 * @date 2018/8/22
 * @time 11:52
 * @description
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ListAdapter listAdapter;

    private RecyclerView recyclerView;

    public ListAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false));
        } else {
            return new RangeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.range_item_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case 1:
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.tv_name.setText("我是" + position + "item");
                break;
            case 2:
                RangeViewHolder viewHolder = (RangeViewHolder) holder;
                viewHolder.rangeView.setupRecycleView(recyclerView);
                viewHolder.rangeView.setTag(position);
                switch (position) {
                    case 4:
                        viewHolder.rangeView.setDefBitmap(BitmapFactory.decodeResource(viewHolder.rangeView.getResources(), R.mipmap.bbb));
                        break;
                    case 6:
                        viewHolder.rangeView.setDefBitmap(BitmapFactory.decodeResource(viewHolder.rangeView.getResources(), R.mipmap.aaa));
                        break;
                    case 15:
                        viewHolder.rangeView.setDefBitmap(BitmapFactory.decodeResource(viewHolder.rangeView.getResources(), R.mipmap.temp));
                        break;
                }
                break;
        }
    }


    @Override
    public int getItemCount() {
        return 30;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 4 || position == 6 || position == 15) {
            return 2;
        } else {
            return 1;
        }
//        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_text1);
        }
    }

    class RangeViewHolder extends RecyclerView.ViewHolder {

        RangeView rangeView;

        public RangeViewHolder(View itemView) {
            super(itemView);
            rangeView = itemView.findViewById(R.id.rv_view);
        }
    }
}
