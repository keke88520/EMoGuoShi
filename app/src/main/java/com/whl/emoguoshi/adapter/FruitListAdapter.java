package com.whl.emoguoshi.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ItemFruitBinding;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.viewmodel.FruitItemViewModel;

import java.util.List;


/**
 * Created by wanghl on 16/7/7.
 */
public class FruitListAdapter extends RecyclerView.Adapter<FruitListAdapter.FruitViewHolder> {


    private List<DevilFruit> data;

    private Context context;




    public FruitListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FruitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemFruitBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_fruit,
                    parent,
                    false);
            return new FruitViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(FruitViewHolder holder, int position) {
       DevilFruit bean = data.get(position);
            holder.bindItem(bean);

    }

    @Override
    public int getItemCount() {

//        return count+1;
        return data == null ? 0 : data.size();
//        return 20;
    }



    public class FruitViewHolder extends RecyclerView.ViewHolder {
        private ItemFruitBinding binding;

        public FruitViewHolder(ItemFruitBinding binding) {
            super(binding.llContainer);
            this.binding = binding;
        }
        public void bindItem(DevilFruit devilFruit) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new FruitItemViewModel(context));
            }

            binding.getViewModel().setFruit(devilFruit);
        }
    }



    public void setData(List<DevilFruit> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLoadMoreData(List<DevilFruit> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }






}
