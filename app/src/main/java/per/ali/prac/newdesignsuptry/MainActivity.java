package per.ali.prac.newdesignsuptry;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    String Tag = "Tab";

//     获取DrawerLayout和直接获取NavigationView布局
    DrawerLayout mDrawer;
    NavigationView nvContent;
    ActionBarDrawerToggle mDrawerToggle;

//    主页面控件
    TextInputLayout etEmail;
    TabLayout tabDesign;
//    private View rootView;
    ViewPager viewPager;
    ArrayList<View> viewPagerList = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_with_drawer);
        ButterKnife.bind(this);

        setupToolbar();
        initNavigationDrawer();
        initMainPage();

    }

    /**
     * 初始化抽屉
     */
    private void initNavigationDrawer() {
//        获取DrawerLayout和直接获取NavigationView布局
        mDrawer = (DrawerLayout) findViewById(R.id.ly_drawer);
        nvContent = (NavigationView) findViewById(R.id.nv_main);

//        在NavigationView的Menu中动态添加Item
        final Menu nvMenu = nvContent.getMenu();
        nvMenu.add(R.string.nv_item_run);
//        nvMenu.getItem(3).setCheckable(true);
        nvContent.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawer.closeDrawers();
                        return true;
                    }
                });
        setupToolbarDrawerToggle();
    }

    /**
     * 初始化主页面
     */
    private void initMainPage() {

//        获取TextInputLayout
        etEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        下面前两个属性设置后不会自动聚焦
        etEmail.setFocusable(true);
        etEmail.setFocusableInTouchMode(true);
//        设置错误提示
        etEmail.setErrorEnabled(true);
        etEmail.setError("Don't input null content");

//        建立Tab界面
        setupTabView();
//        建立浮动按钮
        setupFloatActionButton();
    }

    /**
     * 设置TabLayout界面
     */
    private void setupTabView() {
//        ViewPager
        viewPager = (ViewPager) this.findViewById(R.id.view_pager);
//        布局过滤器
        LayoutInflater mLayoutInflater = getLayoutInflater();
        viewPagerList.add(0, mLayoutInflater.inflate(R.layout.viewpager_red, null));
        viewPagerList.add(1, mLayoutInflater.inflate(R.layout.viewpager_green, null));
        viewPagerList.add(2, mLayoutInflater.inflate(R.layout.viewpager_blue, null));

        titleList.add(0, "Tab Red");
        titleList.add(1, "Tab Green");
        titleList.add(2, "Tab Blue");

        viewPager.setAdapter(new PagerAdapter() {
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
        });
//        Tab布局
//        ***关联上Viewpager后Tab标签显示不出来***
        tabDesign = (TabLayout) findViewById(R.id.tab_design);
/*        tab_design.addTab(tab_design.newTab().setText("Tab Red"));
        Log.d(Tag, "Tab Red adding...");
        tab_design.addTab(tab_design.newTab().setText("Tab Green"));
        tab_design.addTab(tab_design.newTab().setText("Tab Blue"));*/

//        Tab关联上ViewPager
        tabDesign.setupWithViewPager(viewPager);
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

    }

    /**
     * 设置抽屉开关键纽
     */
    protected void setupToolbarDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
//                Snackbar.make(view, R.string.drawer_close, Snackbar.LENGTH_SHORT).show();
            }

            public void onDrawerOpened(View view) {
                Snackbar.make(view, R.string.drawer_open, Snackbar.LENGTH_SHORT).show();
            }
        };
        mDrawer.setDrawerListener(mDrawerToggle);
    }

    /**
     * 重写返回键响应，先关闭抽屉再退出。
     */
    @Override
    public void onBackPressed() {
        if (isDrawerOpened()) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //    判断抽屉是否打开
    protected boolean isDrawerOpened() {
        return mDrawer != null && mDrawer.isDrawerOpen(GravityCompat.START);
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
                mDrawer.openDrawer(GravityCompat.START);
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
