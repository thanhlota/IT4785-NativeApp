package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    EditText txtInput,txtResult;
    TextView txtOrigin,txtConvert;
    Double og,cv,usd2vnd=24000.0;
    String curOg,curCv;
    HashMap<String, Double> curMap = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= this.getIntent();
        curOg= intent.getStringExtra("curOg");
        curCv= intent.getStringExtra("curCv");
        curMap.put("VND",1.0);
        curMap.put("EUR",25813.8);
        curMap.put("JPY",178.32);
        curMap.put("KRW",18.67);
        curMap.put("USD",24.833);
        curMap.put("CNY",3457.3);
        if(curOg==null){
            curOg="USD";
        }
        if(curCv==null){
            curCv="VND";
        }
        txtOrigin=findViewById(R.id.txtOrigin);
        txtConvert=findViewById(R.id.txtConvert);
        txtOrigin.setText(curOg);
        txtConvert.setText(curCv);
        og= curMap.get(curOg);
        cv= curMap.get(curCv);
        txtInput= findViewById(R.id.txtInput);
        txtResult= findViewById(R.id.txtResult);
        findViewById(R.id.btnConvert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrencyMenu.class);
                intent.putExtra("curOg",curOg);
                intent.putExtra("curCv",curCv);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                txtInput.setText("");
                txtResult.setText("");
            }
        });
        txtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String foo= txtInput.getText().toString();
                if(!foo.isEmpty()){
                double origin= Double.parseDouble(foo);
                usd2vnd= og/cv;
                double convert= origin*usd2vnd;
                txtResult.setText(String.valueOf(convert));}
                else txtResult.setText("");
            }
        });
    }
}