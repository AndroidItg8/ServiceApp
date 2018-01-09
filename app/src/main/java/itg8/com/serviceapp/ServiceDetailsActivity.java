package itg8.com.serviceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.common_method.CommonMethod;

public class ServiceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setRecyclerView();


    }


    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (getIntent().hasExtra(CommonMethod.FROM_PURCHASE)) {
            toolbar.setTitle("My Account");
            setSupportActionBar(toolbar);


            recyclerView.setAdapter(new ServiceDetailsAdapter(getApplicationContext(), CommonMethod.FROM_PURCHASE));
        } else if (getIntent().hasExtra(CommonMethod.FROM_SERVICE)) {
            toolbar.setTitle("Warranty");
            setSupportActionBar(toolbar);

            recyclerView.setAdapter(new ServiceDetailsAdapter(getApplicationContext(), CommonMethod.FROM_SERVICE));


        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
