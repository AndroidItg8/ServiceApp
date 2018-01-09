package itg8.com.serviceapp.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;


/**
 * Created by itg_Android on 12/3/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed :" + refreshedToken);
        Log.d(TAG, "Refreshed :" + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        if(Prefs.getBoolean(CommonMethod.IS_LOGIN,false)) {
            AppApplication.getInstance().getRetroController().sendFirebaseTokenToServer(getString(R.string.url_firebase_token), refreshedToken);

        }
    }
}
