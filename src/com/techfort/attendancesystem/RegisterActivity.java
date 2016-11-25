package com.techfort.attendancesystem;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.database.Mydatabseone;

public class RegisterActivity extends Activity implements OnClickListener {
	Button submit;
	EditText user, pass, fullname, address, country, number;
	RadioButton red_mail, red_femail;
	String username, password;
	Mydatabseone mydb;
	
	String S_Fullname,S_Address,S_Phonenumber,S_Country;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mydb=new Mydatabseone(this);
		initilize();
	}

	private void initilize() {
		// TODO Auto-generated method stub
		submit = (Button) findViewById(R.id.button1);
		user = (EditText) findViewById(R.id.editText2);
		fullname = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText3);
		address = (EditText) findViewById(R.id.editText4);
		number = (EditText) findViewById(R.id.editText5);
		country = (EditText) findViewById(R.id.editText6);
	
		red_femail = (RadioButton) findViewById(R.id.radio2);
		red_mail = (RadioButton) findViewById(R.id.radio1);
		submit.setOnClickListener(this);
	/*	Sh_obj=getSharedPreferences("DATA",MODE_PRIVATE);
		Sh_ed_obj=Sh_obj.edit();
	*/}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		validation();

	}

	private void validation() {
		// TODO Auto-generated method stub
		username = user.getText().toString();
		password = pass.getText().toString();
		S_Fullname=fullname.getText().toString();
		S_Phonenumber=number.getText().toString();
		S_Address=address.getText().toString();
		S_Country=country.getText().toString();
		
		if (username.equals("")||password.equals("")||S_Fullname.equals("")||S_Phonenumber.equals("")||S_Address.equals("")||S_Country.equals("")) {
			
			Toast.makeText(RegisterActivity.this, "Please Enter All values",Toast.LENGTH_LONG).show();
			
		}
		
		/*}
		if (password.equals("")) {
			pass.setError("missing password");
		}if (S_Fullname.equals("")) {
			E_Fullname.setError("missing full name");
		}if (S_Phonenumber.equals("")) {
			E_Phonenumber.setError("missing phone number");
		}if (S_Address.equals("")) {
			E_Address.setError("missing address");
			
		}
		if (S_Country.equals("")) {
			E_Country.setError("country name missing");
		}
		*/
		
		else{
			
			// store in data base 
			// navigate to Login if success
			
			mydb.insert_in_to_register_values(username,password,S_Fullname,S_Address,S_Phonenumber,S_Country);
			startActivity(new Intent(this,LoginActivity.class));
		}

	}

}
