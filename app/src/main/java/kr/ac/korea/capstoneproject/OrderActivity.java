package kr.ac.korea.capstoneproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.fragment.CafePageFragment;

public class OrderActivity extends AppCompatActivity {

    Intent intent;
    String name;
    int price;
    int crnt_price;
    int crnt_ctn;
    String image;
    TextView itemName;
    TextView itemPrice;
    ImageView itemImage;


    Switch switch_hi;
    Button btn_plus;
    Button btn_minus;
    int item_ctn = 1;
    TextView tv_count;
    Button btn_size;
    Button btn_options1;
    Button btn_options2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getIntExtra("price", 0);
        image = intent.getStringExtra("image");

        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemImage = findViewById(R.id.item_image);

        itemName.setText(name);
        itemPrice.setText(String.valueOf(price));
        Glide.with(this).load(image).into(itemImage);


        switch_hi = findViewById(R.id.switch_hi);

        btn_plus = findViewById(R.id.button_plus);
        btn_minus = findViewById(R.id.button_minus);
        tv_count = findViewById(R.id.count);

        btn_size = findViewById(R.id.item_size);
        btn_options1 = findViewById(R.id.item_options1);
        btn_options2 = findViewById(R.id.item_options2);


        switch_hi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    crnt_price = Integer.parseInt((String) itemPrice.getText());
                    crnt_ctn = Integer.parseInt((String) tv_count.getText());
                    itemPrice.setText(String.valueOf(crnt_price + 500*crnt_ctn));
                }
                else {
                    crnt_price = Integer.parseInt((String) itemPrice.getText());
                    crnt_ctn = Integer.parseInt((String) tv_count.getText());
                    itemPrice.setText(String.valueOf(crnt_price - 500*crnt_ctn));
                }

            }
        });


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_ctn++;
                tv_count.setText(String.valueOf(item_ctn));
                itemPrice.setText(String.valueOf(price*item_ctn));
            }
        });


        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item_ctn==1){
                    Toast.makeText(getApplicationContext(), "최소 수량은 1개 입니다.", Toast.LENGTH_SHORT).show();
                    tv_count.setText(String.valueOf(item_ctn));
                    itemPrice.setText(String.valueOf(price*item_ctn));
                }
                else {
                    item_ctn--;
                    tv_count.setText(String.valueOf(item_ctn));
                    itemPrice.setText(String.valueOf(price*item_ctn));
                }
            }
        });



        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.menu_size, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.small:
                                btn_size.setText("S");
                                break;
                            case R.id.medium:
                                btn_size.setText("M");
                                break;
                            case R.id.large:
                                btn_size.setText("L");
                                break;
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });



        btn_options1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.menu_options, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.no_option:
                                btn_options1.setText("없음");
                                break;
                            case R.id.less_sweet:
                                btn_options1.setText("덜 달게");
                                break;
                            case R.id.more_sweet:
                                btn_options1.setText("더 달게");
                                break;
                            case R.id.less_ice:
                                btn_options1.setText("얼음 적게");
                                break;
                            case R.id.more_ice:
                                btn_options1.setText("얼음 많이");
                                break;
                            case R.id.more_milk:
                                btn_options1.setText("우유 추가");
                                break;
                            case R.id.more_shot:
                                btn_options1.setText("샷 추가");
                                break;
                            case R.id.etc:
                                btn_options1.setText("기타");
                                break;
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });


        btn_options2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.menu_options, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.no_option:
                                btn_options2.setText("없음");
                                break;
                            case R.id.less_sweet:
                                btn_options2.setText("덜 달게");
                                break;
                            case R.id.more_sweet:
                                btn_options2.setText("더 달게");
                                break;
                            case R.id.less_ice:
                                btn_options2.setText("얼음 적게");
                                break;
                            case R.id.more_ice:
                                btn_options2.setText("얼음 많이");
                                break;
                            case R.id.more_milk:
                                btn_options2.setText("우유 추가");
                                break;
                            case R.id.more_shot:
                                btn_options2.setText("샷 추가");
                                break;
                            case R.id.etc:
                                btn_options2.setText("기타");
                                break;
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });


    }
}