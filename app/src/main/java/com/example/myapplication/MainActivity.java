package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import com.example.myapplication.ui.main.SectionsPagerAdapter;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    public BigInteger p,q,n,phi,e,d;

    public BigInteger getP() {
        return p;
    }
    public void setP(BigInteger test) {
        this.p = test;
    }

    public BigInteger getQ() {
        return q;
    }
    public void setQ(BigInteger test) {
        this.q = test;
    }

    public BigInteger getN() {
        return n;
    }
    public void setN(BigInteger test) {
        this.n = test;
    }

    public BigInteger getPhi() {
        return phi;
    }
    public void setPhi(BigInteger test) {
        this.phi = test;
    }

    public BigInteger getE() {
        return e;
    }
    public void setE(BigInteger test) {
        this.e = test;
    }

    public BigInteger getD() {
        return d;
    }
    public void setD(BigInteger test) {
        this.d = test;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

}