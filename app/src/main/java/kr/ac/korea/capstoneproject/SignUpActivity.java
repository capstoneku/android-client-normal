package kr.ac.korea.capstoneproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.ac.korea.capstoneproject.data.pojo.SignUpData;
import kr.ac.korea.capstoneproject.data.pojo.SignUpResponse;
import kr.ac.korea.capstoneproject.data.remote.SignUpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class SignUpActivity extends AppCompatActivity {
    private Retrofit mRetrofit;
    private SignUpRequest mSignUpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initConnection();
        initView();
    }

    /**
     * 네트워크 관련 객체 초기화 함수
     */
    private void initConnection() {
        mRetrofit = getInstance();
        mSignUpRequest = mRetrofit.create(SignUpRequest.class);
    }

    /**
     * 뷰 객체 초기화 함수
     * 1개의 Button과 4개의 EditText를 초기화한다.
     * 회원가입 버튼 클릭 이벤트를 정의한다.
     */
    private void initView() {
        Button signInBtn = findViewById(R.id.btn_sign_in);
        final EditText emailEdt = findViewById(R.id.et_email);
        final EditText nicknameEdt = findViewById(R.id.et_nickname);
        final EditText passwordEdt = findViewById(R.id.et_password);
        // TODO: 4/9/19 비밀번호 확인 기능 추가, 서버 API 수정 요청
        final EditText passwordConfirmationEdt = findViewById(R.id.et_password_confirm);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = emailEdt.getText().toString().trim();
                final String nickname = nicknameEdt.getText().toString().trim();
                final String password = passwordEdt.getText().toString().trim();
                final String passwordConfirmation = passwordConfirmationEdt.getText().toString().trim();

                SignUpData signUpData = new SignUpData(account, nickname, password, passwordConfirmation);

                Call<SignUpResponse> call = mSignUpRequest.signUpRequest(signUpData);
                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        Log.d("zzanzu", "onResponse: " + response.body().success);

                        if (response.body().success) {
                            Toast.makeText(getApplicationContext(), "Sign Up Success",
                                    Toast.LENGTH_LONG)
                                    .show();

                            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sign Up Fail, try later",
                                Toast.LENGTH_LONG)
                                .show();

                        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

    }
}
