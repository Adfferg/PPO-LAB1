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
        Button copyButton1 = rootView.findViewById(R.id.copyButton1);
        Button copyButton2 = rootView.findViewById(R.id.copyButton2);
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

        copyButton1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                CopyNumber(viewModel.getNumberToConvert().getValue());

            }
        });

        copyButton2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                CopyNumber(viewModel.getConvertedNumber().getValue());
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearAll();
            }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Convert();
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

    private void ClearAll(){
        viewModel.setNumberToConvert("");
        viewModel.setConvertedNumber("");
    }
    public void Delete(){
        if (viewModel.getNumberToConvert().getValue().length() > 0) {
            viewModel.setNumberToConvert(viewModel.getNumberToConvert().getValue().substring(0, viewModel.getNumberToConvert().getValue().length() - 1));
        }
    }
    public void Convert() {
        if (viewModel.getNumberToConvert().getValue().length() > 0 && !viewModel.getNumberToConvert().getValue().equals(".")) {
            int unitCategorySelected = viewModel.getSelectedItem1();
            int value1 = viewModel.getSelectedItem2();
            int value2 = viewModel.getSelectedItem3();
            if (value1 == value2)
                viewModel.setConvertedNumber(viewModel.getNumberToConvert().getValue());
            else if (unitCategorySelected == 0) {
                double value = Double.parseDouble(viewModel.getNumberToConvert().getValue());
                if (value1 == 1) value *= 0.3048;
                else if (value1 == 2) value *= 1609.34;
                switch (value2) {
                    case (0):
                        viewModel.setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        viewModel.setConvertedNumber(String.valueOf(value * 3.28084));
                        break;
                    case (2):
                        viewModel.setConvertedNumber(String.valueOf(value * 0.000621371));
                        break;
                }
            } else if (unitCategorySelected == 1) {
                double value = Double.parseDouble(viewModel.getNumberToConvert().getValue());
                if (value1 == 1) value *= 0.453592;
                else if (value1 == 2) value *= 0.0283495;
                switch (value2) {
                    case (0):
                        viewModel.setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        viewModel.setConvertedNumber(String.valueOf(value * 2.20462));
                        break;
                    case (2):
                        viewModel.setConvertedNumber(String.valueOf(value * 35.274));
                        break;
                }
            } else if (unitCategorySelected == 2) {
                double value = Double.parseDouble(viewModel.getNumberToConvert().getValue());
                if (value1 == 1) value -= 273.15;
                else if (value1 == 2) value = (value - 32) * 5 / 9;
                switch (value2) {
                    case (0):
                        viewModel.setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        viewModel.setConvertedNumber(String.valueOf(value + 273.15));
                        break;
                    case (2):
                        viewModel.setConvertedNumber(String.valueOf((value * 9 / 5) + 32));
                        break;
                }
            }
        } else {
            viewModel.setConvertedNumber("");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void CopyNumber(String copiedText) {
        int sdk = Build.VERSION.SDK_INT;
        if(sdk < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(copiedText);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("TAG",copiedText);
            clipboard.setPrimaryClip(clip);
        }
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
