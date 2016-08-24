package com.whl.emoguoshi.recycler;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whl.emoguoshi.R;

/**
 * Created by wanghl on 16/8/23.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;

    public MyItemDecoration(Context context) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int offset = context.getResources().getDimensionPixelOffset(R.dimen.item_margin);
        outRect.set(offset,offset,offset,offset);
    }


}
