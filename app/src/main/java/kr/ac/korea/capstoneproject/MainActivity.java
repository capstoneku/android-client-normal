package kr.ac.korea.capstoneproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kr.ac.korea.capstoneproject.fragment.CouponFragment;
import kr.ac.korea.capstoneproject.fragment.HistoryFragment;
import kr.ac.korea.capstoneproject.fragment.HomeFragment;
import kr.ac.korea.capstoneproject.fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initToolBar();
        initBottomNavBar();

        // 최초 실행시 홈 프래그먼트를 기본으로 표
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main, new HomeFragment()).commit();
    }

    /**
     * 상단 툴바 초기화
     *
     * @author Chanjoo Lee
     * @version 0.1
     */
    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
    }

    private void initBottomNavBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_my_page:
                        replaceFragment(new MyPageFragment());
                        return true;
                    case R.id.navigation_history:
                        replaceFragment(new HistoryFragment());
                        return true;
                    case R.id.navigation_coupon:
                        replaceFragment(new CouponFragment());
                        return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
