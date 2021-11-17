package ib.ganz.u_care.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import ib.ganz.u_care.activity.MainActivity;
import io.reactivex.disposables.CompositeDisposable;


public class BaseFragment extends Fragment {

    MainActivity mainActivity;
    ProgressDialog progressDialog;
    CompositeDisposable compositeDisposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
        compositeDisposable = new CompositeDisposable();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Silahkan tunggu...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public void errorToast() {
        Toast.makeText(getActivity(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
    }

    protected void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hide(View ...v) {
        for (View vv : v) {
            vv.setVisibility(View.GONE);
        }
    }

    protected void show(View ...v) {
        for (View vv : v) {
            vv.setVisibility(View.VISIBLE);
        }
    }

    protected String getText(TextInputEditText e) {
        return e.getText().toString().trim();
    }

    protected String getText(EditText e) {
        return e.getText().toString().trim();
    }

    protected boolean isEmpty(TextInputEditText e) {
        return e.getText().toString().isEmpty();
    }

    protected boolean isEmpty(EditText e) {
        return e.getText().toString().isEmpty();
    }

    protected void delay(Runnable r) {
        delay(r, 2000);
    }

    protected void delay(Runnable r, int time) {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(r, time);
    }
}