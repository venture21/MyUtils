package com.venture.android.myutils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.venture.android.myutils.dummy.DummyContent;

import java.util.Stack;

/*
 * GPS 사용순서
 * 1. manifest에 FINE, COARSE 권한 추가
 * 2. runtime permission 소스코드에 추가
 * 3. GPS Location Manager 정의
 * 4. GPS가 켜져있는지 확인.  꺼져 있다면 GPS 화면으로 이동
 *
 */

public class MainActivity extends AppCompatActivity implements FiveFragment.OnListFragmentInteractionListener{

    final int TAB_COUNT = 5;
    OneFragment one;
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;
    FiveFragment five;

    boolean backpress=false;

    private int page_position=0;
    ViewPager viewPager;

    Stack<Integer> pageStack = new Stack<>();


    // GPS 위치 정보 관리자
    private LocationManager manager;
    public LocationManager getLocationManager() {
        return manager;
    }

    private final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메써드 추적 시작 ------
        Debug.startMethodTracing("tresult");
        // 프레그먼트 init
        one   = new OneFragment();
        two   = new TwoFragment();
        three = new ThreeFragment();
        four  = new FourFragment();
        five  = FiveFragment.newInstance(2); //미리 정해진 그리드 갯수

        // 탭 Layoyt 정의
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        // 탭 생성 및 타이틀 입력
        tabLayout.addTab(tabLayout.newTab().setText("계산기"));
        tabLayout.addTab(tabLayout.newTab().setText("단위환산"));
        tabLayout.addTab(tabLayout.newTab().setText("검색"));
        tabLayout.addTab(tabLayout.newTab().setText("현재위치"));
        tabLayout.addTab(tabLayout.newTab().setText("갤러리"));

        // 프레그먼트 페이저 작성
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // 아답터 생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // 페이저가 변경되었을 때 탭을 바꿔주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(!backpress) {

                    pageStack.push(page_position);
                } else {
                    backpress = false;
                }

                page_position = position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 탭이 변경되었을 때 페이지를 바꿔주는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        // 버전체크를 통해 마시멜로보다 낮으면 런타임권한 체크를 하지 않는다.
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            init();
        }
        // 메써드 추적 종료
        Debug.stopMethodTracing();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0: fragment = one;     break;
                case 1: fragment = two;     break;
                case 2: fragment = three;   break;
                case 3: fragment = four;    break;
                case 4: fragment = five;    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        // 1.1 런타임 권한체크
        if( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            // 1.2 요청할 권한 목록 작성
            String permArr[] = {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET};
            // 1.3 시스템에 권한요청
            requestPermissions(permArr, REQ_CODE);
        }
        else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQ_CODE){
            // 배열에 넘긴 런타임권한을 체크해서 승인이 됐으면 프로그램 실행.
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    ){
                init();
            } else {
                Toast.makeText(this,"권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }



    private void init(){
        /**
         *  GPS가 꺼져 있을 경우, GPS를 켜기 위한 팝업창 생성
         */
        manager = (LocationManager)  getSystemService(Context.LOCATION_SERVICE);
        // GPS가 꺼져있다면...
        if(!gpsCheck()) {
            // 팝업창 만들기
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            // 1. 팝업창 제목
            alertDialog.setTitle("GPS켜기");
            // 2. 팝업창 메시지
            alertDialog.setMessage("GPS가 꺼져 있습니다. \n설정창으로 이동하시겠습니까?");
            // 3. YES 버튼 생성
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            // 4. NO 버튼 생성.
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            // 5. show 함수로 팝업창을 화면에 띄운다.
            alertDialog.show();
        }
    }

    // GPS가 꺼져있는지 체크 롤리팝 이하버전
    private boolean gpsCheck() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            String gps = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if(gps.matches(",*gps.*")){
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        switch(page_position){
            // webview 페이지에서
            case 2:
                // 뒤로 가기가 가능하면 아무런 동작을 하지 않는다.
                if(three.goBack()){
                // 뒤로 가기가 안되면 앱을 닫는다.
                } else {
                    goBackStack();
                }
                break;
            // 위의 조건에 해당되지 않는 모든 케이스는 아래 로직을 처리한다.
            default:
                goBackStack();
                break;
        }
    }

    private void goBackStack() {
        if(pageStack.size() < 1) {
            super.onBackPressed();
        } else {
            backpress = true;
            viewPager.setCurrentItem(pageStack.pop());
        }
    }

}
