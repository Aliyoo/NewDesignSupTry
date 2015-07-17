package per.ali.prac.newdesignsuptry;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_drawer_main);

//        抽屉
        DrawerLayout drawerNewDesign = (DrawerLayout) findViewById(R.id.ly_drawer);


        final FrameLayout famNvContent = (FrameLayout) findViewById(R.id.frame_nv_content);
//        Tab布局
        TabLayout tab_desgin = (TabLayout) findViewById(R.id.tab_design);
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab One"));
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab Two"));
        tab_desgin.addTab(tab_desgin.newTab().setText("Tab Three"));

        final FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.fab_normal);
        final CoordinatorLayout mCoordinator;
        mCoordinator = (CoordinatorLayout) findViewById(R.id.content_coordinator);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famNvContent.setVisibility(View.VISIBLE);
//                famNvContent.setAnimation(new Ani);
                Snackbar.make(mCoordinator, "Snackbar test ^_^",Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        EditText etPass = (EditText) findViewById(R.id.et_pass);

        TextInputLayout etEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        下面两个属性设置后不会自动聚焦
        etEmail.setFocusable(true);
        etEmail.setFocusableInTouchMode(true);
        etEmail.setErrorEnabled(true);
        etEmail.setError("Don't input null content");
        TextInputLayout etName = (TextInputLayout) findViewById(R.id.input_layout_name);

    }
}
