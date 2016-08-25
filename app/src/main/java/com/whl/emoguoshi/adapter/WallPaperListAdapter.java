package com.whl.emoguoshi.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ItemAdBinding;
import com.whl.emoguoshi.databinding.ItemFruitBinding;
import com.whl.emoguoshi.databinding.ItemWallPaperBinding;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.domain.WallPaperBean;
import com.whl.emoguoshi.viewmodel.AdItemViewModel;
import com.whl.emoguoshi.viewmodel.FruitItemViewModel;
import com.whl.emoguoshi.viewmodel.WallPaperItemViewModel;

import java.util.List;


/**
 * Created by wanghl on 16/7/7.
 */
public class WallPaperListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_AD = 0;
    public static final int TYPE_ITEM = 1;

    private List<WallPaperBean> data;

    private Context context;


    public WallPaperListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_AD: {
                ItemAdBinding itemAdBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_ad,
                        parent,
                        false);
                return new AdViewHolder(itemAdBinding);
            }
            case TYPE_ITEM: {
                ItemWallPaperBinding itemWallPaperBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_wall_paper,
                        parent,
                        false);
                return new WallPaperViewHolder(itemWallPaperBinding);
            }
        }
        return null;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//       DevilFruit bean = data.get(position);
//            holder.bindItem(null);
        WallPaperBean bean = data.get(position);
        if (holder instanceof WallPaperViewHolder) {
            WallPaperViewHolder wallPaperViewHolder = (WallPaperViewHolder) holder;
            wallPaperViewHolder.bindItem(bean);
        } else {
            AdViewHolder adViewHolder = (AdViewHolder) holder;
            adViewHolder.bindItem(bean);
        }

    }

    @Override
    public int getItemCount() {

//        return count+1;
        return data == null ? 0 : data.size();
//        return 20;
    }

    @Override
    public int getItemViewType(int position) {
//        if (position % 7 == 0) {
//            return TYPE_AD;
//        }
//        return TYPE_ITEM;
        if (data == null) {
            return TYPE_ITEM;
        }
        return data.get(position).getType();
    }

    public void setData(List<WallPaperBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLoadMoreData(List<WallPaperBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public class WallPaperViewHolder extends RecyclerView.ViewHolder {
        private ItemWallPaperBinding binding;

        public WallPaperViewHolder(ItemWallPaperBinding binding) {
            super(binding.llContainer);
            this.binding = binding;
        }

        public void bindItem(WallPaperBean devilFruit) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new WallPaperItemViewModel(context));
            }

            binding.getViewModel().setPaper(devilFruit);
        }
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {
        private ItemAdBinding binding;

        public AdViewHolder(ItemAdBinding binding) {
            super(binding.llContainer);
            this.binding = binding;
        }

        public void bindItem(WallPaperBean devilFruit) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new AdItemViewModel());
            }
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
//            binding.getViewModel().setFruit(devilFruit);
        }
    }


}
