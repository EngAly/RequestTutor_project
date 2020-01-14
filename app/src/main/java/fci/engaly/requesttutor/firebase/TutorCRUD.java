package fci.engaly.requesttutor.firebase;

import android.app.Activity;
import android.util.Patterns;
import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fci.engaly.requesttutor.activities.TutorRegisterActivity;
import fci.engaly.requesttutor.models.TutorData;
import fci.engaly.requesttutor.utils.Helper;


import java.util.HashMap;
import java.util.Map;

public class TutorCRUD {

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    /**
     * method that is used to insert tutor (record) data to database
     * with all tutor's fields ie shownName,loginName,joinDate,... etc
     * that is child for user LoginName
     * note that child(users) is stable for all users' emails i.e all users fields
     * after user name.
     *
     * @param data
     */
    public boolean tutor_insert(TutorData data, Activity context) {
//        context=new TutorRegisterActivity();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
        try {
            Map<String, String> userFields = new HashMap<>();
            userFields.put("login", data.getLoginName());
            userFields.put("shownName", data.getShownName());
            userFields.put("phone", data.getPhoneNumer());
            userFields.put("address", data.getAddress());
            userFields.put("age", data.getAge());
            userFields.put("city", data.getCity());
            userFields.put("gender", data.getGender());
            userFields.put("level", data.getLevel());
            userFields.put("material", data.getMaterial());
            userFields.put("resume", data.getTutorSummary());
            myRef.child(Helper.getHash(data.getLoginName())).setValue(userFields);
//            Task t = myRef.child(data.getLoginName()).setValue(userFields);
//            return t.isSuccessful();
            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

//    private static boolean isFill(TutorData data) {
//        return (data.getShownName().length() < 8 || isEmail(data) || !data.getAddress().isEmpty() ||
//                !data.getCity().isEmpty() || !data.getAge().isEmpty() ||
//                (data.getTutorSummary().length() < 10 && data.getTutorSummary().length() > 500));
//    }
//
//    private static boolean isEmail(TutorData data) {
//        return Patterns.EMAIL_ADDRESS.matcher(data.getLoginName()).matches() &&
//                Patterns.PHONE.matcher(data.getPhoneNumer()).matches();
//    }


}
