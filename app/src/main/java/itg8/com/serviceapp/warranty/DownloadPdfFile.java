package itg8.com.serviceapp.warranty;


import android.app.Notification;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import itg8.com.serviceapp.common_method.CommonMethod;

/**
 * Created by Android itg 8 on 10/17/2017.
 */

public class DownloadPdfFile {

    CommonMethod.DownloadFileListner listner;

    private static final String TAG = "Download Task";

    private String downloadUrl = "";
    private String downloadFileName = "";
    private WarrantyModel model;



    public DownloadPdfFile( WarrantyModel model, CommonMethod.DownloadFileListner listner) {
        this.downloadUrl = CommonMethod.BASE_URL+model.getInvoiceBill();
        downloadFileName = downloadUrl.substring(downloadUrl.lastIndexOf('/'), downloadUrl.length());//Create file name by picking download file name from URL
        this.model = model;
        this.listner = listner;
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingTask().execute();
    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
             listner.startDownload();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {

                 //   Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();

                  // model.setPath(outputFile.getAbsolutePath());
                    model.setProgress(false);
                    model.setDownload(true);
                    listner.onSuccessFile(model);
                    listner.onProgressHide();

                }

            } catch (Exception e) {
                e.printStackTrace();

                listner.onProgressHide();
                listner.onDownloadFiled(e.getMessage());
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection


                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }


                //Get File if SD card is present
                if (CommonMethod.isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/"
                                    +"Service App");
                }
                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                int total = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    int lenght =  c.getContentLength();
                    total += len1;
                    // publishing the progress
//                    publishProgress((int)((total*100)/lenght));
                    Log.d(TAG,"lenght:"+lenght+" "+ "total "+total);
                     listner.onProgressShow(lenght, total);
                    fos.write(buffer, 0, len1);//Write new file
                }


                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
                listner.onProgressHide();

                listner.onDownloadFiled(e.getMessage());

            }

            return null;
        }


    }


}

