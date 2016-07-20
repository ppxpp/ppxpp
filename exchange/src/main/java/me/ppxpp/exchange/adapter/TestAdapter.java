package me.ppxpp.exchange.adapter;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ppxpp.exchange.R;
import me.ppxpp.exchange.SimpleTouchCallback;

/**
 * Created by zhouhao on 2016/7/17.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>
        implements SimpleTouchCallback.TouchHelperAdapter{

    private String TAG = getClass().getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;

    private final int TYPE_CONTENT = 1;
    private final int TYPE_EMPTY = 2;

    List<Item> items;

    public TestAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        items = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            Item item = new Item();
            item.content = "item_" + i;
            item.type = TYPE_CONTENT;
            items.add(item);
        }
    }

    public void addItem(){
        for (int i = 0; i < 3; i++) {
            Item item = new Item();
            item.content = "item_" + items.size();
            item.type = TYPE_EMPTY;
            items.add(item);
        }
        notifyItemRangeInserted(items.size() - 3, 3);
    }

    public void removeItem(){
        for (int i = 0; i < 3; i++) {
            items.remove(items.size() - 1);
        }
        notifyItemRangeRemoved(items.size() - 3, 3);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TestViewHolder holder = null;
        if (viewType == TYPE_CONTENT) {
            View view = mInflater.inflate(R.layout.recycler_item_layout_test, parent, false);
            holder = new ContentViewHolder(view);
        }else if (viewType == TYPE_EMPTY){
            View view = mInflater.inflate(R.layout.recycler_item_layout_empty, parent, false);
            holder = new EmptyViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CONTENT) {
            holder.setContent(items.get(position).content);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    private static class Item{
        public String content;
        public int type;
    }


    public static class TestViewHolder extends RecyclerView.ViewHolder
        implements SimpleTouchCallback.TouchHelperViewHolder{

        View rootView;

        public TestViewHolder(View itemView) {
            super(itemView);
        }

        public void setContent(String content){

        }

        @Override
        public void onItemSelected() {
            rootView.setScaleX(1.2f);
            rootView.setScaleY(1.2f);
        }

        @Override
        public void onItemClear() {
            rootView.setScaleX(1.0f);
            rootView.setScaleY(1.0f);
        }
    }

    public static class ContentViewHolder extends TestViewHolder
            implements SimpleTouchCallback.TouchHelperViewHolder{

        @BindView(R.id.title)
        TextView title;

        View rootView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setContent(String content){
            title.setText(content);
        }


        @Override
        public void onItemSelected() {
            rootView.setScaleX(1.2f);
            rootView.setScaleY(1.2f);
        }

        @Override
        public void onItemClear() {
            rootView.setScaleX(1.0f);
            rootView.setScaleY(1.0f);
        }
    }

    public static class EmptyViewHolder extends TestViewHolder
            implements SimpleTouchCallback.TouchHelperViewHolder{

        View rootView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            //ButterKnife.bind(this, itemView);
        }


        @Override
        public void onItemSelected() {
            rootView.setScaleX(1.2f);
            rootView.setScaleY(1.2f);
        }

        @Override
        public void onItemClear() {
            rootView.setScaleX(1.0f);
            rootView.setScaleY(1.0f);
        }
    }
}
