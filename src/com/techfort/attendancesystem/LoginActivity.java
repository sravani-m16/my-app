package com.techfort.attendancesystem;


import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.database.Mydatabseone;

public class LoginActivity extends Activity implements OnClickListener {
	EditText user, pass;
	Button login;
	CheckBox ch_rem;
	SQLiteDatabase sd;
	String username, password;
	TextView tv_signup;
	Mydatabseone mydb;
	SharedPreferences shpreff;
	LinearLayout lll;
	SharedPreferences.Editor ed;
	ArrayList<String> alist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		lll=(LinearLayout)findViewById(R.id.ll);
		lll.getBackground().setAlpha(40);
		shpreff=PreferenceManager.getDefaultSharedPreferences(this);
		ed=shpreff.edit();
		mydb = new Mydatabseone(this);
		initilization();
		loading_rememberDATA();

	}

	private void loading_rememberDATA() {
		// TODO Auto-generated method stub
		
		boolean val__c=shpreff.getBoolean("key_chebox", false);
		if(val__c){
			
			
			user.setText(shpreff.getString("Key_username", null));
			pass.setText(shpreff.getString("Key_password", null));
			ch_rem.setChecked(true);
			
		}else{
			
			
			ch_rem.setChecked(false);
		}
}

	private void initilization() {
		// TODO Auto-generated method stub

		user = (EditText) findViewById(R.id.editText1);
		tv_signup = (TextView) findViewById(R.id.textView2);
		pass = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button1);
		ch_rem = (CheckBox) findViewById(R.id.checkBox1);
		login.setOnClickListener(this);
		tv_signup.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.button1:
			validations();

			break;

		case R.id.textView2:
			// gng to sign up page

			startActivity(new Intent(this, RegisterActivity.class));

			break;

		default:
			break;
		}

	}

	private void validations() {
	

		username = user.getText().toString();
		password = pass.getText().toString();
		
		if (username.equals("")) {

			user.setError("missing username");

		}

		if (password.equals("")) {
			pass.setError("missing password");
		} else {		
			Cursor cw = mydb.retrieve_database();
			int i=cw.getCount();
			System.out.println(""+i);
			if (i==0) {
				
				new AlertDialog.Builder(LoginActivity.this).setMessage("Please signup before login").show();
			}
			else{		
				alist=mydb.retrieve_credientiaos_frm_dataBase();
			
			if  (alist.contains(username) && alist.contains(password)) {
				ed.putString("Key_username_profile", username);
				ed.commit();
				
				if(ch_rem.isChecked()){
					
					ed.putString("Key_username", username);
					ed.putString("Key_password", password);
					ed.putBoolean("key_chebox", ch_rem.isChecked());
					ed.commit();
					user.setText("");
					pass.setText("");
					
				}
				

				startActivity(new Intent(this, DashBoardActivity.class));

			} else {
				
				new AlertDialog.Builder(LoginActivity.this)
						.setMessage("Enter correct crediantials")
						.setPositiveButton("OK", null).show();
			}
			}

		}

	}	}

