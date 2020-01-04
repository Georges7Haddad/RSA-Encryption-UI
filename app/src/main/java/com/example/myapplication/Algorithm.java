package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Switch;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.widget.CompoundButton;
import android.widget.TextView;



import java.math.BigInteger;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Algorithm extends Fragment {


    public Algorithm() {
    }

    private Button button0, button1, copy;
    private EditText message,cipher, plain;
    private TextView text;
    private Switch keysize;

    BigInteger p,q,n,phi,e,d,AlicePlain, AliceCipher, BobPlain, BobCipher;
    private int size;
    private String AliceMsg, BobMsg;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_algorithm, container, false);

        //Linking the UI to the code
        button0 = (Button) rootView.findViewById(R.id.send);
        button1 = (Button) rootView.findViewById(R.id.decrypt);
        copy = (Button) rootView.findViewById(R.id.copy);
        message=(EditText)rootView.findViewById(R.id.message);
        cipher=(EditText)rootView.findViewById(R.id.cipher);
        plain=(EditText)rootView.findViewById(R.id.plain);
        keysize =(Switch)rootView.findViewById(R.id.key);
        text = (TextView)rootView.findViewById(R.id.pvariable);




        size = 1024; // we chose the size to be 1024 bits long by default
        //Switch between a key size of 1024 and 2048
        keysize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                size = 2048;
                if (!isChecked) {
                    size = 1024;
                }
            }
        });


            button0.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //This if statement is in case the user clicks but there is no message to encrypt
                    if(!message.getText().toString().matches("")) {

                        //Hide Keyboard after Click
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                        // Step 1: generate 2 large prime numbers
                        Random rnd = new Random();
                        Random rnd2 = new Random();
                        p = BigInteger.probablePrime(size, rnd);
                        ((MainActivity) getActivity()).setP(p); //Setting p in the activity
                        q = BigInteger.probablePrime(size, rnd2);
                        ((MainActivity) getActivity()).setQ(q); //Setting q in the activity


                        // Step 2: compute n=p*q
                        n = p.multiply(q);
                        ((MainActivity) getActivity()).setN(n); //Setting n in the activity


                        // Step 3: calculate ø(n) = (p - 1).(q - 1)
                        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
                        ((MainActivity) getActivity()).setPhi(phi); //Setting phi in the activity

                        // Step 4: Find e such that gcd(e, ø(n)) = 1 ; 1 < e < ø(n)
                        do {
                            e = new BigInteger(2 * size, new Random());
                        } while ((e.compareTo(phi) != 1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));
                        ((MainActivity) getActivity()).setE(e); //Setting e in the activity


                        // Step 5: generate d such that e.d = 1 (mod ø(n))
                        d = e.modInverse(phi);
                        ((MainActivity) getActivity()).setD(d); //Setting d in the activity

                        AliceMsg = message.getText().toString(); // Read the message from Alice, and store it in string AliceMsg
                        AlicePlain = new BigInteger(AliceMsg.getBytes()); // Turn AliceMsg to a Big Integer AlicePlain
                        AliceCipher = AlicePlain.modPow(e, n); // Find Cipher in text
                        cipher.setText(AliceCipher + "");


                        //Create a new instance of fragment Variables to update the variables(p,q,n,phi,e,d) to be printed in the Variables tab
                        Variables fragment = new Variables();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.placeholder, fragment);
                        fragmentTransaction.commit();
                    }

                }
            });


            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //This if statement is in case the user clicks but there is no message to decrypt
                    if (!cipher.getText().toString().matches("")) {
                        BobMsg = cipher.getText().toString(); // Take the Cipher text from box
                        BobCipher = new BigInteger(BobMsg.getBytes()); // Convert cipher text from string to Big Integer
                        BobPlain = AliceCipher.modPow(d, n);// Decrypting the plain cipher text
                        BobMsg = new String(BobPlain.toByteArray()); // Convert from Big Integer to string
                        plain.setText(BobMsg + "");
                    }
                }
            });

            //This is the button to copy the cipher text
            copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Cipher Text", cipher.getText());
                clipboard.setPrimaryClip(clip);
            }
        });
        return rootView;
    }

}
