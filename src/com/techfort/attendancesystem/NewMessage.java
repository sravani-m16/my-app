package com.techfort.attendancesystem;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.database.Mydatabseone;

public class NewMessage extends Activity implements OnClickListener {
	EditText ed1_name,ed2_number;
	Button save;
	String name,number;
	Mydatabseone mydb;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_message);
		ed1_name=(EditText)findViewById(R.id.editText1);
		ed2_number=(EditText)findViewById(R.id.editText2);
		save=(Button)findViewById(R.id.button1);
		save.setOnClickListener(this);
		mydb=new Mydatabseone(this);
	}

	
	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v) {
		
		name=ed1_name.getText().toString();
		number=ed2_number.getText().toString();
		
		if(name.length()!=0&&(number.length()!=0))
		{
		//&&(number.length()==10)
		mydb.insert_numbers_parent(name,number);
		Toast.makeText(this, "inserted", 40).show();
		startActivity(new Intent(this,MessagingAcvitvity.class));
		this.finish();
	
		}
		else{
			Toast.makeText(NewMessage.this, "Please Enter Proper Values",Toast.LENGTH_LONG).show();
			
		}
		
		
		
	}
	
	

	

}
