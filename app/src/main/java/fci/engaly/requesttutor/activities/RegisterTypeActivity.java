package fci.engaly.requesttutor.activities;

 import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
 import android.widget.Button;

 import fci.engaly.requesttutor.R;

public class RegisterTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);



        findViewById(R.id.varTutorRegister).setOnClickListener(v->{
            Intent go2TutorRegister=new Intent(this,TutorRegisterActivity.class);
            startActivity(go2TutorRegister);
        });
    }
}


