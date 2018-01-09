package itg8.com.serviceapp.showcase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.widget.AutoScrollViewPager;

public class ShowCaseDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
//    @BindView(R.id.viewPager)
//    AutoScrollViewPager viewPager;

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.lbl_productName)
    TextView lblProductName;
    @BindView(R.id.lbl_productName_value)
    TextView lblProductNameValue;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Product Name");

//        bottomSheet = coordinator.findViewById(R.id.rl_bottom_parent);
//        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                Log.d("onStateChanged", "onStateChanged:" + newState);
//                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//
//
//                    Log.d("onStateChanged", "appBar.getHeight:" + appBar.getHeight());
//
//                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//
//                    Log.d("onStateChanged", "onStateChanged Collapsed:" + newState);
//
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                // React to dragging events
//                Log.d("slideOffset", "slideOffset" + slideOffset);
//            }
//        });

        init();


    }

    private void init() {
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_case_details, menu);
        return true;
    }
}
