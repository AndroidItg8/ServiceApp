package itg8.com.serviceapp.warranty;

import android.Manifest;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.ConnectivityReceiver;
import itg8.com.serviceapp.common_method.FileDownloadService;
import itg8.com.serviceapp.warranty.mvp.WarrantyMVP;
import itg8.com.serviceapp.warranty.mvp.WarrantyPresenterImp;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class WarrantyActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, WarrantyMVP.WarrantyView, WarrrantyAdapter.WarrantyItemClick, CommonMethod.DownloadFileListner {

    private static final String TAG = WarrantyActivity.class.getSimpleName();
    private static final int RC_STORAGE = 124;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewWarranty)
    RecyclerView recyclerViewWarranty;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.lbl_message)
    TextView lblMessage;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;
    private WarrantyMVP.WarrantyPresenter presenter;
    private Snackbar snackbar;
    private WarrrantyAdapter adapter;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private ProgressBar progress = null;
    private boolean noList = false;
    private boolean isViewVisible = false;

    private WarrantyModel model;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            model = intent.getParcelableExtra(CommonMethod.WARRANTY_MODEL);
//            hideDownloadProgress("Download Successful");
            adapter.downloadSuccessfull(model);
            adapter.notifyDataSetChanged();
            Log.d("receiver", "Got message: " + model);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.title_activity_warranty));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new WarrantyPresenterImp(this);
        presenter.onGetList(getString(R.string.url_warranty));
        checkStoragePermission();
        isViewVisible = true;
        noList = false;
        checkNoList();

        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewWarranty.setLayoutManager(linearLayoutManager);
//        List<WarrantyModel> lists = getListFileFromPath(list);
        recyclerViewWarranty.addOnScrollListener(presenter.recyclerViewScrllListener(linearLayoutManager));
        adapter = new WarrrantyAdapter(getApplicationContext(), this);
        recyclerViewWarranty.setAdapter(adapter);


    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        presenter.onGetList(getString(R.string.url_warranty));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyed();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @AfterPermissionGranted(RC_STORAGE)
    public void checkStoragePermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            //  presenter.onGetList();


        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_storage),
                    RC_STORAGE, perms);
        }
    }


    @Override
    public void onSuccess(List<WarrantyModel> list) {

        adapter.addItems(list);
    }

    @Override
    public void onDownloadPdf() {

    }


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(CommonMethod.FROM_SERVICE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    @Override
//    public void onInvoicePdf() {
//
//        showToast("");
//    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemWarrantyClicked(int position, WarrantyModel model) {
        //, WarrantyModel model
//        presenter.onDownloadInvoice(model.getInvoiceNumber());

//        new DownloadPdfFile(WarrantyActivity.this,"http://www.pdf995.com/samples/pdf.pdf");
        if (model.getInvoiceBill() != null) {
            Intent intent = new Intent(this, FileDownloadService.class);
            //model.setId(String.valueOf(position));

            model.setPkid((position));
            intent.putExtra(CommonMethod.WARRANTY_MODEL, model);
            startService(intent);
        } else {
            Toast.makeText(this, "No pdf link available.... ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onOpenPdf(int position, WarrantyModel model) {

        String filePath = model.getInvoiceBill();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String newFilePath = filePath.replaceAll("%20", " ");
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(Uri.parse(newFilePath), "application/pdf");
            } else {
                Uri uri = Uri.parse(newFilePath);
                File file = new File(uri.getPath());
                if (file.exists()) {
                    uri = FileProvider.getUriForFile(getApplicationContext(), getApplication().getPackageName() + ".fileprovider", file);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            }
        } catch (Exception e) {
            Log.d(getApplicationContext().getClass().getName(), String.valueOf(e));
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        getApplicationContext().startActivity(intent);


    }

    private void checkConnection(boolean b) {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnackbar(b);

    }

    private void showSnackbar(boolean isConnected) {

        int color;
        String message;
        if (isConnected) {

            message = "Connected to Internet";
            color = Color.WHITE;
            hideSnackbar();

        } else {
            message = " Not connected to internet...Please try again";
            color = Color.RED;
        }
        snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.show();


        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked();

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked() {
        presenter.onGetList(getString(R.string.url_warranty));
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }


    @Override
    public void onSuccessFile(WarrantyModel model) {
        // hideDownloadProgress("Download Successful");
        // adapter.fileDownload( model);
    }

    private void showNotification() {

        mNotifyManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentTitle("File Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher);
        Toast.makeText(getApplicationContext(), "Downloading the file... The download progress is on notification bar.", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProgressShow(int lenght, int total) {
        publishProgress((int) ((total * 100) / lenght));
    }

    private void publishProgress(int... i) {


        mBuilder.setProgress(100, i[0], false);
        // Displays the progress bar on notification
        mNotifyManager.notify(0, mBuilder.build());
    }


    @Override
    public void onDownloadFiled(String message) {
        //hideDownloadProgress(message);


    }

    @Override
    public void onProgressHide() {
        progressView.setVisibility(View.GONE);
        // hideDownloadProgress("Download Failed");

    }


    @Override
    public void onProgressHide(int from) {
        progressView.setVisibility(View.GONE);

    }

    @Override
    public void onProgressShow(int from) {
        progressView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onNoInternet(boolean b, int from) {
        checkConnection(b);
    }

    @Override
    public void onError(String mesg, int from) {
        showSnackbar(false);

    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {
        if (adapter == null) {
            return;
        }
        if (show) {
            adapter.addFooter();

        } else {
            adapter.removeFooter();
        }
    }

    @Override
    public void onPaginationError(boolean show, int from) {
        adapter.removeFooter();
    }

    @Override
    public void emptyList(int from) {
        noList = true;
        if (isViewVisible)
            checkNoList();
    }

    private void checkNoList() {
        if (noList) {
            showHideView(rlHide, recyclerViewWarranty);
        } else {
            showHideView(recyclerViewWarranty, rlHide);
        }
    }

    @Override
    public void startDownload() {
        showNotification();
    }

    private void hideDownloadProgress(String message) {
        mBuilder.setContentText(message);
        // Removes the progress bar
        mBuilder.setProgress(0, 0, false);
        mNotifyManager.notify(0, mBuilder.build());
    }

    private List<WarrantyModel> getListFileFromPath(List<WarrantyModel> lists) {

        String root = Environment.getExternalStorageDirectory().toString();
        Log.d(TAG, "root:" + root);

        File file = new File(root + "/Service App");
        File list[] = file.listFiles();
        List<WarrantyModel> newList = new ArrayList<>();
        List<String> fileNamesList = new ArrayList<String>(Collections.singletonList(Arrays.toString(list)));
        for (WarrantyModel model : lists) {
//        if(fileNamesList.contains(model.getPath()))
//        {
//
//            newList.add(model);
//
//        }
        }

        return newList;


    }
}
