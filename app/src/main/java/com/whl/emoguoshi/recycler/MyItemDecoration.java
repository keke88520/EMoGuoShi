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
    private int r;

    public MyItemDecoration(Context context,int r) {
        this.r = r;
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int offset = context.getResources().getDimensionPixelOffset(r);
        outRect.set(offset,offset,offset,offset);
    }


}
