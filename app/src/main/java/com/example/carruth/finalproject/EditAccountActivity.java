
        package com.example.carruth.finalproject;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

public class EditAccountActivity extends AppCompatActivity {

    public boolean infoEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account2);
        infoEdit = false;
    }

    public void onEditAccount(View view){

        EditText text = (EditText)findViewById(R.id.edit_email);
        String email = text.getText().toString();

        if (!email.equals("")){
            Log.d("dasdasdasd","asdasdasdasd");
            new User().updateDataBase(getIntent().getStringExtra("user"),"email",email);
            infoEdit = true;
        }
        //Get first name from UI and update account
        text = (EditText)findViewById(R.id.edit_first_name);
        String firstName = text.getText().toString();

        if (!firstName.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"firstName",firstName);
            infoEdit = true;
        }
        //Get last name from UI and update account
        text = (EditText)findViewById(R.id.edit_last_name);
        String lastName = text.getText().toString();
        if (!lastName.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"lastName",lastName);
            infoEdit = true;
        }
        //Get address from UI and update account
        text = (EditText)findViewById(R.id.edit_address);
        String address = text.getText().toString();
        if (!address.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"address",address);
            infoEdit = true;
        }
        //Get city from UI and update account
        text = (EditText)findViewById(R.id.edit_city);
        String city = text.getText().toString();
        if (!city.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"city",city);
            infoEdit = true;
        }
        //Get state from UI and update account
        Spinner st = (Spinner) findViewById(R.id.edit_state);
        String state = st.getSelectedItem().toString();
        if (!state.isEmpty()){
            new User().updateDataBase(getIntent().getStringExtra("user"),"state",state);
        }
        //Get zip from UI and update account
        text = (EditText)findViewById(R.id.edit_zip);
        String zip = text.getText().toString();
        if (!zip.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"zip",zip);
            infoEdit = true;
        }
        //Get phone from UI and update account
        text = (EditText)findViewById(R.id.edit_phone);
        String phone = text.getText().toString();
        if (!phone.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"phone",phone);
            infoEdit = true;
        }
        //Get password from UI and update account
        text = (EditText)findViewById(R.id.edit_password);
        String password = text.getText().toString();
        if (!password.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"password",password);
            infoEdit = true;
        }
        //Get confirmed password from UI and check against original
        text = (EditText)findViewById(R.id.edit_password_confirm);
        String passwordConfirm = text.getText().toString();
        //Get security question from UI and update account
        Spinner sq = (Spinner) findViewById(R.id.edit_security_question);
        String ques = sq.getSelectedItem().toString();
        if (!ques.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"security_question",ques);
        }
        //Get answer to security question and update account
        text = (EditText)findViewById(R.id.edit_security_answer);
        String answer = text.getText().toString();
        if (!answer.equals("")){
            new User().updateDataBase(getIntent().getStringExtra("user"),"security_answer",answer);
            infoEdit = true;
        }
        if (password.equals(passwordConfirm)){
            if (infoEdit){
                Toast.makeText(getApplicationContext(),"Successfully Updated Information!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
                intent.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Please enter information to change!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Make sure passwords match!", Toast.LENGTH_SHORT).show();
        }

    }
}
