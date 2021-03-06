package com.blackstone.goldenquran.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackstone.goldenquran.R;
import com.blackstone.goldenquran.adapters.Mo3jamWordsAdapter;
import com.blackstone.goldenquran.models.Mo3jamModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Mo3JamFragment extends Fragment {


    @BindView(R.id.Mo3jamRecycler)
    RecyclerView mo3jamRecycler;
    Unbinder unbinder;
    @BindView(R.id.mo3jamIsEmptyText)
    TextView mo3jamIsEmptyText;

    public Mo3JamFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mo3_jam, container, false);
        unbinder = ButterKnife.bind(this, view);
        mo3jamRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Mo3jamModel> mo3jamModels = getArguments().getParcelableArrayList("mo3jamWords");
        if (mo3jamModels != null && mo3jamModels.isEmpty()) {
            mo3jamIsEmptyText.setVisibility(View.VISIBLE);
        } else if (mo3jamModels != null) {
            for (int i = 0; i < mo3jamModels.size(); i++) {
                if (mo3jamModels.get(i).wordRoot.isEmpty()) {
                    mo3jamModels.remove(i);
                    i--;
                }
            }
            mo3jamRecycler.setAdapter(new Mo3jamWordsAdapter(getActivity(), mo3jamModels));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
