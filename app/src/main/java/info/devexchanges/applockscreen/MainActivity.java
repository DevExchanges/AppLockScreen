package info.devexchanges.applockscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PasswordSession session;
    private View btnChange;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnChange = findViewById(R.id.btn_change);
        txtPassword = (TextView) findViewById(R.id.text);

        session = new PasswordSession(getApplicationContext());
        txtPassword.setText(session.getKeyPassword()); //show password just typed in lock screen to TextView

        btnChange.setOnClickListener(onClickListener());
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setKeyPassword(""); //remove old password
                session.setKeyIsPass(false);
                Intent intent = new Intent(MainActivity.this, LockScreenActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!session.getKeyIsPass()) {
            Intent intent = new Intent(this, LockScreenActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        session.setKeyIsPass(false);
    }
}
