package com.example.login;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import static com.basgeekball.awesomevalidation.utility.RegexTemplate.TELEPHONE;

public class Register extends AppCompatActivity {

        DatabaseHelper db;
        EditText mTextFirstname,mTextUsername,mTextPassword,mTextComfirmPassword,mTextEmail;
        Button Register;
        AwesomeValidation awesomeValidation;
        TextView mTextViewLogin;
        TextView mTextViewForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DatabaseHelper(this);
        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);
        mTextViewLogin=(TextView)findViewById(R.id.view_Singin);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(Register.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mTextViewForget=(TextView)findViewById(R.id.view_Forget);
        mTextViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetIntent=new Intent(Register.this,Forget.class);
                startActivity(forgetIntent);
            }
        });
            updateU();
    }
    private void updateU(){
        mTextFirstname=(EditText)findViewById(R.id.Firsname);
        mTextUsername=(EditText)findViewById(R.id.Username1);
        mTextPassword=(EditText)findViewById(R.id.Password1);
        mTextEmail=(EditText)findViewById(R.id.Email);
        mTextComfirmPassword=(EditText)findViewById(R.id.ComfirmPassword);
        Register=(Button)findViewById(R.id.review_login);



        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(Register.this,R.id.Firsname,"[a-zA-Z\\s]+",R.string.Usernamee);
        awesomeValidation.addValidation(Register.this,R.id.Password1,regexPassword ,R.string.Passwordd);
        awesomeValidation.addValidation(Register.this,R.id.ComfirmPassword,R.id.Password1,R.string.ComfirmPasswordd);
        awesomeValidation.addValidation(Register.this,R.id.Email,android.util.Patterns.EMAIL_ADDRESS,R.string.Emaill);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strFirstName,strSurname, strEmail, strPassword;
                strFirstName=mTextFirstname.getText().toString();
                strSurname = mTextUsername.getText().toString();
                strEmail = mTextEmail.getText().toString();
                strPassword=mTextPassword.getText().toString();


                    DatabaseHelper dh = new DatabaseHelper(getApplicationContext());

                    ContentValues values = new ContentValues();


                    values.put(Database.Student.COL_1,strFirstName );
                    values.put(Database.Student.COL_2, strSurname);
                    values.put(Database.Student.COL_3, strEmail);
                    values.put(Database.Student.COL_4, strPassword);



                    dh.insert(Database.Student.TABLE_NAME, values);

                    Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();

                    mTextFirstname.setText("");
                    mTextUsername.setText("");
                    mTextEmail.setText("");
                    mTextPassword.setText("");


                    Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);




            }

        });
    }
}
