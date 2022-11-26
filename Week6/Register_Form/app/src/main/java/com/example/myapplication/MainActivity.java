package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextInputLayout txtFn,txtLn,txtAdress,txtEmail;
    TextView txtBirth;
    CheckBox ckPolicy;
    Button btnSelect;
    RadioGroup rgGender;
    RadioButton rbMale;
    int date=17,month1=10,year1=2001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFn=findViewById(R.id.txtFn);
        txtLn=findViewById(R.id.txtLn);
        txtBirth=findViewById(R.id.txtBirth);
        txtAdress=findViewById(R.id.txtAdress);
        txtEmail=findViewById(R.id.txtEmail);
        ckPolicy=findViewById(R.id.ckPolicy);
        btnSelect=findViewById(R.id.btnSelect);
        rgGender=findViewById(R.id.rgGender);
        rbMale=findViewById(R.id.rbMale);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtBirth.setText(day + "/" + month + "/" + year);
                                date=day;
                                month1=month;
                                year1=year;
                            }
                        },year1, month1, date);
                datePickerDialog.show();
            }

        });
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener(){
            boolean check=true;
            @Override
            public void onClick(View view) {
                validateName(txtFn);
                validateName(txtLn);
                validateName(txtAdress);
                validateEmail(txtEmail);
                validatePolicy(ckPolicy);
                validateGender(rgGender);
            }
        });
    }
    private Boolean validateGender(RadioGroup rgGender){
        if(rgGender.getCheckedRadioButtonId()==-1){
            rbMale.setError("Field cannot be empty");
            return false;
        }
        else {
            rbMale.setError(null);
            return true;
        }
    }
    private Boolean validatePolicy(CheckBox regPolicy){
        if(!regPolicy.isChecked()){
            regPolicy.setError("Field cannot be empty");
            return false;
        }
        else{
            regPolicy.setError(null);
            return true;
        }
    }
    private Boolean validateName(TextInputLayout regName) {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(TextInputLayout txtEmail) {
        String val = txtEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            txtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            txtEmail.setError("Invalid email address");
            return false;
        } else {
            txtEmail.setError(null);
            txtEmail.setErrorEnabled(false);
            return true;
        }
    }
}