package me.ppxpp.exchange;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by zhouhao on 2016/7/17.
 */
public class SimpleTouchCallback extends ItemTouchHelper.Callback {

    public interface TouchHelperAdapter{

        void onMove(int fromPosition, int toPosition);
    }

    public interface TouchHelperViewHolder{
        void onItemSelected();

        void onItemClear();
    }

    private TouchHelperAdapter mAdapter;

    public SimpleTouchCallback(TouchHelperAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            if (viewHolder instanceof TouchHelperViewHolder){
                TouchHelperViewHolder vh = (TouchHelperViewHolder) viewHolder;
                vh.onItemSelected();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof TouchHelperViewHolder){
            TouchHelperViewHolder vh = (TouchHelperViewHolder) viewHolder;
            vh.onItemClear();
        }
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlag, 0);
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        mAdapter.onMove(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
