package com.techfort.attendancesystem;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class csvtry extends Activity {
	SharedPreferences shpreff;
	String val_mail,val_branch,sem,file_name;
	
	
	Calendar c = Calendar.getInstance();
	ArrayList<String> alist = new ArrayList<String>();
	ArrayList<String> al_stu = new ArrayList<String>();
	//ArrayList<String> alist_ab = new ArrayList<String>();
	//String present,absent;

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shpreff=this.getSharedPreferences("email_setter", MODE_WORLD_READABLE);
		
		savedInstanceState = getIntent().getExtras();
		alist = savedInstanceState.getStringArrayList("vpresent");
		al_stu=savedInstanceState.getStringArrayList("no_student");
		//alist_ab = savedInstanceState.getStringArrayList("vabsent");
		val_branch=savedInstanceState.getString("key_branch");
		sem=savedInstanceState.getString("key_sem");
		System.out.println("the present values " + alist);
		//System.out.println("the absent values " + alist_ab);
		file_name="Present_on"+c.DATE+".csv";
		generateCsvFile(file_name);
	}

	
	
	@SuppressLint("SdCardPath")
	private void generateCsvFile(String filename) {
		// TODO Auto-generated method stub
		System.out.println("in csv+"+filename);
		File sdcard = Environment.getExternalStorageDirectory();
		File my_auton = new File(sdcard.getAbsolutePath(), "VMTW_Attendance_Report");
		my_auton.mkdir();
		
		try {
			File myfile = new File(my_auton, filename);
			
			System.out.println("in try");
			
			FileOutputStream fos = new FileOutputStream(myfile);
			OutputStreamWriter ows = new OutputStreamWriter(fos);
			ows.write("VMTW Attendance Report on " +c.getTime()+"\n");
			ows.write("Branch: "+val_branch+"\t");
			ows.write("Sem: "+sem+"\n");
			//ows.write("Roll Numbers" + al_stu + "\t");
			//ows.write("");
			for (int i = 0; i < alist.size(); i++) {
				System.out.println("in loop");
				ows.write(alist.get(i) + "\n");
			}
			ows.write("Number of Presenties:" +alist.size()+"\n");
			int val_student_size=al_stu.size();
			System.out.println("size of students"+val_student_size);
			int val_present_size=alist.size();
			System.out.println("size of present"+val_present_size);
			double per_age=((double)val_present_size/val_student_size)*100;
			ows.write("Percentage of presents: "+ per_age+"%");
			ows.close();
			
			val_mail=shpreff.getString("key_val", null);
if(val_mail==null){
	System.out.println("in if contion before alert");
	AlertDialog.Builder al = new AlertDialog.Builder(this);
	al.setMessage("Plz Fix the Mail id");
	al.setPositiveButton("Redirect", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			Intent ii=new Intent(csvtry.this,MailingSetterActivity.class);
			ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(ii);
			
		}
	});
	al.show();
}else{
	System.out.println("in else");
	////////////////////////////////////////
	Intent email = new Intent(Intent.ACTION_SEND);
	email.putExtra(Intent.EXTRA_EMAIL,
			new String[] { val_mail });
	email.setType("message/rfc822");
	email.putExtra(Intent.EXTRA_SUBJECT, "Attendence on" + c.getTime());
	email.putExtra(
			Intent.EXTRA_STREAM,
			Uri.parse("file:///sdcard/VMTW_Attendance_Report/"+filename));
	
	

	startActivity(Intent.createChooser(email, val_mail));
	//Toast.makeText(this, "email sened", Toast.LENGTH_LONG).show();

	/*AlertDialog.Builder al = new AlertDialog.Builder(this);
	al.setMessage("Successfully Mailed...!");
	al.setPositiveButton("Ok", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
		}
	});
	al.show();*/
}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

}
