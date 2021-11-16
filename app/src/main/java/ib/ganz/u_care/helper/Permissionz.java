package ib.ganz.u_care.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by limakali on 3/19/2018.
 *
 * USAGE ->
 * {
 *     // call this ->
 *     Permissionz.checkPermission(a, permissions);
 *
 *     // on onRequestPermissionsResult of the activity or frgament, call ->
 *     super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 *     p.onRequestPermissionsResult(a, requestCode, permissions, grantResults);
 * }
 *
 */

public class Permissionz
{
    public static final int PERMISSION_CODE = 666;
    private static Permissionz instance;
    private static String[] permit;

    private Activity a;

    private Permissionz(Activity a, String[] s)
    {
        this.a = a;
        Permissionz.permit = s;
    }

    public static Permissionz checkPermission(Activity a, String[] s)
    {
        if (instance == null) instance = new Permissionz(a, s);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            for (String p : permit)
            {
                int permisi = ContextCompat.checkSelfPermission(a, p);

                if (permisi != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(a, permit, PERMISSION_CODE);
                }
            }
        }

        return instance;
    }

    public static boolean isPermissionsGranted(Activity a, String[] s)
    {
        boolean b = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : s) {
                if (ContextCompat.checkSelfPermission(a, p) != PackageManager.PERMISSION_GRANTED) {
                    b = false;
                    break;
                }
            }
        }

        return b;
    }

    public static void onRequestPermissionsResult(Activity a, int requestCode, String s[], int[] grantResults)
    {
        if (instance == null) instance = new Permissionz(a, s);

        switch (requestCode)
        {
            case PERMISSION_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {

                }
            }
        }
    }
}