package kr.ac.korea.capstoneproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kr.ac.korea.capstoneproject.fragment.CouponFragment;
import kr.ac.korea.capstoneproject.fragment.HistoryFragment;
import kr.ac.korea.capstoneproject.fragment.HomeFragment;
import kr.ac.korea.capstoneproject.fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 메인 액티비티의 뷰들을 초기화한다.
     * 1) 상단 툴바
     * 2) 바텀 네비게이션 바
     * 3) 홈 프래그먼트 표시
     *
     * @author Chanjoo Lee
     * @version 0.1
     */
    private void initView() {
        initToolBar();
        initBottomNavBar();

        // 최초 실행시 홈 프래그먼트를 기본으로 표
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main, new SearchFragment()).commit();
    }

    /**
     * 메인액티비티의 상단 툴바를 초기화한다.
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

    /**
     * 상단 툴바의 버튼 클릭 이벤트를 정의한다.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                // TODO: 2019-05-08 바텀 네비게이션 버튼들의 아이콘, 텍스트 색을 unfocusd 색으로 변경.
                replaceFragment(new HomeFragment());
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 바텀 네비게이션 바를 초기화한다.
     * 네비게이션 바의 메뉴들의 클릭 이벤트도 정의한다.
     *
     * @author Chanjoo Lee
     * @version 0.1
     */
    private void initBottomNavBar() {
        mBottomNavigationView = findViewById(R.id.nav_view);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    /**
     * 메인 액티비티에 표시되는 프래그먼트를 교체한다.
     * 프래그먼트 교체시 이전에 표시되었던 프래그먼트의 데이터는 유지되지 않는다(addToBackStack(null))
     *
     * @param fragment 메인 액티비티에 표시하려는 프래그먼트
     * @author Chanjoo Lee
     * @version 0.1
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_main, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}
