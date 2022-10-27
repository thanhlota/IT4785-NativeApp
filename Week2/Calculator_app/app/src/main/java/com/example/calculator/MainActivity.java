package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    boolean newOp=true,isFloat=false;
    String nowValue="0";
    String calc="";
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private void addEvents() {
    }
    private void addControls() {
        txtResult=findViewById(R.id.txtResult);
    }
    public void numberEvent(View view) {
        String number= txtResult.getText().toString();
        if(newOp) number="";
        newOp=false;
        switch(view.getId()){
            case R.id.btn0:
                number+="0";
                break;
            case R.id.btn1:
                number+="1";
                break;
            case R.id.btn2:
                number+="2";
                break;
            case R.id.btn3:
                number+="3";
                break;
            case R.id.btn4:
                number+="4";
                break;
            case R.id.btn5:
                number+="5";
                break;
            case R.id.btn6:
                number+="6";
                break;
            case R.id.btn7:
                number+="7";
                break;
            case R.id.btn8:
                number+="8";
                break;
            case R.id.btn9:
                number+="9";
                break;
            case R.id.btnFloat:
                number+=".";
                isFloat=true;
                break;
            case R.id.btnSign:
                try{
                    int num= Integer.parseInt(number);
                    num=-num;
                    number= String.valueOf(num);
                }catch(NumberFormatException e){
                 float num=Float.parseFloat(number);
                 num=-num;
                 number= String.valueOf(num);
                }
        }
        txtResult.setText(number);
    }
    public void calculate(String op){
        String tmp= txtResult.getText().toString();
        if(isFloat){
           float foo= Float.parseFloat(tmp);
           float foo1= Float.parseFloat(nowValue);
           if(op.equals("+")){
                foo1+=foo;
           }
           else if(op.equals("-")){
                foo1-=foo;
           }
           else if(op.equals("/")){
                 foo1/=foo;
           }
           else{
              foo1*=foo;
           }
          txtResult.setText(String.valueOf(foo1));
           nowValue=String.valueOf(foo1);
      }
      else{
          int foo= Integer.parseInt(tmp);
          int foo1= Integer.parseInt(nowValue);
          float gf = 0;
          if(op.equals("+")){
              foo1+=foo;
          }
          else if(op.equals("-")){
              foo1-=foo;
          }
          else if(op.equals("/")){
                gf= (float) foo1/foo;
          }
          else foo1 *= foo;
          if(op.equals("/")){
              txtResult.setText(String.valueOf(gf));
              nowValue= String.valueOf(gf);
          }
          else txtResult.setText(String.valueOf(foo1));
      }

    }
    public void calcEvent(View view){
        if(!newOp){
            newOp=true;
            if(calc.length()!=0){
                if(calc.equals("+")){
                  calculate("+");
                }
                else if(calc.equals("-")){
                    calculate("-");
                }
                else if(calc.equals("x")){
                    calculate("x");
                }
                else{
                    calculate("/");
                }
            }
            switch(view.getId()){
                case R.id.btnAdd: calc="+"; break;
                case R.id.btnDivide:calc="/"; break;
                case R.id.btnMinus:calc="-"; break;
                case R.id.btnx:calc="x"; break;
                case R.id.btnEqual:
                    calc="";
                    newOp=true;isFloat=false;
                    nowValue="0";
            }
            nowValue=  txtResult.getText().toString();
        }
    }
    public void clear(View view){
          txtResult.setText("0");
          calc="";
          newOp=true;isFloat=false;
          nowValue="0";
    }
    public void clearEntry(View view){
        txtResult.setText("");
    }
    public void backSpace(View view){
        String tmp = txtResult.getText().toString();
        if(tmp!=null){
            txtResult.setText(tmp.replaceAll(".$",""));
        }
    }
}