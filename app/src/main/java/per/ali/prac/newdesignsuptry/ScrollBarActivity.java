package per.ali.prac.newdesignsuptry;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ScrollBarActivity extends AppCompatActivity {
    //    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        actionBar.setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        创建默认的线性布局管理器
        LinearLayoutManager linearViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearViewManager);
//        设置item高度固定
        recyclerView.setHasFixedSize(true);

        RecyclerView.Adapter recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setClickable(true);

//       不会添加Item

//        RecyclerView.Adapter recyclerAdapter = new RecyclerView.Adapter() {
//
//            public static class ViewHolder extends RecyclerView.ViewHolder {
//                // each data item is just a string in this case
//                public TextView mTextView;
//                public ViewHolder(TextView v) {
//                    super(v);
//                    mTextView = v;
//                }
//            }
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.item_recycler, parent, false);
//                ViewHolder vh = new ViewHolder(view);
//                return vh;
//            }
//
//            @Override
//            public void onBindViewHolder(ViewHolder holder, int position) {
//            }
//
//            @Override
//            public int getItemCount() {
//                return 10;
//            }
//
//        };
//        recyclerView.setAdapter(recyclerAdapter);
    }
}
