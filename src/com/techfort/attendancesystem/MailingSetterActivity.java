package com.techfort.attendancesystem;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MailingSetterActivity extends Activity implements OnClickListener {
	EditText email_ed;
	Button fix;
	SharedPreferences preff;
	SharedPreferences.Editor ed;

	@SuppressLint("WorldWriteableFiles")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mailing_setter);
		email_ed=(EditText)findViewById(R.id.editText1);
		fix=(Button)findViewById(R.id.button1);
		fix.setOnClickListener(this);
		preff=this.getSharedPreferences("email_setter", MODE_WORLD_WRITEABLE);
		ed=preff.edit();
	//	load_email();
	}

	/*private void load_email() {
		// TODO Auto-generated method stub
		String val_true=preff.getString("key_val", null)
		if(.equals("")){
			
			
		}else{
			
			email_ed.setText(preff.getString("key_val", null));
			email_ed.setSelection(email_ed.getText().length());
			
		}
		
	}*/

	@Override
	public void onClick(View v) {
	
		String val=email_ed.getText().toString();
	if(	E_Mail_Validation(val)){
		ed.putString("key_val", val);
		ed.commit();
		Toast.makeText(this, "email setted to conserned person", Toast.LENGTH_LONG).show();
		this.finish();
	}else{
		Toast.makeText(this, "Provide valid Email id", Toast.LENGTH_LONG).show();
	}
	}

	private boolean E_Mail_Validation(String val1) {


		Pattern pattern;
		Matcher matcher;
		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(val1);
		System.out.println("" + matcher);
		return matcher.matches();

	}

		
	}

	


