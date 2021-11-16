package ib.ganz.u_care.activity;

import static com.zhihu.matisse.filter.Filter.K;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import ib.ganz.u_care.R;
import ib.ganz.u_care.helper.GifSizeFilter;
import ib.ganz.u_care.helper.Permissionz;
import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    CompositeDisposable compositeDisposable;

    protected String[] external_storage_permissions = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        compositeDisposable = new CompositeDisposable();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Silahkan tunggu...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void errorToast() {
        Toast.makeText(this, "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
    }

    protected void hide(View...v) {
        for (View vv : v) {
            vv.setVisibility(View.GONE);
        }
    }

    protected void show(View...v) {
        for (View vv : v) {
            vv.setVisibility(View.VISIBLE);
        }
    }

    protected String getText(TextInputEditText e) {
        return e.getText().toString();
    }

    protected boolean isEmpty(TextInputEditText e) {
        return e.getText().toString().isEmpty();
    }

    protected void chooseImage() {
        chooseImage(212);
    }

    protected void chooseImage(int resCode) {
        if (Permissionz.isPermissionsGranted(this, external_storage_permissions)) {
            Matisse.from(this)
                    .choose(MimeType.ofImage())
                    .theme(R.style.Matisse_Dracula)
                    .countable(true)
                    .maxSelectable(1)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "ib.ganz.simadminkarangtarunar", "AdminSimKarangTaruna"))
                    .addFilter(new GifSizeFilter(320, 320, 5 * K * K))
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new PicassoEngine())
                    .forResult(resCode);
        }
        else {
            Permissionz.checkPermission(this, external_storage_permissions);
        }
    }

    protected void finishAndReload() {
        Intent i = new Intent();
        i.putExtra("reload", true);
        setResult(RESULT_OK, i);
        finish();
    }
}
