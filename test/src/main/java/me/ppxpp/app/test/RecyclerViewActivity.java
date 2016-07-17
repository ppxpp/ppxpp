package me.ppxpp.app.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.ppxpp.app.test.layout.FixedGridLayoutManager;
import me.ppxpp.app.test.layout.MyLayoutManager;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    List<String> mDataSet;
    MyAdapter mAdapter;

    private Button mRemoveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        mRemoveBtn = (Button) findViewById(R.id.remove_btn);
        mRemoveBtn.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //mRecyclerView.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        //mRecyclerView.setLayoutManager(linearLayoutManager);

        MyLayoutManager myLayoutManager = new MyLayoutManager();
        mRecyclerView.setLayoutManager(myLayoutManager);

        FixedGridLayoutManager fixedGridLayoutManager = new FixedGridLayoutManager();
        fixedGridLayoutManager.setTotalColumnCount(13);
        //mRecyclerView.setLayoutManager(fixedGridLayoutManager);

        mDataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            mDataSet.add("string_"+i);
        }
        mAdapter = new MyAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mRemoveBtn){
            //mDataSet.remove(1);
            //mAdapter.notifyItemRemoved(1);
           // mAdapter.notifyDataSetChanged();
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private List<String> mDataSet;
        public MyAdapter(List<String> dataSet){
            mDataSet = dataSet;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
            //View view = View.inflate(parent.getContext(), R.layout.recycler_view_item_layout, null);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String content = mDataSet.get(position);
            holder.mContent.setText(content);
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            TextView mContent;
            public ViewHolder(View itemView) {
                super(itemView);
                mContent = (TextView) itemView.findViewById(R.id.content);
            }
        }
    }
}
