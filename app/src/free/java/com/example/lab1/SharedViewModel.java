package com.example.lab1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> numberToConvert;
    private MutableLiveData<String> convertedNumber;
    private int selectedItem1 = 0;
    private MutableLiveData<Integer> selectedItem2;
    private MutableLiveData<Integer> selectedItem3;
  //  private int selectedItem2 = 0;
  //  private int selectedItem3 = 0;

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
        selectedItem2.setValue(item);
    }

    public LiveData<Integer> getSelectedItem2() {
        if(selectedItem2==null)
        {
            selectedItem2=new MutableLiveData<>();
            selectedItem2.setValue(0);
        }
         return selectedItem2;
    }

    public void setSelectedItem3(int item) {
        selectedItem3.setValue(item);
    }

    public LiveData<Integer> getSelectedItem3() {
        if(selectedItem3==null)
        {
            selectedItem3=new MutableLiveData<>();
            selectedItem3.setValue(0);
        }
        return selectedItem3;
    }

    public void Convert() {
        if (getNumberToConvert().getValue().length() > 0 && !getNumberToConvert().getValue().equals(".")) {
            int unitCategorySelected = getSelectedItem1();
            int value1 = getSelectedItem2().getValue();
            int value2 = getSelectedItem3().getValue();
            if (value1 == value2)
                setConvertedNumber(getNumberToConvert().getValue());
            else if (unitCategorySelected == 0) {
                double value = Double.parseDouble(getNumberToConvert().getValue());
                if (value1 == 1) value *= 0.3048;
                else if (value1 == 2) value *= 1609.34;
                switch (value2) {
                    case (0):
                        setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        setConvertedNumber(String.valueOf(value * 3.28084));
                        break;
                    case (2):
                        setConvertedNumber(String.valueOf(value * 0.000621371));
                        break;
                }
            } else if (unitCategorySelected == 1) {
                double value = Double.parseDouble(getNumberToConvert().getValue());
                if (value1 == 1) value *= 0.453592;
                else if (value1 == 2) value *= 0.0283495;
                switch (value2) {
                    case (0):
                        setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        setConvertedNumber(String.valueOf(value * 2.20462));
                        break;
                    case (2):
                        setConvertedNumber(String.valueOf(value * 35.274));
                        break;
                }
            } else if (unitCategorySelected == 2) {
                double value = Double.parseDouble(getNumberToConvert().getValue());
                if (value1 == 1) value -= 273.15;
                else if (value1 == 2) value = (value - 32) * 5 / 9;
                switch (value2) {
                    case (0):
                        setConvertedNumber(String.valueOf(value));
                        break;
                    case (1):
                        setConvertedNumber(String.valueOf(value + 273.15));
                        break;
                    case (2):
                        setConvertedNumber(String.valueOf((value * 9 / 5) + 32));
                        break;
                }
            }
        } else {
            setConvertedNumber("");
        }
    }

    public void ClearAll(){
        setNumberToConvert("");
        setConvertedNumber("");
    }

    public void Delete(){
        if (getNumberToConvert().getValue().length() > 0) {
            setNumberToConvert(getNumberToConvert().getValue().substring(0, getNumberToConvert().getValue().length() - 1));
        }
    }
    }
