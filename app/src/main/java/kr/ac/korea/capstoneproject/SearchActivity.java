package kr.ac.korea.capstoneproject;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    ListView listview = null;
    private TextView mTextMessage;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
////            = new BottomNavigationView.OnNavigationItemSelectedListener() {
////
////        @Override
////        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////            switch (item.getItemId()) {
////                case R.id.navigation_home:
////                    mTextMessage.setText(R.string.title_home);
////                    return true;
////                case R.id.navigation_dashboard:
////                    mTextMessage.setText(R.string.title_dashboard);
////                    return true;
////                case R.id.navigation_notifications:
////                    mTextMessage.setText(R.string.title_notifications);
////                    return true;
////            }
////            return false;
////        }
////    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewAdapter adapter;
        adapter = new ListViewAdapter();

        mTextMessage = (TextView) findViewById(R.id.message);
        listview = (ListView) findViewById(R.id.listView_search);
        listview.setAdapter(adapter);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.mmth),
                "매머드커피", "★4.5 리뷰 32개","대표메뉴: 꿀라떼, 아몬드라떼");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.gram),
                "14gram커피", "★4.3 리뷰 28개","대표메뉴: 녹차라떼, 카페모카");

        //EditText 텍스트 변경 이벤트 처리
        //EditText(@id/searchCafe)를 통해 필터링할 텍스트 입력받은 다음, 필터링 수행

        EditText editTextFilter = (EditText)findViewById(R.id.searchCafe) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((ListViewAdapter)listview.getAdapter()).getFilter().filter(filterText) ;
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;
    }

}
