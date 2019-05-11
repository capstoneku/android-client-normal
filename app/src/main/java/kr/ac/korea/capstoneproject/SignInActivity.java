package kr.ac.korea.capstoneproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kr.ac.korea.capstoneproject.data.pojo.SignInData;
import kr.ac.korea.capstoneproject.data.pojo.SignInResponse;
import kr.ac.korea.capstoneproject.data.remote.SignInRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class SignInActivity extends AppCompatActivity {
    private Retrofit mRetrofit;
    private SignInRequest mSignInRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initConnection();
        initView();
    }

    /**
     * 네트워크 관련 객체 초기화 함수
     */
    private void initConnection() {
        mRetrofit = getInstance();
        mSignInRequest = mRetrofit.create(SignInRequest.class);
    }

    /**
     * 뷰 객체 초기화 함수
     * 1개의 Button과 4개의 EditText를 초기화한다.
     * 로그인 버튼 클릭 이벤트를 정의한다.
     * 회원가입 텍스트 클릭 이벤트를 정의한다.
     */
    private void initView() {
        Button signInBtn = findViewById(R.id.btn_sign_in);
        TextView signUpTv = findViewById(R.id.tv_sign_up);
        final EditText emailEdt = findViewById(R.id.et_email);
        final EditText passwordEdt = findViewById(R.id.et_password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = emailEdt.getText().toString().trim();
                final String password = passwordEdt.getText().toString().trim();

                SignInData signInData = new SignInData(account, password);

                Call<SignInResponse> call = mSignInRequest.signInRequest(signInData);
                call.enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        Log.d("zzanzu", "onResponse: " + response.body().success);

                        if(response.body().success) {
                            String JSONWebToken = response.body().data.getToken();
                            saveToken("JSONWebToken", JSONWebToken);

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        Log.d("zzanzu", "onFailure: " + t.toString());
                    }
                });

            }
        });

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 기기 내부 SharePreference에 토큰을 저장한다
     *
     * TODO 2019-05-11 : Utils 파일 만들어서 public한 함수로 만들기(FCM 토큰 저장하는 기능과 중복됨)
     *
     * @param tokenName : 토큰 이름
     * @param token : 토큰 값
     */
    private void saveToken(String tokenName, String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tokenName, token);
        editor.apply();
    }
}
