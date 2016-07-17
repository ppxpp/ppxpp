package me.ppxpp.exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ppxpp.exchange.adapter.TestAdapter;
import me.ppxpp.library.Log.LogUtils;
import me.ppxpp.uilib.widget.helper.RecyclerViewItemClickHelper;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.test_recycler_view)
    RecyclerView mRecycleView;
    LinearLayoutManager mLayoutManager;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.addItemDecoration(new TestItemDecoration(this));

        mAdapter = new TestAdapter(this);
        SimpleTouchCallback callback = new SimpleTouchCallback(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecycleView);
        mRecycleView.setAdapter(mAdapter);

        RecyclerViewItemClickHelper.addTo(mRecycleView).setOnItemClickListener(new RecyclerViewItemClickHelper.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getApplicationContext(), "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRecycleView != null){
            RecyclerViewItemClickHelper.removeFrom(mRecycleView);
        }
    }

    @OnClick(R.id.btn)
    void scrollToPosition(){
        mLayoutManager.scrollToPositionWithOffset(1, 100);
    }

}
