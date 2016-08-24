package com.whl.emoguoshi.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.adapter.FruitListAdapter;
import com.whl.emoguoshi.adapter.WallPaperListAdapter;
import com.whl.emoguoshi.databinding.FragmentWallPaperBinding;
import com.whl.emoguoshi.recycler.MyItemDecoration;
import com.whl.emoguoshi.viewmodel.WallPaperViewModel;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WallPaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WallPaperFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewGroup rootView;
    private WallPaperListAdapter adapter;


    public WallPaperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WallPaperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WallPaperFragment newInstance(String param1, String param2) {
        WallPaperFragment fragment = new WallPaperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_wall_paper, container, false);

        if (rootView == null) {
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wall_paper, container, false);
            FragmentWallPaperBinding binding = DataBindingUtil.bind(rootView);
            WallPaperViewModel viewModel = new WallPaperViewModel();
            binding.setViewModel(viewModel);
            setUpRecyclerView(binding.rvPaper);
        }


        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new MyItemDecoration(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new WallPaperListAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = adapter.getItemViewType(position);
                switch (type) {
                    case WallPaperListAdapter.TYPE_AD:{
                        return 3;
                    }
                    case WallPaperListAdapter.TYPE_ITEM:{
                        return 1;
                    }
                }
                return 1;
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private void setUpRecyclerView2(RefreshRecyclerView recyclerView) {
        recyclerView.addItemDecoration(new MyItemDecoration(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new WallPaperListAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = adapter.getItemViewType(position);
                switch (type) {
                    case WallPaperListAdapter.TYPE_AD:{
                        return 3;
                    }
                    case WallPaperListAdapter.TYPE_ITEM:{
                        return 1;
                    }
                }
                return 1;
            }
        });
        RecyclerViewManager.with(adapter, gridLayoutManager)
                .setMode(RecyclerMode.NONE)
                .setOnBothRefreshListener(new OnBothRefreshListener() {
                    @Override
                    public void onPullDown() {
//                        viewModel.pullDown();
                    }

                    @Override
                    public void onLoadMore() {
//                        viewModel.loadMore();
                    }
                })
                .into(recyclerView, getContext());

    }
}
