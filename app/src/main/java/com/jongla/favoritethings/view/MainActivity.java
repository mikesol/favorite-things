package com.jongla.favoritethings.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.jongla.favoritethings.R;
import com.jongla.favoritethings.databinding.MainActivityBinding;
import com.jongla.favoritethings.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainViewModel = new MainViewModel();
        binding.setViewModel(mainViewModel);
        setSupportActionBar(binding.toolbar);
        mainViewModel.setupViewPager(binding.viewPager, getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.editTextUsername.getWindowToken(), 0);
    }
}
