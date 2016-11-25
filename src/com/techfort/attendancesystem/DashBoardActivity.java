package com.techfort.attendancesystem;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DashBoardActivity extends Activity implements OnClickListener {
	ImageView act_img, pro_img, msg_ing, email_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dash_board);
		RelativeLayout rel=(RelativeLayout)findViewById(R.id.relle);
		rel.getBackground().setAlpha(40);
		findViewById(R.id.imageView1).setOnClickListener(this);
		findViewById(R.id.imageView2).setOnClickListener(this);
		findViewById(R.id.imageView3).setOnClickListener(this);
		findViewById(R.id.imageView4).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dash_board, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.imageView1:

			startActivity(new Intent(this,AttendanceActivity.class));
			
			break;

		case R.id.imageView2:
			
			startActivity(new Intent(this,MailingSetterActivity.class));

			break;
		case R.id.imageView3:
			
			startActivity(new Intent(this,MessagingAcvitvity.class));

			break;
		case R.id.imageView4:
			//startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.aceec.ac.in")));
			
			startActivity(new Intent(this,NewMessage.class));
			break;
		default:
			break;
		}

	}

}
