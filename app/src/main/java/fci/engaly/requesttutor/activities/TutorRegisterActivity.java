package fci.engaly.requesttutor.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import fci.engaly.requesttutor.R;
import fci.engaly.requesttutor.firebase.TutorCRUD;
import fci.engaly.requesttutor.models.TutorData;

public class TutorRegisterActivity extends AppCompatActivity {

    // all other classes
    private TutorData tutorData;
    private ArrayAdapter materialAdapter;

    // all widgets
    private EditText l_name, f_name, login_name, city, address, resume, age, phone;
    private Spinner tutor_levels, materials, gender;
    private Button sign_up;
    private ProgressBar prog_finish;
    private TextInputLayout err_l_name, err_f_name, err_login_name, err_city, err_address, err_resume, err_age, err_phone;

    private String[] ar_materials = new String[]{"عربي", "رياضيات", "علوم", "كيمياء", "فيزياء"};
    private String[] en_materials = new String[]{"Arabic", "Math", "Science", "English", "History", "Computer", "Fancies"};

    private void init() {
        // all edit texts
        f_name = findViewById(R.id.varFirstName);
        l_name = findViewById(R.id.varLastName);
        login_name = findViewById(R.id.varLoginName);
        city = findViewById(R.id.varCity);
        address = findViewById(R.id.varAddress);
        resume = findViewById(R.id.varSummary);
        age = findViewById(R.id.varAge);
        phone = findViewById(R.id.varPhoneNumber);

        // all text input layout
        err_f_name = findViewById(R.id.varErrFirstName);
        err_l_name = findViewById(R.id.varErrLastName);
        err_login_name = findViewById(R.id.varErrLoginName);
        err_city = findViewById(R.id.varErrCity);
        err_address = findViewById(R.id.varErrAddress);
        err_resume = findViewById(R.id.varErrSummary);
        err_age = findViewById(R.id.varErrAge);
        err_phone = findViewById(R.id.varErrPhoneNumber);
        // all spinners
        tutor_levels = findViewById(R.id.varlevel);
        materials = findViewById(R.id.varMaterial);
        gender = findViewById(R.id.varGender);
        // all buttons
        sign_up = findViewById(R.id.varSignUp);
        // all progressbars
        prog_finish = findViewById(R.id.varProgressCompleteRegister);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            setContentView(R.layout.activity_tutor_register);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        this.setTitle("Registeration");

        // init all widgets in tutor register
        try {
            init();


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        // fill spinner material with materials for selected level
        materialAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ar_materials);
        materials.setAdapter(materialAdapter);

//        val gender = arrayOf("male", "female","ذكر","انثي");
//        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, gender);
//        cities.setAdapter(adapter);


        /**
         * reverse action to materials when user choose material level form level list like
         * primary, preparatory and secondary.
         */
        tutor_levels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMaterials4Level();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sign_up.setOnClickListener(v -> {
            try {
                if (check_tutor_details()) {
                    if (new TutorCRUD().tutor_insert(TutorDetails2Object(), this)) {
                        Toast.makeText(TutorRegisterActivity.this, "register is OK", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(TutorRegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean check_tutor_details() {
        if (f_name.getText().toString().trim().length() < 2) {
            Toast.makeText(this, "first name length must be more than 2 letters", Toast.LENGTH_SHORT).show();
        } else if (l_name.getText().toString().trim().length() < 3) {
            Toast.makeText(this, "last name length must be more than 2 letters", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(login_name.getText().toString().trim()).matches()) {
            Toast.makeText(this, "login name must be as exapmle format", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.PHONE.matcher(phone.getText().toString().trim()).matches()) {
            Toast.makeText(this, "not valid phone number", Toast.LENGTH_SHORT).show();
        } else if (city.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please check your city as example", Toast.LENGTH_SHORT).show();
        } else if (age.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please check your age", Toast.LENGTH_SHORT).show();
        } else if (address.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "please check your addrees as example", Toast.LENGTH_SHORT).show();
        } else if (resume.getText().toString().length() < 10 || resume.getText().toString().length() > 500) {
            Toast.makeText(this, "your cv must be between 100 and 500 letters", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


    /**
     * pass data form its ui(s) conatiner to tutorData model
     * and return it as object then pass it to firebase
     * to insert it as JSON data.
     *
     * @return
     */
    private TutorData TutorDetails2Object() {
        tutorData = new TutorData();
        tutorData.setShownName(f_name.getText().toString() + " " + l_name.getText().toString());
        tutorData.setLoginName(login_name.getText().toString());
        tutorData.setAddress(address.getText().toString());
        tutorData.setAge(age.getText().toString());
        tutorData.setGender(gender.getSelectedItem().toString());
        tutorData.setCity(city.getText().toString());
        tutorData.setLevel(tutor_levels.getSelectedItem().toString());
        tutorData.setMaterial(materials.getSelectedItem().toString());
        tutorData.setTutorSummary(resume.getText().toString().trim());
        return tutorData;
    }


    /**
     * select materials as tutor level in learning
     * when user select her level all materials attached with this level
     * will shown in material spinner.
     */
    private void getMaterials4Level() {
        if (tutor_levels.getSelectedItemId() < 3) {
            materialAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ar_materials);
        } else {
            materialAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, en_materials);
        }
        materials.setAdapter(materialAdapter);
    }


}
