package com.kyle.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.kyle.fragment.InformationFragment;


public class Activity_information extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        FragmentManager manager = getSupportFragmentManager();
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sa", "news");
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_information_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
