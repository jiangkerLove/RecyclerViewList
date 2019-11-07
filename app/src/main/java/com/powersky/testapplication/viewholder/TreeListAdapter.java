package com.powersky.testapplication.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.powersky.testapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TreeListAdapter extends RecyclerView.Adapter<TreeListAdapter.TreeViewHolder> {


    private Context mContext;
    //传入的列表数据
    private List<TreeItem> mTreeItems;
    //用于判断是否为第一次加载，方便为展示列表重新添加数据
    private boolean begin;
    //展示的列表数据
    private List<TreeItem> mItemList;

    public TreeListAdapter(Context context, List<TreeItem> treeItems) {
        mContext = context;
        mTreeItems = treeItems;
        mItemList = new ArrayList<>();
        begin = true;
    }


    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        TreeViewHolder holder = new TreeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        //在getList方法中为mItemList添加了所有需要展示出来的数据，所以根据顺序依次展示即可
        TreeItem treeItem = mItemList.get(position);
        //设置缩进
        holder.itemView.setPadding(30 * treeItem.getLevel(), 0, 0, 0);

        //如果有子类
        if (treeItem.getSons() != null) {
            //如果子项展开了
            if (treeItem.isOpen()) {
                holder.mImageView.setBackgroundResource(R.drawable.tree_open);
            } else {
                holder.mImageView.setBackgroundResource(R.drawable.tree_close);
            }
            //给有子项的列表图片添加点击事件，更改是否展开
            holder.mImageView.setOnClickListener((v) -> {
                treeItem.setOpen(!treeItem.isOpen());
                //刷新列表，重新添加数据
                begin = true;
                notifyDataSetChanged();
            });
        } else {
            //根节点设置标志
            holder.mImageView.setBackgroundResource(R.drawable.ic_launcher_background);
        }
        //添加文本
        holder.mTextView.setText(treeItem.getString());
    }

    //因为在每次刷新列表首先调用的是这个方法，所以在这个方法中填充mItemList列表
    @Override
    public int getItemCount() {
        if (begin) {
            mItemList.clear();
            getList(mTreeItems);
            //每次刷新只添加一次
            begin = false;
        }
        return mItemList.size();
    }

    //传入TreeItem数列
    private void getList(List<TreeItem> treeItems) {
        //依次取出数列中的每一项
        for (TreeItem treeItem : treeItems) {
            //首先加入到需要展示的列表中
            mItemList.add(treeItem);
            //如果此项又有子项，且是展开的
            if (treeItem.getSons() != null && treeItem.isOpen()) {
                //则把此项的子项列表传入
                getList(treeItem.getSons());
            }
        }
    }

    class TreeViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageView;
        TextView mTextView;

        public TreeViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.id_item_icon);
            mTextView = itemView.findViewById(R.id.id_item_text);
        }
    }
}
