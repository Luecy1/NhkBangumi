package com.github.luecy1.nhkbangumi;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.luecy1.nhkbangumi.databinding.FragmentProgramDetailBinding;
import com.github.luecy1.nhkbangumi.viewmodel.DetailViewModel;

/**
 * Created by you on 2018/01/27.
 */

public class ProgramDetailFragment extends Fragment {
    FragmentProgramDetailBinding binding;

    private DetailViewModel detailViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_program_detail,container,false);

        return inflater.inflate(R.layout.fragment_program_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
    }
}
