package me.ppxpp.exchange.adapter;

import android.content.Context;
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

    List<String> items;

    public TestAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        items = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            items.add("item_" + i);
        }
    }

    public void addItem(){
        items.add("item_" + items.size());
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_item_layout_test, parent, false);
        TestViewHolder holder = new TestViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {

        holder.title.setText(items.get(position));
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

    public static class TestViewHolder extends RecyclerView.ViewHolder
        implements SimpleTouchCallback.TouchHelperViewHolder{

        @BindView(R.id.title)
        TextView title;

        View rootView;

        public TestViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
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
