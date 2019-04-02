package com.xcx.rxpermission;

import android.support.v4.app.FragmentManager;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.Observer;

/**
 * Create By Ruge at 2018-09-12
 */
public class RequestManager implements PermissionSupportFragment.PermissionResult {

    private static final String F_TAG = "MyPermissionSupportFragment";

    private Reference<FragmentManager> reference;

    private String[] permissions;

    private Observer<? super int[]> observer;

    public void setObserver(Observer<? super int[]> observer) {
        this.observer = observer;
    }

    public RequestManager(FragmentManager manager, String[] permissions) {
        reference = new WeakReference(manager);
        this.permissions = permissions;
    }


    public void requestPermissions() {
        if (reference == null || reference.get() == null ||
                permissions == null || permissions.length <= 0) {
            return;
        }

        FragmentManager manager = reference.get();
        PermissionSupportFragment fragment = getSupportFragment(manager);
        fragment.setPermissionResult(this);
        fragment.requestPermissions(permissions);

    }


    private PermissionSupportFragment getSupportFragment(FragmentManager manager) {
        PermissionSupportFragment f = new PermissionSupportFragment();
        manager.beginTransaction()
                .add(f, F_TAG)
                .commitNowAllowingStateLoss();
        return f;
    }

    @Override
    public void onPermissionResult(int[] grantResults) {
        if (observer != null) {
            observer.onNext(grantResults);
        }
    }
}
