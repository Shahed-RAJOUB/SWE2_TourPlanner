package validation;

import javafx.scene.control.TextField;

public class CityTextFieldValidation extends TextField {
    public CityTextFieldValidation(){
        this.setPromptText("Enter only City ");
    }

    @Override
    public void replaceText(int i , int il , String string){
        if(string.matches("[a-zA-Z]")||string.isEmpty()){
            super.replaceText(i , il, string);
        }
    }
    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}
