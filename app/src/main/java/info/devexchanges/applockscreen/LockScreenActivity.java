package info.devexchanges.applockscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreenActivity extends AppCompatActivity {

    private EditText txtPass;
    private LinearLayout llRePass;
    private EditText txtRePass;
    private View btn1;
    private View btn2;
    private View btn3;
    private View btn4;
    private View btn5;
    private View btn6;
    private View btn7;
    private View btn8;
    private View btn9;
    private View btn0;
    private View btnGo;

    private PasswordSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new PasswordSession(getApplicationContext());

        setContentView(R.layout.activity_lock_screen);
        findViews();

        btn0.setOnClickListener(onClickListener(0));
        btn1.setOnClickListener(onClickListener(1));
        btn2.setOnClickListener(onClickListener(2));
        btn3.setOnClickListener(onClickListener(3));
        btn4.setOnClickListener(onClickListener(4));
        btn5.setOnClickListener(onClickListener(5));
        btn6.setOnClickListener(onClickListener(6));
        btn7.setOnClickListener(onClickListener(7));
        btn8.setOnClickListener(onClickListener(8));
        btn9.setOnClickListener(onClickListener(9));
        btnGo.setOnClickListener(onChangeActivityListener());

        if (!session.getKeyPassword().equals("")) {
            llRePass.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener onChangeActivityListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!session.getKeyPassword().equals("")) {
                    if (!getText(txtPass).equals(session.getKeyPassword())) {
                        Toast.makeText(LockScreenActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    } else {
                        session.setKeyIsPass(true);
                        Intent intent = new Intent(LockScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    if (textLength(txtPass) < 4) {
                        Toast.makeText(LockScreenActivity.this, "Password is 4 digits", Toast.LENGTH_SHORT).show();
                    } else if (textLength(txtRePass) < 4) {
                        Toast.makeText(LockScreenActivity.this, "Please retype password", Toast.LENGTH_SHORT).show();
                    } else if (!txtRePass.getText().toString().trim().equals(txtPass.getText().toString().trim())) {
                        Toast.makeText(LockScreenActivity.this, "Re-type failed", Toast.LENGTH_SHORT).show();
                    } else {
                        session.setKeyIsPass(true);
                        session.setKeyPassword(txtPass.getText().toString());
                        Intent intent = new Intent(LockScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        Log.i("Activity", "go gog go");
                        finish();
                    }
                }
            }
        };
    }

    private View.OnClickListener onClickListener(final int value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPass.isFocused() && textLength(txtPass) < 4) {
                    txtPass.setText(txtPass.getText().toString() + String.valueOf(value));
                } else if (txtRePass.isFocused() && textLength(txtRePass) < 4) {
                    txtRePass.setText(txtRePass.getText().toString() + String.valueOf(value));
                } else {
                    Log.i("Activity", "?????????");
                }
            }
        };
    }

    private void findViews() {
        txtPass = (EditText) findViewById(R.id.txt_pass);
        llRePass = (LinearLayout) findViewById(R.id.ll_re_pass);
        txtRePass = (EditText) findViewById(R.id.txt_re_pass);
        btnGo = findViewById(R.id.btn_go);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);
    }

    private int textLength(TextView textView) {
        return textView.getText().toString().trim().length();
    }

    private String getText(TextView textView) {
        return textView.getText().toString().trim();
    }
}
