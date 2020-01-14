package fci.engaly.requesttutor.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
 import android.widget.TextView;

import fci.engaly.requesttutor.R;

public class LoginActivity extends AppCompatActivity {

    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.varRegisterNow).setOnClickListener(v->{
                Intent go2RegisterType=new Intent(this,RegisterTypeActivity.class);
                startActivity(go2RegisterType);
            });
     }

}
