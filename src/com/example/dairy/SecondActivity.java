package com.example.dairy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener{

	private EditText edit;

	private String fileName;
	
	private int position;
	
	private List<Day> dayList;
	
	private String content_parttime;
	
	private Button btn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		edit=(EditText) findViewById(R.id.edit);
		
		Intent intent=getIntent();
		if(intent.getStringExtra("extra_month")!=null){
			fileName=intent.getStringExtra("extra_month");
		}else{
			fileName=intent.getStringExtra("extra");
		}
		
		position=Integer.parseInt(intent.getStringExtra("extra_data"));
		
		dayList=read();
		
		if(dayList.size()!=0){
			if(!TextUtils.isEmpty(dayList.get(position).getContent())){
				content_parttime=dayList.get(position).getContent();
				edit.setText(content_parttime);
				edit.setSelection(content_parttime.length());
			}
		}

		btn=(Button) findViewById(R.id.save);
		btn.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View arg0) {			
		// TODO Auto-generated method stub		
		String text=edit.getText().toString();
		Toast.makeText(SecondActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
		write(dayList,position,text);
	}


	private void write(List<Day> contentList_test, int position, String text_test) {
		// TODO Auto-generated method stub
		
		//1
		contentList_test=get();
		//2
		if(contentList_test.size()!=0)
		{
			if(contentList_test.get(position).getContent()!=text_test){
				contentList_test.get(position).content=text_test;
				save(contentList_test);
		}
		//3
		}else{
			init(contentList_test);
			save(contentList_test);
			contentList_test=read();
			if(contentList_test.get(position).getContent()!=text_test){
			contentList_test.get(position).content=text_test;
			save(contentList_test);}
		}
	}

	private void init(List<Day> contentList_test) {
		// TODO Auto-generated method stub
		for(int i=0; i<30; i++){
			Day day=new Day();
			day.content=new String ("  ");
			contentList_test.add(day);
		}
	}

	private void save(List<Day> contentList_test) {
		// TODO Auto-generated method stub
		try{
			FileOutputStream out=openFileOutput(fileName,Context.MODE_PRIVATE);
			ObjectOutputStream outObject=new ObjectOutputStream(out);
			outObject.writeObject(contentList_test);
			outObject.close();
			out.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private List<Day> read() {
		// TODO Auto-generated method stub
		return get();
	}




	private List<Day> get() {
		// TODO Auto-generated method stub
		List<Day> list = new ArrayList<Day>();
		
		try{
			FileInputStream in=openFileInput(fileName);
			ObjectInputStream inObject=new ObjectInputStream(in);
			list=(List<Day>) inObject.readObject();

		
			inObject.close();
			in.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return list;
	}









	




}


