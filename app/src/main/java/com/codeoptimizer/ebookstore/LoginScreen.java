package com.codeoptimizer.ebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeoptimizer.ebookstore.Utilities.MyDataBase;

public class LoginScreen extends AppCompatActivity {

    EditText userEmail,userPassword;
    Button loginBtn;
    private MyDataBase mdb;
    TextView signUpBtn;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        userEmail = (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        signUpBtn = (TextView)findViewById(R.id.signUpBtn);
        rememberMe = (CheckBox)findViewById(R.id.rememberMe);

        mdb = new MyDataBase(getApplicationContext());
        mdb.open();
        mdb.save("Admin@ebook.com","admin");
        mdb.save("ankita@gmail.com","ankita123");
        mdb.save("vishal@gmail.com","vishal123");
        mdb.save("diksha@gmail.com","diksha123");
        mdb.close();

        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.contains("userEmail")){
            userEmail.setText(sharedPreferences.getString("userEmail",""));
            userPassword.setText(sharedPreferences.getString("userPassword",""));
        }


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"SignUp",Toast.LENGTH_LONG).show();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                mdb.open();
//                // listData.addAll(mdb.getCartData());
//                for (int ii = 0; ii<mdb.getUserData().size();ii++){
//                    String id = mdb.getUserData().get(ii).getUserId();
//                    String uemail =mdb.getUserData().get(ii).getUserEmail();
//                    String upassword =mdb.getUserData().get(ii).getUserPassword();
//                   // arrayList.add(new User(id,uemail,upassword));
//                }
//
                // Toast.makeText(LoginScreen.this,email,Toast.LENGTH_LONG).show();
                int count =  mdb.checkUserExist(email,password);

                if(count != 0 ){

                    if(rememberMe.isChecked()){
                        editor.putString("userEmail",email);
                        editor.putString("userPassword",password);
                    }else {
                        editor.remove("userEmail");
                        editor.remove("userPassword");
                    }
                    editor.apply();
                      if (email.startsWith("Admin")){
                         // Toast.makeText(LoginScreen.this,email,Toast.LENGTH_LONG).show();
                          Intent intent = new Intent(LoginScreen.this, HomeScreenForAdmin.class);
                          intent.putExtra("email",email);
                          startActivity(intent);
                          finish();
                      }
                      else {
                     // Toast.makeText(LoginScreen.this,"USER",Toast.LENGTH_LONG).show();
                          Intent intent = new Intent(LoginScreen.this, UserScreen.class);
                          intent.putExtra("email",email);
                          startActivity(intent);
                          finish();
                      }

                }
                else if (count == 0){
                    Toast.makeText(LoginScreen.this,"Invalid Email Or Password",Toast.LENGTH_LONG).show();
                }
                mdb.close();

            }
        });

    }
}
