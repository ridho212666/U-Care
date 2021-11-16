package ib.ganz.u_care.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import ib.ganz.u_care.R;
import ib.ganz.u_care.network.Service;

public class OtpActivity extends BaseActivity {

    private EditText e1, e2, e3, e4, e5, e6;
    private View bLanjut;
    private String mVerificationId = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);
        bLanjut = findViewById(R.id.bLanjut);

        initOtp();
    }

    private void initOtp() {
        mAuth = FirebaseAuth.getInstance();
        final String TAG = "eaea";
        String phoneNumber = getIntent().getStringExtra("no_hp");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = "+62" + phoneNumber.substring(1);
        }

        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);

                try {
                    Log.d("eaea", credential.getSmsCode());
                    String[] s = credential.getSmsCode().split("");
                    if (s[0].isEmpty()) {
                        e1.setText(s[1]);
                        e2.setText(s[2]);
                        e3.setText(s[3]);
                        e4.setText(s[4]);
                        e5.setText(s[5]);
                        e6.setText(s[6]);
                    }
                    else {
                        e1.setText(s[0]);
                        e2.setText(s[1]);
                        e3.setText(s[2]);
                        e4.setText(s[3]);
                        e5.setText(s[4]);
                        e6.setText(s[5]);
                    }
                }
                catch (Exception e) {
                    Log.d("eaea", e.getMessage());
                }

                // signInWithPhoneAuthCredential(credential);
                onPhoneVerified();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d(TAG, "onVerificationFailed", e);
                toast(e.getMessage());

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    toast(e.getMessage());
                }
                else if (e instanceof FirebaseTooManyRequestsException) {
                    toast(e.getMessage());
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);

                mVerificationId = verificationId;
            }
        };


        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
            .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        bLanjut.setOnClickListener(x -> {
            String code = e1.getText().toString() + e2.getText().toString() + e3.getText().toString() + e4.getText().toString() + e5.getText().toString() + e6.getText().toString();
            if (!code.isEmpty()) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void onPhoneVerified() {
        toast("Kode OTP berhasil");
        String id = getIntent().getStringExtra("id");
        boolean isOrtu = getIntent().getBooleanExtra("ortu", false);

        progressDialog.show();
        compositeDisposable.add(isOrtu ?
                Service.phoneVerifiedOrtu(id).subscribe(r -> {
                    progressDialog.dismiss();

                    Intent i = new Intent(OtpActivity.this, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }, e -> {
                    progressDialog.dismiss();
                    errorToast();
                    Log.d("eaea", e.getMessage());
                }) :
                Service.phoneVerifiedNakes(id).subscribe(r -> {
                    progressDialog.dismiss();

                    Intent i = new Intent(OtpActivity.this, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }, e -> {
                    progressDialog.dismiss();
                    errorToast();
                    Log.d("eaea", e.getMessage());
                })
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        progressDialog.show();
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Log.d("eaea", "signInWithCredential:success");
                FirebaseUser user = task.getResult().getUser();

                onPhoneVerified();
            }
            else {
                Log.w("eaea", "signInWithCredential:failure", task.getException());
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    toast("Kode yang anda masukkan salah");
                }
                else {
                    errorToast();
                }
            }
        });
    }

}