package itg8.com.serviceapp.common_method;

import java.io.IOException;

/**
 * Created by Android itg 8 on 10/14/2017.
 */

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
