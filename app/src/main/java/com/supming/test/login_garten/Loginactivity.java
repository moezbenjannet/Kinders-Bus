package com.supming.test.login_garten;

/**
 * Created by Omar Bouhamed on 25/11/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.transition.Transition;
import android.util.Log ;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.supming.test.GartenActivity;
import com.supming.test.MainActivity;
import com.supming.test.R;
import com.supming.test.SigninActivity;
import com.supming.test.fragment_garten.HomeGarten_Main;

public class Loginactivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText input_email,input_password;
    TextView btnSignup,btnForgotPass;
    int gid;
    String email ="";

    RelativeLayout activity_main;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //get data from HomeGarten_Main
        gid = getIntent().getIntExtra("data",1);

        //View
        btnLogin = (Button)findViewById(R.id.login_btn_login);
        input_email = (EditText)findViewById(R.id.login_email);
        input_password = (EditText)findViewById(R.id.login_password);
        btnSignup = (TextView)findViewById(R.id.login_btn_signup);
        btnForgotPass = (TextView)findViewById(R.id.login_btn_forgot_password);
        activity_main = (RelativeLayout)findViewById(R.id.activity_main);

        btnSignup.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //Init Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Check already session , if ok-> DashBoardString
        if(auth.getCurrentUser() != null ) {
            email = getemailid(auth.getCurrentUser().getEmail());
            if(email.equals(""+gid)){
                Intent intent = new Intent(this,SigninActivity.class);
                intent.putExtra("data",gid);
                startActivity(intent);
            finish();
            }
        }
    }

    public String getemailid(String input){
        String name = input ;
        char c = name.charAt(0);
        char b;
        int i=0;
        while (c!='.' && i<=name.length()-2)
        {
            i++;
            c = name.charAt(i);
        }
        int j=i;
        b=name.charAt(i);
        while (b!='@' && i<=name.length()-2){
            i++;
            b=name.charAt(i);
        }
        email ="";
        for (int k=j+1;k<i;k++){
            email=email+name.charAt(k);
        }
        return email;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_btn_forgot_password)
        {
            startActivity(new Intent(Loginactivity.this ,ForgotPassword.class));
            finish();
        }
        else if(view.getId() == R.id.login_btn_signup)
        {
            finish();
        }
        else if(view.getId() == R.id.login_btn_login)
        {
            if(input_email.getText().toString().length()<=0 || input_password.getText().toString().length()<=0)
            {
                if(input_email.getText().toString().length()<=0)
                   Toast.makeText(this,"please enter your email",Toast.LENGTH_SHORT).show();
                else
                   Toast.makeText(this,"please enter your password",Toast.LENGTH_SHORT).show();
            }
            else {
            String emailid = getemailid(input_email.getText().toString());
            if(emailid.equals(""+gid))
            loginUser(input_email.getText().toString(),input_password.getText().toString());
            else
            Toast.makeText(this,"check your email or your password",Toast.LENGTH_SHORT).show();
                 }
        }
    }

    private void loginUser(String email, final String password) {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Snackbar snackB = Snackbar.make(activity_main,"Error: "+task.getException(),Snackbar.LENGTH_SHORT);
                            snackB.show();
                            Log.e("ERORRRRRRRRRRRRRRRRRRRR"+task.getException(),":'(");
                            if(password.length() < 6)
                            {
                                Snackbar snackBar = Snackbar.make(activity_main,"Password length must be over 6",Snackbar.LENGTH_SHORT);
                                snackBar.show();
                            }
                        }
                        else{
                            Intent intent = new Intent(Loginactivity.this,SigninActivity.class);
                            intent.putExtra("data",gid);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
