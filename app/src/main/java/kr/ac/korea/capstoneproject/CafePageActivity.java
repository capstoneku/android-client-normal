package kr.ac.korea.capstoneproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.ac.korea.capstoneproject.data.remote.CafePageRequest;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class CafePageActivity extends AppCompatActivity implements View.OnClickListener {
    private Retrofit mRetrofit;
    private CafePageRequest mCafePageRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_page);

        initConnection();
        initView();

        Button button =(Button) findViewById(R.id.button_order);
        button.setOnClickListener(this);
    }


    /**
     * 네트워크 관련 객체 초기화 함수
     */
    private void initConnection() {
        mRetrofit = getInstance();
        mCafePageRequest = mRetrofit.create(CafePageRequest.class);
    }


    private void initView() {
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}

