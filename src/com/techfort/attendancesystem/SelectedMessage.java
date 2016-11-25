package com.techfort.attendancesystem;


import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SelectedMessage extends Activity {
	EditText ed;
	ArrayList<String> alist=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savedInstanceState=getIntent().getExtras();
		alist=savedInstanceState.getStringArrayList("My_call_list");
		setContentView(R.layout.activity_selected_message);
		ed=(EditText)findViewById(R.id.editText1);
	}

	public void sendAllMessages(View v){
		String val=ed.getText().toString();
		if(val.length()==0){
			ed.setError("please add message");
		}
		else{
			if(alist.size()==0){
				
				Toast.makeText(this, "please add phones numbers", Toast.LENGTH_LONG).show();
			}else{
			for(int i=0;i<alist.size();i++){
				String valss[]=alist.get(i).split("~");
				SmsManager.getDefault().sendTextMessage(valss[1].toString(), null, val, null, null);
				Toast.makeText(this, "Message send successfully", Toast.LENGTH_LONG).show();
				this.finish();
				
			}}
			
			
			
		}
		
		
		
	}

}
