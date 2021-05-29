package com.example.wheretowatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "태그";
    private FirebaseAuth mFirebaseAuth; // firebase 인증
    private DatabaseReference mDatabaseRef; // 실시간 database
    private EditText mEdtName, mEdtEmail, mEdtPwd; // 로그인 입력필드
    private Button btn_login; // 로그인 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("WhereToWatch");

        mEdtName = findViewById(R.id.et_name);
        mEdtEmail = findViewById(R.id.et_email);
        mEdtPwd = findViewById(R.id.et_pwd);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 요청
                String strName = mEdtName.getText().toString();
                String strEmail = mEdtEmail.getText().toString();
                String strPwd = mEdtPwd.getText().toString();

                if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strPwd)) {
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    login(strName, strEmail, strPwd);
                }

//                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // 로그인 성공
//                            Log.e(TAG, "onComplete: login 성공");
//
//                            SharedPreferences sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);
//                            SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
//                            editor.putString("login", str); // key,value 형식으로 저장
//                            editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
//
//                            Toast.makeText(LoginActivity.this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show();
//
//
//                            Intent intent = new Intent(LoginActivity.this, MyPageFragment.class);
//                            startActivity(intent);
////                            finish(); // 현재 activity finish
//                        } else {
//                            Log.e(TAG, "onComplete: login 실패");
//                            Toast.makeText(LoginActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });



            }
        });


        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(final String name, final String email, final String password) {

        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SharedPreferences sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("login", name); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                    Toast.makeText(getApplicationContext(),"로그인되었습니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"로그인 오류",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}