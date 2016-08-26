package com.whl.emoguoshi.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.adapter.FruitListAdapter;
import com.whl.emoguoshi.databinding.FragmentSearchBinding;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.recycler.DividerItemDecoration;
import com.whl.emoguoshi.recycler.MyItemDecoration;
import com.whl.emoguoshi.viewmodel.SearchViewModel;

import java.util.List;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchViewModel.SearchViewModelListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewGroup rootView;
    private FragmentSearchBinding binding;
    private SearchViewModel viewModel;
    private FruitListAdapter adapter;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
//        return inflater.inflate(R.layout.fragment_search, container, false);


        if (rootView == null) {
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
            binding = DataBindingUtil.bind(rootView);
            viewModel = new SearchViewModel(this);
            binding.setViewModel(viewModel);

            setUpRecyclerView(binding.rvFruit);
        }


        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new MyItemDecoration(getContext(),R.dimen.item_margin2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new FruitListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onDestroy() {
        if (viewModel != null) {
            viewModel.onDestroy();
            viewModel = null;
        }
        super.onDestroy();
    }

    @Override
    public void onFruitsLoadFinish(List<DevilFruit> devilFruits) {
        adapter.setData(devilFruits);
    }
}
