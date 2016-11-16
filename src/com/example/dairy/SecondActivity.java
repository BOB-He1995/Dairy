package com.example.dairy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
	
	private Button btn_save;
	
	private String inputText;
	
	private String data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent intent=getIntent();
		data=intent.getStringExtra("extra_data");
		edit=(EditText) findViewById(R.id.edit);
		btn_save=(Button) findViewById(R.id.save);
		btn_save.setOnClickListener(this);
		inputText=load();
		edit.setText(inputText);
		edit.setSelection(inputText.length());
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String inputText=edit.getText().toString();
		save(inputText);
		Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
		
	}

	
//	@Override
//	protected void onDestroy(){		//设置销毁函数机制
	//	super.onDestroy();
	//	String inputText=edit.getText().toString();
	//	save(inputText);
//	}
	

	private void save(String inputText) {	//文件保存函数
		// TODO Auto-generated method stub
		FileOutputStream out=null;
		BufferedWriter writer=null;
		try{
			out=openFileOutput(data,Context.MODE_PRIVATE);
			writer=new BufferedWriter(new OutputStreamWriter(out));
			writer.write(inputText);
		} catch(IOException e)	{
			e.printStackTrace();
		} finally{
			try{
				if (writer!=null){
					writer.close();
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public String load(){
		FileInputStream in=null;
		BufferedReader reader=null;
		StringBuilder content=new StringBuilder();
		try{
			in=openFileInput(data);
			reader=new BufferedReader(new InputStreamReader(in));
			String line="";
			while((line=reader.readLine())!=null){
				content.append(line);
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try{
					reader.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}


	
}


