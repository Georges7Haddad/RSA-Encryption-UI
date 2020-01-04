package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigInteger;


/**
 * A simple {@link Fragment} subclass.
 */
public class Variables extends Fragment {


    public Variables() {
    }

    private BigInteger p,q,n,phi,e,d;

    private TextView pvariable2, qvariable2, nvariable2, phivariable2, evariable2, dvariable2,t6,t7,t8,t9,t10,t11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view2 = inflater.inflate(R.layout.fragment_variables, container, false);

        //Getting the variables from the activity
        p = ((MainActivity)getActivity()).getP();
        q = ((MainActivity)getActivity()).getQ();
        n = ((MainActivity)getActivity()).getN();
        phi = ((MainActivity)getActivity()).getPhi();
        e = ((MainActivity)getActivity()).getE();
        d = ((MainActivity)getActivity()).getD();

        //Linking the UI to the code
        pvariable2 = (TextView)view2.findViewById(R.id.pvariable);
        qvariable2 = (TextView)view2.findViewById(R.id.qvariable);
        phivariable2 = (TextView)view2.findViewById(R.id.nvariable);
        nvariable2 = (TextView)view2.findViewById(R.id.phivariable);
        evariable2 = (TextView)view2.findViewById(R.id.evariable);
        dvariable2 = (TextView)view2.findViewById(R.id.dvariable);
        t6 = (TextView)view2.findViewById(R.id.textView6);
        t7 = (TextView)view2.findViewById(R.id.textView7);
        t8 = (TextView)view2.findViewById(R.id.textView8);
        t9 = (TextView)view2.findViewById(R.id.textView9);
        t10 = (TextView)view2.findViewById(R.id.textView10);
        t11 = (TextView)view2.findViewById(R.id.textView11);

        if (q !=null) {

            t6.setText("p:");
            t7.setText("q:");
            t8.setText("n:");
            t9.setText("phi:");
            t10.setText("e:");
            t11.setText("d:");
            pvariable2.setText(p + "");
            qvariable2.setText(q + "");
            phivariable2.setText(n + "");
            nvariable2.setText(phi + "");
            evariable2.setText(e + "");
            dvariable2.setText(d + "");
        }

        return view2;
    }



}
