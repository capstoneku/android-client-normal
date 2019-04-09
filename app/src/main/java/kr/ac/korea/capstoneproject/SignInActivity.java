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

        initView();
    }

    private void initView() {
        mRetrofit = getInstance();
        mSignInRequest = mRetrofit.create(SignInRequest.class);

        Button signInBtn = findViewById(R.id.btn_sign_in);
        final EditText emailEdt = findViewById(R.id.et_email);
        final EditText nicknameEdt = findViewById(R.id.et_nickname);
        final EditText passwordEdt = findViewById(R.id.et_password);
        final EditText passwordConfirmationEdt = findViewById(R.id.et_password_confirm);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = emailEdt.getText().toString().trim();
                final String nickname = nicknameEdt.getText().toString().trim();
                final String password = passwordEdt.getText().toString().trim();
                final String passwordConfirmation = passwordConfirmationEdt.getText().toString().trim();

                SignInData signInData = new SignInData(account, nickname, password, passwordConfirmation);

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
