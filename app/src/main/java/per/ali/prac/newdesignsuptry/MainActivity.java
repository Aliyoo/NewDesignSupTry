package per.ali.prac.newdesignsuptry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tab";

    //      主界面布局
    private DrawerLayout mDrawerLayout;
    private NavigationView navContent;

    //    主页面控件
    private TextInputLayout textInputLayoutEmail;
    private TabLayout tabDesign;

//    数据集合
    private ArrayList<View> viewPagerList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_drawer);
        ButterKnife.bind(this);

        initNavigationDrawer();
        initMainPage();

    }

    /**
     * 初始化侧滑抽屉
     */
    private void initNavigationDrawer() {
//        获取DrawerLayout和直接获取NavigationView布局
        mDrawerLayout = (DrawerLayout) findViewById(R.id.ly_drawer);
        navContent = (NavigationView) findViewById(R.id.nv_main);

//        在NavigationView的Menu中动态添加Item
        final Menu nvMenu = navContent.getMenu();
        nvMenu.add(R.string.nav_item_run);
//        nvMenu.getItem(3).setCheckable(true);

//        设置导航菜单的项目点击事件
        navContent.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
//                        while (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_item_1:
                                break;
                            case R.id.nav_item_2:
                                startActivity(new Intent(MainActivity.this, ScrollBarActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    /**
     * 初始化主页面
     */
    private void initMainPage() {

//        获取TextInputLayout
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        下面前两个属性设置后,启动时不会自动聚焦
        textInputLayoutEmail.setFocusable(true);
        textInputLayoutEmail.setFocusableInTouchMode(true);
        textInputLayoutEmail.setErrorEnabled(true);
//        设置空白输入监听并提示
        final EditText etEmail = textInputLayoutEmail.getEditText();
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    textInputLayoutEmail.setError("e-mail can't be null");
                } else {
                    textInputLayoutEmail.setError(null);
                }
            }
        });

//        建立Tab界面
        setupTabView();
//        建立工具栏
        setupToolbar();
//        建立浮动按钮
        setupFloatActionButton();
    }

    /**
     * 设置TabLayout界面
     */
    private void setupTabView() {
//        ViewPager
        ViewPager mViewPager = (ViewPager) this.findViewById(R.id.view_pager);
//        布局过滤器
        LayoutInflater mLayoutInflater = getLayoutInflater();
        viewPagerList.add(0, mLayoutInflater.inflate(R.layout.viewpager_red, null));
        viewPagerList.add(1, mLayoutInflater.inflate(R.layout.viewpager_green, null));
        viewPagerList.add(2, mLayoutInflater.inflate(R.layout.viewpager_blue, null));

        titleList.add(0, "Tab Red");
        titleList.add(1, "Tab Green");
        titleList.add(2, "Tab Blue");

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                System.out.println("在GetCount...");
                return viewPagerList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            //            生成布局项目
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View currView = viewPagerList.get(position);
                container.addView(currView);
                return currView;
            }

            //            销毁当前布局项目
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View currView = viewPagerList.get(position);
                container.removeView(currView);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
            }
        });
//        Tab布局
//        ***关联上Viewpager后Tab标签显示不出来***
        tabDesign = (TabLayout) findViewById(R.id.tab_design);
        tabDesign.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            设置不同Tab选择的颜色，TabLayout中设置不能生效，需要在Viewpager里设置
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabDesign.setTabTextColors(
                                R.color.abc_background_cache_hint_selector_material_dark,
                                R.color.bg_material_red);
                    case 1:
                        tabDesign.setTabTextColors(
                                R.color.abc_background_cache_hint_selector_material_dark,
                                R.color.bg_material_green);
                    case 2:
                        tabDesign.setTabTextColors(
                                R.color.abc_background_cache_hint_selector_material_dark,
                                R.color.bg_material_blue);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
/*        tab_design.addTab(tab_design.newTab().setText("Tab Red"));
        Log.d(TAG, "Tab Red adding...");
        tab_design.addTab(tab_design.newTab().setText("Tab Green"));
        tab_design.addTab(tab_design.newTab().setText("Tab Blue"));*/

//        Tab关联上ViewPager
        tabDesign.setupWithViewPager(mViewPager);
    }

    /**
     * 设置FAB（浮动按钮）
     */
    protected void setupFloatActionButton() {
        //        创建并得到FloatingActionButton
        final FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.fab_normal);
        final CoordinatorLayout mCoordinator;
        mCoordinator = (CoordinatorLayout) findViewById(R.id.content_coordinator);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mCoordinator, "Activate a Snackbar ^_^", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    /**
     * 设置Toolbar代替ActionBar
     */
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

//     设置抽屉开关键纽（Toggle）
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
//                Snackbar.make(view, R.string.drawer_close, Snackbar.LENGTH_SHORT).show();
            }

            public void onDrawerOpened(View view) {
                Snackbar.make(view, R.string.drawer_open, Snackbar.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    /**
     * 重写返回键响应，先关闭抽屉再退出。
     */
    @Override
    public void onBackPressed() {
        if (isDrawerOpened()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //    判断抽屉是否打开
    protected boolean isDrawerOpened() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    //    基本菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        设置菜单按钮动作
        switch (item.getItemId()) {
            case android.R.id.home:     //  ‘home’ID表示Toggle
                mDrawerLayout.openDrawer(GravityCompat.START);
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
