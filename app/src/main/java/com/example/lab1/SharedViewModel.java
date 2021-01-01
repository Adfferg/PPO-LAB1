package com.example.lab1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> numberToConvert;
    private MutableLiveData<String> convertedNumber;
    private int selectedItem1 = 0;
    private int selectedItem2 = 0;
    private int selectedItem3 = 0;

     public LiveData<String> getNumberToConvert() {
        if(numberToConvert==null)
        {
            numberToConvert=new MutableLiveData<>();
            numberToConvert.setValue("");
        }
          return numberToConvert;
     }

     public void setNumberToConvert(String item) {
         numberToConvert.setValue(item);
     }

    public void setConvertedNumber(String item) {
        convertedNumber.setValue(item);
    }

    public LiveData<String> getConvertedNumber() {
        if(convertedNumber==null)
        {
            convertedNumber=new MutableLiveData<>();
            convertedNumber.setValue("");
        }
        return convertedNumber;
    }

    public void setSelectedItem1(int item) {
        selectedItem1 = item;
    }

    public int getSelectedItem1() {
        return selectedItem1;
    }

    public void setSelectedItem2(int item) {
        selectedItem2 = item;
    }

    public int getSelectedItem2() {
        return selectedItem2;
    }

    public void setSelectedItem3(int item) {
        selectedItem3 = item;
    }

    public int getSelectedItem3() {
        return selectedItem3;
    }


}
