package kr.ac.korea.capstoneproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
     * 회원가입 버튼 클릭 이벤트를 정의한다.
     */
    private void initView() {
        Button signInBtn = findViewById(R.id.btn_sign_in);
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
                        Log.d("zzanzu", "onResponse: " + response.body().message);

                        if(response.body().success == true) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {

                    }
                });

            }
        });


    }
}
