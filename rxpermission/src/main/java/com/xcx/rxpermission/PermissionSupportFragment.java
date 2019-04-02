package com.xcx.rxpermission;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class PermissionSupportFragment extends Fragment {

    private PermissionResult permissionResult;
    private static final int PERMISSION_REQUEST_CODE = 1000;

    public void setPermissionResult(PermissionResult permissionResult) {
        this.permissionResult = permissionResult;
    }


    public void requestPermissions(String[] permissions) {
        if (permissions != null && permissions.length > 0) {
//            getPermissionWithNoGranted(permissions);
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }

    private List<String> getPermissionWithNoGranted(String[] permissions) {
        List<String> list=new ArrayList<>();
        if (getActivity() == null){
            return list;
        }
        for (String p:permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(),p) != PackageManager.PERMISSION_GRANTED){
                list.add(p);
            }
        }

        return list;


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionResult != null) {
            permissionResult.onPermissionResult(grantResults);
        }
    }

    public interface PermissionResult {
        void onPermissionResult(int[] grantResults);
    }
}
