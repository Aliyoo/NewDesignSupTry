package per.ali.prac.newdesignsuptry;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //        获取DrawerLayout和直接获取NavigationView布局
    DrawerLayout mDrawer;
    NavigationView nvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_with_main);

        setupToobBar();
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

/*//        获取NavigationView的FrameLayout布局，**不能达到滑出效果**
        final FrameLayout famNvContent = (FrameLayout) findViewById(R.id.frame_nv_content);*/

/*//        动态添加Item和SubMenu item
        final SubMenu nvSubMenu  = (SubMenu) findViewById(R.id.nv_item_with_sub);
        nvSubMenu.add(R.string.nv_sub_item_run);*/

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
    }

    /**
     * 初始化主页面
     */
    private void initMainPage() {

//        获取TextInputLayout
        TextInputLayout etEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        下面前两个属性设置后不会自动聚焦
        etEmail.setFocusable(true);
        etEmail.setFocusableInTouchMode(true);
        etEmail.setErrorEnabled(true);
        etEmail.setError("Don't input null content");
//        Tab布局
        TabLayout tab_design = (TabLayout) findViewById(R.id.tab_design);
        tab_design.addTab(tab_design.newTab().setText("Tab One"));
        tab_design.addTab(tab_design.newTab().setText("Tab Two"));
        tab_design.addTab(tab_design.newTab().setText("Tab Three"));

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

    protected void setupToobBar() {
//        利用Toolbar添加DrawerToggle
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
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
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings : break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
