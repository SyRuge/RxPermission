package com.xcx.rxpermission;

import android.support.v4.app.FragmentManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Create By Ruge at 2018-09-12
 */
public class PermissionRequestObservable extends Observable<int[]> {

    private FragmentManager fragmentManager;

    private String[] permissions;


    public PermissionRequestObservable(FragmentManager fragmentManager, String[] permissions) {
        this.fragmentManager = fragmentManager;
        this.permissions = permissions;
    }

    @Override
    protected void subscribeActual(Observer<? super int[]> observer) {
        RequestManager manager = new RequestManager(fragmentManager, permissions);
        manager.setObserver(observer);
        observer.onSubscribe(new MyDisposable(fragmentManager));
        manager.requestPermissions();
    }

    public static class MyDisposable extends MainThreadDisposable {

        private FragmentManager manager;

        public MyDisposable(FragmentManager manager) {
            this.manager = manager;
        }

        @Override
        protected void onDispose() {
            if (manager != null) {
                manager = null;
            }
        }
    }
}
