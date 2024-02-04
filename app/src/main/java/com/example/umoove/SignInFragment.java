package com.example.umoove;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
    Button signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, true);

        signInButton = (Button)view.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), HomeActivity.class));
    }

    public static SignInFragment newInstance(String text) {

        SignInFragment f = new SignInFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}