package itg8.com.eprocurementsystem.login;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Android itg 8 on 10/7/2017.
 */

public class ViewPagerLoginAdapter  extends FragmentPagerAdapter{


        private Context context;
    private Fragment fragment;


    public ViewPagerLoginAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return 5;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
//                case 0:
//                    fragment = new LoginFragment();
//                    break;
//                case 1:
//                    fragment = new OTPFragment();
//                    break;
//                case 3:
//                    fragment = new SignUpFragment();
//                    break;
            }
            return fragment;

        }



}
