package kr.ac.korea.capstoneproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private static final int DELAY_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        moveToMainActivity(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    /**
     * 일정 시간 이후 메인 액티비티를 화면에 띄운다.
     *
     * @param context
     * @author Chanjoo Lee
     * @version 0.1
     */
    private void moveToMainActivity(final Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        Handler handler = new Handler();

        if(sharedPreferences.getString("jwt", "none") != "none") {
            handler.postDelayed((new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }), DELAY_TIME);
        } else {
            handler.postDelayed((new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }), DELAY_TIME);
        }


    }
}
