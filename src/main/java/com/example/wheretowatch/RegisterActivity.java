package com.example.wheretowatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance(); // firebase 인증
    private FirebaseDatabase mDatabaseRef = FirebaseDatabase.getInstance();
    private DatabaseReference userReference = mDatabaseRef.getReference("WhereToWatch");

    // 실시간 database
    private EditText mEdtName, mEdtEmail, mEdtPwd; // 회원가입 입력필드
    private Button mBtnRegister; // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DatabaseReference userReference = mDatabaseRef.getReference();

        mEdtName = findViewById(R.id.et_name);
        mEdtEmail = findViewById(R.id.et_email);
        mEdtPwd = findViewById(R.id.et_pwd);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 처리 시작
                String strName = mEdtName.getText().toString();
                String strEmail = mEdtEmail.getText().toString();
                String strPwd = mEdtPwd.getText().toString();


                if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPwd)) {
                    Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                        HashMap result = new HashMap<>();
                        result.put("name", strName);
                        result.put("email", strEmail);
                        result.put("password", strPwd);
                        registerNewAccount(strName, strEmail, strPwd);
                }

//                // Firebase Auth 진행
//                mFirebaseAuth.createUserWithEmailAndPassword(strName, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        // 인증처리 완료되면
//                        if (task.isSuccessful()) {
//                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                            UserAccount account = new UserAccount(username, email, password);
//                            account.setUserName(firebaseUser.getDisplayName());
//                            account.setIdToken(firebaseUser.getUid());
//                            account.setEmailId(firebaseUser.getEmail());
//                            account.setPassword(strPwd);
//
//                            // setValue는 database에 insert(삽입)하는 행
//                            userReference.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
//
//                            Toast.makeText(RegisterActivity.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
//                            finish();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

            }


        });
    }

    private void registerNewAccount(final String username, final String email, final String password) {

        final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
        mDialog.setMessage("가입중입니다...");
        mDialog.show();
        UserAccount user = new UserAccount(username, email, password);

        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    mDialog.dismiss();
                    Log.d("userName3", username);
                    userReference.child("users").child(username).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("userName4", username);
                            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Register Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}