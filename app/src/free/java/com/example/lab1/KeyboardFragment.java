package com.example.lab1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class KeyboardFragment extends Fragment implements View.OnClickListener{
    public KeyboardFragment(){
    }
    private SharedViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView =
                inflater.inflate(R.layout.fragment_keyboard, container, false);
        Button button1 = rootView.findViewById(R.id.button_one);
        Button button2 = rootView.findViewById(R.id.button_two);
        Button button3 = rootView.findViewById(R.id.button_three);
        Button button4 = rootView.findViewById(R.id.button_four);
        Button button5 = rootView.findViewById(R.id.button_five);
        Button button6 = rootView.findViewById(R.id.button_six);
        Button button7 = rootView.findViewById(R.id.button_seven);
        Button button8 = rootView.findViewById(R.id.button_eight);
        Button button9 = rootView.findViewById(R.id.button_nine);
        Button button0 = rootView.findViewById(R.id.button_zero);
        Button buttonDot = rootView.findViewById(R.id.button_dot);
        Button deleteButton = rootView.findViewById(R.id.delete_button);
        Button clearAllButton = rootView.findViewById(R.id.clear_button);
        Button convertButton = rootView.findViewById(R.id.convertButton);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Delete();
            }
        });
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.ClearAll();
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Convert();;
            }
        });
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel.class);
    }

    String translateIdToIndex(int id) {
        String number = "";
        switch (id) {
            case R.id.button_one:
                number = "1";
                break;
            case R.id.button_two:
                number = "2";
                break;
            case R.id.button_three:
                number = "3";
                break;
            case R.id.button_four:
                number = "4";
                break;
            case R.id.button_five:
                number = "5";
                break;
            case R.id.button_six:
                number = "6";
                break;
            case R.id.button_seven:
                number = "7";
                break;
            case R.id.button_eight:
                number = "8";
                break;
            case R.id.button_nine:
                number = "9";
                break;
            case R.id.button_zero:
                number = "0";
                break;
            case R.id.button_dot:
                number = ".";
                break;
        }
        return number;
    }

    @Override
    public void onClick(View v) {
        String number = translateIdToIndex(v.getId());
        String s = viewModel.getNumberToConvert().getValue();
        assert s != null;
        if (!number.equals(".") || s.indexOf('.') == -1)
            viewModel.setNumberToConvert(s + number);
    }

}
