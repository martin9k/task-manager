package com.example.task;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.RegistrationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView singup;
    private EditText log;
    private EditText pass;
    private Button btnLog;
private  Button btnGmail;
    private FirebaseAuth mAuth;
    private FirebaseAuth mAuth1;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            startActivity( new Intent( getApplicationContext(),HomeActivity.class ) );
        }
        mDialog=new ProgressDialog( this );
        singup=findViewById( R.id.singup );
        log=findViewById( R.id.email_login );
        pass=findViewById( R.id.password );
        btnGmail=findViewById(R.id.gmail_btn);
        btnLog=findViewById( R.id.login_btn );

       btnGmail.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       } );
        btnLog.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail=log.getText().toString().trim();
                String mPassword=pass.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    log.setError( "Required field.." );
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    pass.setError( "Required field.." );
                    return;
                }
                mDialog.setMessage( "Processing.." );
                mDialog.show();
                mAuth.signInWithEmailAndPassword( mEmail,mPassword ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText( getApplicationContext(),"Login Successful",Toast.LENGTH_LONG ).show();
                            startActivity( new Intent( getApplicationContext(),HomeActivity.class ) );
                            mDialog.dismiss();
                        }else{
                            Toast.makeText( getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG ).show();
                            mDialog.dismiss();
                        }
                    }
                } );
            }
        } );

        singup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getApplicationContext(), RegistrationActivity.class ) );
            }
        } );
    }

    }


