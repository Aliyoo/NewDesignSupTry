package per.ali.prac.newdesignsuptry;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_drawer_main);

/*//        获取NavigationView的FrameLayout布局，**不能达到滑出效果**
        final FrameLayout famNvContent = (FrameLayout) findViewById(R.id.frame_nv_content);*/

//        获取DrawerLayout和直接获取NavigationView布局
        final DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.ly_drawer);
        final NavigationView nvContent = (NavigationView) findViewById(R.id.nv_main);
        //        动态添加Item和SubMenu item
//        final SubMenu nvSubMenu  = (SubMenu) findViewById(R.id.nv_item_with_sub);
//        nvSubMenu.add(R.string.nv_sub_item_run);
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

//        利用Toolbar添加DrawerToggle
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar)

//        Tab布局
        TabLayout tab_desgin = (TabLayout) findViewById(R.id.tab_design);
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab One"));
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab Two"));
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab Three"));

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

        EditText etPass = (EditText) findViewById(R.id.et_pass);

        TextInputLayout etEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        下面前两个属性设置后不会自动聚焦
        etEmail.setFocusable(true);
        etEmail.setFocusableInTouchMode(true);
        etEmail.setErrorEnabled(true);
        etEmail.setError("Don't input null content");

    }
}
