package com.xcx.rxpermission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Create By Ruge at 2018-09-12
 */
public class RxPermission {


    public static SupportFactory with(FragmentActivity activity) {
        if (activity == null) {
            throw new NullPointerException("Activity or Fragment is null");
        }
        return new SupportFactory(activity.getSupportFragmentManager());
    }

    public static SupportFactory with(Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("Activity or Fragment is null");
        }
        return new SupportFactory(fragment.getChildFragmentManager());
    }


    static public class SupportFactory {

        private FragmentManager manager;


        public SupportFactory(FragmentManager manager) {
            this.manager = manager;
        }

        public PermissionRequestObservable requestPermissions(String... permissions) {
            return new PermissionRequestObservable(manager, permissions);
        }

    }
}
