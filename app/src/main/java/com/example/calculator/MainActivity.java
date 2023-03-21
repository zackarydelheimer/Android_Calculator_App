package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton butC,butBrackOpen,butBrackClose;
    MaterialButton butDivide,butMultiply,butPlus,butMinus,butEquals;
    MaterialButton but0,but1,but2,but3,but4,but5,but6,but7,but8,but9;
    MaterialButton butAC,butDot;


    //Gives each component of the app a referencable id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(butC,R.id.button_c);
        assignId(butAC,R.id.button_ac);
        assignId(butDot,R.id.button_dot);
        assignId(butBrackOpen,R.id.button_open_bracket);
        assignId(butBrackClose,R.id.button_close_bracket);
        assignId(butDivide,R.id.button_divide);
        assignId(butMultiply,R.id.button_multiply);
        assignId(butPlus,R.id.button_add);
        assignId(butMinus,R.id.button_subtract);
        assignId(butEquals,R.id.button_equals);
        assignId(but0,R.id.button_0);
        assignId(but1,R.id.button_1);
        assignId(but2,R.id.button_2);
        assignId(but3,R.id.button_3);
        assignId(but4,R.id.button_4);
        assignId(but5,R.id.button_5);
        assignId(but6,R.id.button_6);
        assignId(but7,R.id.button_7);
        assignId(but8,R.id.button_8);
        assignId(but9,R.id.button_9);


    }

    //Used in onCreate to assign each component an id
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    //Gives each button its function
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(dataToCalculate.length() == 1){
                resultTv.setText("0");
                return;
            }else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    //Performs the calculation
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}