package com.example.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername,mTextPassword;
    Button Login;
    DatabaseHelper db;
    AwesomeValidation awesomeValidation;
    TextView mTextViewForget;
    TextView mTextViewRegister;
    RelativeLayout rellay1,rellay2;
    Handler handler=new Handler();

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            rellay2.setVisibility(View.VISIBLE);
            rellay1.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();
        db =new DatabaseHelper(this);
        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);

        mTextViewRegister=(TextView) findViewById(R.id.vie_Signup);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(MainActivity.this,Register.class);
                startActivity(registerIntent);
            }
        });

        mTextViewForget=(TextView)findViewById(R.id.vie_Forget);

        mTextViewForget.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent forgetIntent=new Intent(MainActivity.this,Forget.class);
            startActivity(forgetIntent);
            }
        });

        rellay1= (RelativeLayout) findViewById(R.id.rellay1);
        rellay2=(RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable,  2000); //2000 is the timeout for the splash
    }

   private void updateUI(){
        mTextPassword=(EditText)findViewById(R.id.Password);
        mTextUsername =(EditText)findViewById(R.id.Username);

        Login=(Button)findViewById(R.id.View_me);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd=mTextPassword.getText().toString().trim();
                String user=mTextUsername.getText().toString().trim();

                Boolean rest =db.checkUser(user,pwd);

                if(rest){
                    Log.d("Login", "Loged In");
                   Intent logincreen=new Intent (MainActivity.this,HomeActivity.class);
                   startActivity(logincreen);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
