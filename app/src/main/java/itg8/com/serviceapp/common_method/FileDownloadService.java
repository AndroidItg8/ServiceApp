package itg8.com.serviceapp.common_method;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.File;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.warranty.DownloadPdfFile;
import itg8.com.serviceapp.warranty.WarrantyModel;

public class FileDownloadService extends Service implements CommonMethod.DownloadFileListner {
    CommonMethod.DownloadFileListner listner;
    private WarrantyModel model;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private String fromDownloadFailed = "fromDownloadFailed";
    private String fromDownloadSuccess = "fromDownloadSuccess";
    private Intent intent;

    public FileDownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null)
            return START_NOT_STICKY;
        if (intent.hasExtra(CommonMethod.WARRANTY_MODEL)) {
            model = intent.getParcelableExtra(CommonMethod.WARRANTY_MODEL);
        }

            new DownloadPdfFile(model, this);

        return START_NOT_STICKY;
    }

    @Override
    public void onSuccessFile(WarrantyModel model) {
        Intent intent = new Intent(CommonMethod.FROM_SERVICE);
        intent.putExtra(CommonMethod.WARRANTY_MODEL, model);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

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

        hideDownloadProgress(message, fromDownloadFailed);

    }

    @Override
    public void onProgressHide() {

        hideDownloadProgress("Open File", fromDownloadSuccess);

    }

    @Override
    public void startDownload() {
        model.setProgress(true);
        Intent intent = new Intent(CommonMethod.FROM_SERVICE);
        intent.putExtra(CommonMethod.WARRANTY_MODEL, model);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        showNotification();

    }

    private void showNotification() {
        mNotifyManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentTitle("File Download")
                .setContentText("Download in progress")
                .setAutoCancel(true)
                .setOngoing(false)

                .setSmallIcon(R.mipmap.ic_launcher);
    }

    private void hideDownloadProgress(String message, String from) {
        // Removes the progress bar
        mBuilder.setProgress(0, 0, false);

        if (from.equalsIgnoreCase(fromDownloadSuccess)) {
            mBuilder.setContentText("Open PDF FILE");
            //  OpenPDFfile(model.getPath());
            OpenPDFfile("");
//              PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), )
            mBuilder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        } else {
            mBuilder.setContentText(message);
        }
        // mNotifyManager.notify(model.getInvoiceNumber(), mBuilder.build());
        mNotifyManager.notify(model.getPkid(), mBuilder.build());
    }

    public void OpenPDFfile(String filePath) {
        intent = new Intent(Intent.ACTION_VIEW);
        String newFilePath = filePath.replaceAll("%20", " ");
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(Uri.parse(newFilePath), "application/pdf");
            } else {
                Uri uri = Uri.parse(newFilePath);
                File file = new File(uri.getPath());
                if (file.exists()) {
                    uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", file);
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }
        } catch (Exception e) {
            Log.d(getApplicationContext().getClass().getName(), String.valueOf(e));
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    }


}
