package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CurrencyMenu extends AppCompatActivity {
    RadioGroup rgFrom1,rgFrom2,rgTo1,rgTo2;
    String curOg="",curCv="";
    RadioButton btn1,btn2;
//    Button btnOK,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_menu);
        Intent intent= this.getIntent();
        curOg= intent.getStringExtra("curOg");
        curCv= intent.getStringExtra("curCv");
        if(curOg==null) btn1=findViewById(R.id.radioButtond);
        else
            if(curOg.equals("CNY")) btn1= findViewById(R.id.radioButtona);
            else if(curOg.equals("EUR")) btn1= findViewById(R.id.radioButtonb);
            else if(curOg.equals("JPY")) btn1= findViewById(R.id.radioButtonf);
            else if(curOg.equals("KRW")) btn1= findViewById(R.id.radioButtonc);
            else if(curOg.equals("USD")) btn1= findViewById(R.id.radioButtond);
            else btn1= findViewById(R.id.radioButtone);
        if(curCv==null)
            btn2= findViewById(R.id.radioButton6);
        else
        if(curCv.equals("CNY")) btn2= findViewById(R.id.radioButton3);
        else if(curCv.equals("EUR")) btn2= findViewById(R.id.radioButton2);
        else if(curCv.equals("JPY")) btn2= findViewById(R.id.radioButton);
        else if(curCv.equals("KRW")) btn2= findViewById(R.id.radioButton4);
        else if(curCv.equals("USD")) btn2= findViewById(R.id.radioButton5);
        else btn2= findViewById(R.id.radioButton6);
        btn1.setChecked(true);
        btn2.setChecked(true);
        rgFrom1= findViewById(R.id.rgFrom1);
        rgFrom2=findViewById(R.id.rgFrom2);
        rgFrom1.setOnCheckedChangeListener(listenerFrom1);
        rgFrom2.setOnCheckedChangeListener(listenerFrom2);
        rgTo1= findViewById(R.id.rgTo1);
        rgTo2=findViewById(R.id.rgTo2);
        rgTo1.setOnCheckedChangeListener(listenerTo1);
        rgTo2.setOnCheckedChangeListener(listenerTo2);
//        btnOK= findViewById(R.id.btnOK);
      findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view) {
              int chkId1 = rgFrom1.getCheckedRadioButtonId();
              int chkId2 = rgFrom2.getCheckedRadioButtonId();
              int realCheck = chkId1 == -1 ? chkId2 : chkId1;
              btn1= findViewById(realCheck);
              curOg= btn1.getText().toString();
              chkId1 = rgTo1.getCheckedRadioButtonId();
              chkId2 = rgTo2.getCheckedRadioButtonId();
              realCheck = chkId1 == -1 ? chkId2 : chkId1;
              btn2=findViewById(realCheck);
              curCv= btn2.getText().toString();
              Intent intent = new Intent(CurrencyMenu.this, MainActivity.class);
              intent.putExtra("curOg",curOg);
              intent.putExtra("curCv",curCv);
              startActivity(intent);
          }
      });
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                rgFrom1.clearCheck();
                rgFrom2.clearCheck();
                rgTo1.clearCheck();
                rgTo2.clearCheck();
            }
        });
    }
    private RadioGroup.OnCheckedChangeListener listenerFrom1= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if (checkedId != -1) {
                rgFrom2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rgFrom2.clearCheck(); // clear the second RadioGroup!
                rgFrom2.setOnCheckedChangeListener(listenerFrom2); //reset the listener

            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listenerFrom2= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if (checkedId != -1) {
                rgFrom1.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rgFrom1.clearCheck(); // clear the second RadioGroup!
                rgFrom1.setOnCheckedChangeListener(listenerFrom1); //reset the listener
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listenerTo1= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if (checkedId != -1) {

                rgTo2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rgTo2.clearCheck(); // clear the second RadioGroup!
                rgTo2.setOnCheckedChangeListener(listenerTo2); //reset the listener

            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listenerTo2= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if (checkedId != -1) {

                rgTo1.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rgTo1.clearCheck(); // clear the second RadioGroup!
                rgTo1.setOnCheckedChangeListener(listenerTo1); //reset the listener
            }
        }
    };

}