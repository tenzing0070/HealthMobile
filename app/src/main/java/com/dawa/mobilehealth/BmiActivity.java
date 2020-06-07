package com.dawa.mobilehealth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BmiActivity extends Fragment {
     Button btnCalculate;
     EditText weight,height;
    TextView resulttext;
    String calculation, BMIresult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_bmi, container, false);
        btnCalculate = view.findViewById(R.id.btnCalculateBmi);

        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        resulttext = view.findViewById(R.id.result);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }

            private void calculateBMI() {
                String S1 = weight.getText().toString();
                String S2 = height.getText().toString();

                float weightValue = Float.parseFloat(S1);
                float heightValue = Float.parseFloat(S2);

                float bmi = weightValue/(heightValue * heightValue)*10000;

                if (bmi<18.5){
                    BMIresult = "Under Weight\nPlease maintain you health.";
                }else if(bmi >=18.5 && bmi <=24.9){
                    BMIresult = "Normal Weight.\nKeep it up.";
                }else if(bmi >=25 && bmi <=29.9){
                    BMIresult = "Overweight.\nPlease maintain your health.";
                }else{
                    BMIresult ="Obese.\nPlease maintain your health.";
                }
                calculation = "Your Result\n"+"BMI=" + bmi + "\n" + BMIresult;

                resulttext.setText(calculation);
            }
        });
        return view;
        }
}