package kr.ac.korea.capstoneproject.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionUtil {
    private static final int REQUEST_ACCESS_FINE_LOCATION = 1;
    private static String[] PERMISSION_ACCESS_FINE_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static boolean checkPermissions(Activity activity, String permission) {
        int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);

        if (permissionResult == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }

    public static void requestExternalPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSION_ACCESS_FINE_LOCATION, REQUEST_ACCESS_FINE_LOCATION);
    }

    public static boolean verifyPermission(int[] grantResults) {
        if(grantResults.length < 1) {
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public void showRequestAgainDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("이 권한은 반드시 필요한 권한이므로, 설정에서 활성화 부탁드립니다 :)");
        builder.setPositiveButton("설정하러 가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.parse("package: " + activity.getPackageName()));
                activity.startActivity(intent);
            }
        });

        builder.create();
    }
}
