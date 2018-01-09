package itg8.com.serviceapp.common_method;

import android.content.Context;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.login.LoginActivity;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import itg8.com.serviceapp.warranty.WarrantyActivity;
import itg8.com.serviceapp.warranty.WarrantyModel;


/**
 * Created by Android itg 8 on 10/7/2017.
 */

public class CommonMethod {

    public static final String SHARED = "SHARED";
//    public static final String BASE_URL = "http://192.168.1.54:8080";
    public static final String BASE_URL ="http://itechservices.itechgalaxyprojects.com";

    public static final String FROM_PURCHASE = "FROM_PURCHASE";
    public static final String FROM_SERVICE = "FROM_SERVICE";
    public static final String FROM_TICKET = "FROM_TICKET";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String HEADER = "QsSBetVvJt2ld9vLLL067EwLO4KowfVefUPHtwWGi1amq2pl9ByH2A-hJG_jFe4ENMq3QJWyNJnkZRXzijEDz6YnuIZG1Qg0PgOM2oEEOi9zHWxHqlwdQaIHkBAh0_8OYfNyUnewowRNVMziYFNYBX7GJgB7baQVRNeKwX_wB-AHxBAWMQ1Biw4XnpRic9WFWx5IjvUYfNuKr8N-9QzkQZz3Oetv4md5RjEVtE-Qd-1FfuEy1oPz7bqYqX9AFYMiuWw-U4Bpecja1oShIFRXQba7CfUm0i2ax8ZJVfLIHc8RoPVxsov9RboQEYPObmE7kGQTbiscE-MZ1C2ZblFpeCFQZLOeL7EC3nxLf_h9t3egB0D9U-HTH33vmecBHfl6c2pKgttotWfreze6JWST_375p9fKeBdvHv5UeoO6nYSChri4nzRkql9Ae5LL2ylvJrfMV8AfjT1ZTuJONVCIWo1M45WLZos1K6M7e6qOJzwWo6hXvXdpd6CFeLXIU9fWEgUR-lFy4rOCGzZGY5gNyQ";
    public static final java.lang.String DATE_FORMAT = "dd/mm/yyyy";
    public static final String PRODUCTLIST = "PRODUCTLIST";
    public static final String USERNAME = "USER_NAME";
    public static final String SHOWCASE = "SHOWCASE";
    public static final String STATUS_OPEN = "0";
    public static final String STATUS_ASSIGN = "1";
    public static final String STATUS_CLOSE = "2";
    public static final String STATUS_ALL = "10";


public static final int TICKET_STATUS_OPEN = 0;
    public static final int TICKET_STATUS_ASSIGN = 1;
    public static final int TICKET_STATUS_CLOSE = 2;
    public static final int TICKET_STATUS_ALL = 10;

    public static final String TICKET_CLOSE_FEEDBACK = "TICKET_CLOSE_FEEDBACK";
    public static final String WARRANTY_MODEL = "WARRANTY_MODEL";
    public static final String FILE = "FILE";
    public static final String FROM_LOGIN = "FROM_LOGIN";
    public static final String FROM_NOTIFICATION = "FROM_NOTIFICATION";
    public static final String FROM_ERROR = "FROM_ERROR";
    public static String FROM_HomeActivity="FROM_HomeActivity";
    private static Typeface typeface;
    private Context context;

    public CommonMethod(Context context) {
        this.context = context;
    }

    public static Typeface setFontRobotoRegular(Context context) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/robotoregular.ttf");
        return typeface;
    } public static Typeface setFontRobotoMedium(Context context) {
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        return typeface;
    }



    public static Calendar ConvertStringToDate(String assignDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
        Calendar date = Calendar.getInstance();
        try{
            date.setTime(formatter.parse(assignDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String finalString = formatter.format(date);
        return date;
    }

    public static String DiffBetweenDates(String assignDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Calendar.getInstance().getTime();
        Date date = null;
        try {
            date = (Date)formatter.parse(assignDate);
            String CurrentDate = formatter.format(Calendar.getInstance().getTime());
            date = (Date)formatter.parse(CurrentDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String finalString = formatter.format(date);
        return finalString;
    }

    public static void showHideItem(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
    }


    public interface FragmentAttachListner{
        void onAttachListner(Context mContext, String fragmentName);
    }


//    public void downloadFile(){
//        String DownloadUrl = "Paste Url to download a pdf file here…";
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadUrl));
//        request.setDescription("sample pdf file for testing");   //appears the same in Notification bar while downloading
//        request.setTitle("Sample.pdf");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            request.allowScanningByMediaScanner();
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        }
//        request.setDestinationInExternalFilesDir(context,null, "sample.pdf");
//
//        // get download service and enqueue file
//        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        manager.enqueue(request);
//    }
//
//    public static boolean isDownloadManagerAvailable(Context context) {
//        try {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                return false;
//            }
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.setClassName("com.android.providers.downloads.ui","com.android.providers.downloads.ui.DownloadList");
//          //  List <resolveinfo> list = context.getPackageManager().queryIntentActivities(intent,
//                    PackageManager.MATCH_DEFAULT_ONLY);
//          //  return list.size() > 0;
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//     public   void openPDF() {
//         if (Environment.MEDIA_MOUNTED.equals(state)) {
//             // We can read and write the media
//             Toast.makeText(context, "Media Mounted status: true", Toast.LENGTH_LONG).show();
//             try
//             {
//                 File mfile=new File(Environment.getExternalStorageDirectory()+"/pdf/");
//                 File[] list=mfile.listFiles();
//                 if (list.length==0)
//                     listOfFiles.setText("Folder is empty");
//                 else
//                 {
//                     for(int i=0;i < list.length;i++)
//                        // listOfFiles.setText("\n"+list[i].getName());
//                 }
//             }
//             catch(Exception e)
//             {
//                 Toast.makeText(context, "Exception: Folder not created yet", Toast.LENGTH_LONG).show();
//             }
//
//         } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//             Toast.makeText(context, "Media Mounted status: Read only access",Toast.LENGTH_LONG).show();
//         } else {
//             Toast.makeText(context, "Media Mounted status: Error occured", Toast.LENGTH_LONG).show();
//         }
//     }
//     }


 public  interface ProductItemSendToActivityListener{
     void onSendProduct(ProductModel model);
 } public  interface GetProfileListener{
void onSuccess(ProfileModel model);
        void onFailure(Throwable t);

        void onFailure(String s);
    }

    public static boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }




    public  interface  DownloadFileListner
    {
        void onSuccessFile(WarrantyModel model);
        void onProgressShow(int lenght, int total);
        void onDownloadFiled(String message);
        void onProgressHide();
        void startDownload();

    }
 public  interface  AccountListner
    {
        void onKnowledgeListener(List<KnowledgeModel> list);
        void onCareCustomerListener(List<CustomerCareModel> list);
        void onVideoListener(List<VideoModel> list);


    }



}
