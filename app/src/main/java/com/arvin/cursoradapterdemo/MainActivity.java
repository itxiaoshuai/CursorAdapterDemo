package com.arvin.cursoradapterdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button mBt_num;
	public static final int REQ_CODE_SELECT_NUM = 100;
	private EditText mEt_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
	}

	//初始化事件
	private void initEvent() {
		mBt_num.setOnClickListener(this);
	}

	//初始化控件
	private void initView() {
		mBt_num = (Button) findViewById(R.id.bt_num);
		mEt_num = (EditText) findViewById(R.id.et_num);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_num:
				Intent intent = new Intent(this, SelectNumActivity.class);
				startActivityForResult(intent, REQ_CODE_SELECT_NUM);
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQ_CODE_SELECT_NUM:
				if (resultCode == Activity.RESULT_OK) {
					String num = data.getStringExtra(SelectNumActivity.KEY_NUM);
					//赋值给EditText
					mEt_num.setText(num);
				}
				break;
		}
	}
}
