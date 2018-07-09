package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listaFragmentos = new ArrayList<>();
    private final List<String> titulos = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentos.size();
    }

    public void addFragment(Fragment fragment, String title){
        listaFragmentos.add(fragment);
        titulos.add(title);
    }
    @Override
    public CharSequence getPageTitle(int p){
        return titulos.get(p);
    }

}
