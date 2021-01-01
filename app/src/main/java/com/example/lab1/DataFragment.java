package com.example.lab1;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DataFragment extends Fragment {
    public DataFragment() {
    }

    private SharedViewModel viewModel;
    private TextView number_to_convert_view, converted_number_view;
    private Spinner spinner1, spinner2, spinner3;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView =
                inflater.inflate(R.layout.fragment_data, container, false);
        number_to_convert_view = rootView.findViewById(R.id.number_to_convert_view);
        converted_number_view = rootView.findViewById(R.id.converted_number_view);
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel.class);
        viewModel.getNumberToConvert().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                number_to_convert_view.setText(charSequence);
            }
        });
        viewModel.getConvertedNumber().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                converted_number_view.setText(charSequence);
            }
        });
        number_to_convert_view.setText(viewModel.getNumberToConvert().getValue());
        converted_number_view.setText(viewModel.getConvertedNumber().getValue());
        Button swapButton = rootView.findViewById(R.id.swapButton);
        spinner1 = rootView.findViewById(R.id.spinner1);
        spinner2 = rootView.findViewById(R.id.spinner2);
        spinner3 = rootView.findViewById(R.id.spinner3);
        final ArrayAdapter<?> adapter1 =
                ArrayAdapter.createFromResource(rootView.getContext(), R.array.unit_category, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        //Адаптер дистанций
        final ArrayAdapter<?> distance_adapter =
                ArrayAdapter.createFromResource(rootView.getContext(), R.array.distance, android.R.layout.simple_spinner_item);
        distance_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Адаптер весов
        final ArrayAdapter<?> weight_adapter =
                ArrayAdapter.createFromResource(rootView.getContext(), R.array.weight, android.R.layout.simple_spinner_item);
        weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Адаптер температур
        final ArrayAdapter<?> temperature_adapter =
                ArrayAdapter.createFromResource(rootView.getContext(), R.array.temperature, android.R.layout.simple_spinner_item);
        temperature_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setSelection(viewModel.getSelectedItem1());
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.unit_category);
                if (choose[selectedItemPosition].equals(choose[0])) {
                    spinner2.setAdapter(distance_adapter);
                    spinner3.setAdapter(distance_adapter);
                } else if (choose[selectedItemPosition].equals(choose[1])) {
                    spinner2.setAdapter(weight_adapter);
                    spinner3.setAdapter(weight_adapter);
                } else if (choose[selectedItemPosition].equals(choose[2])) {
                    spinner2.setAdapter(temperature_adapter);
                    spinner3.setAdapter(temperature_adapter);
                }
                spinner2.setSelection(viewModel.getSelectedItem2());
                spinner3.setSelection(viewModel.getSelectedItem3());
                viewModel.setSelectedItem1(spinner1.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                viewModel.setSelectedItem2(spinner2.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                viewModel.setSelectedItem3(spinner3.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Swap();
            }
        });
        return rootView;
    }

    public void Swap() {
       int temp = spinner2.getSelectedItemPosition();
        spinner2.setSelection(spinner3.getSelectedItemPosition());
        spinner3.setSelection(temp);
        String temp2 = viewModel.getNumberToConvert().getValue();
        viewModel.setNumberToConvert(viewModel.getConvertedNumber().getValue());
        viewModel.setConvertedNumber(temp2);
        temp = viewModel.getSelectedItem2();
        viewModel.setSelectedItem2(viewModel.getSelectedItem3());
        viewModel.setSelectedItem3(temp);
        number_to_convert_view.setText(viewModel.getNumberToConvert().getValue());
        converted_number_view.setText(viewModel.getConvertedNumber().getValue());
    }

}