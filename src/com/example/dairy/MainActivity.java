package com.example.dairy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button btn_month;
	
	private Button btn_year;
	
	private String []month={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
	
	private String []year={"2015年","2016年","2017年","2018年"};
	
	private List<ListViewMain> pointList=new ArrayList<ListViewMain>();
	
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btn_month=(Button) findViewById(R.id.button_month);
		btn_month.setOnClickListener(this);		//为月份按钮设置监听器
		
		btn_year=(Button) findViewById(R.id.button_year);
		btn_year.setOnClickListener(this);
		
		initPointList();		//配置listview
		ListViewMainAdapter pointAdapter=new ListViewMainAdapter(MainActivity.this, R.layout.listview_main_item, pointList);
		ListView listView_point=(ListView) findViewById(R.id.listView_main);
		listView_point.setAdapter(pointAdapter);
		
		listView_point.setOnItemClickListener(new OnItemClickListener() {		//为item设置点击事件
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				String data=String.valueOf(position);
				Intent intent=new Intent("action_1");
				intent.putExtra("extra_data", data);
				startActivity(intent);
			}
			
		});
	}

		
	public void initPointList(){		//初始化点
		for(int i=0; i<30; i++){
		ListViewMain point=new ListViewMain(R.drawable.button);
		pointList.add(point);
		}
	}


	@Override
	public void onClick(View v) {				
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.button_month:		//月份按钮点击事件
			AlertDialog.Builder dialog_month=new AlertDialog.Builder(MainActivity.this);
			View view_month=LayoutInflater.from(MainActivity.this).inflate(R.layout.month_list,null);
			ArrayAdapter<String> monthAdapter=new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1,month);
			ListView listView_month=(ListView) view_month.findViewById(R.id.month_ListView);
			listView_month.setAdapter(monthAdapter);
			dialog_month.setView(view_month);
			dialog_month.show();
			break;
		
		case R.id.button_year:		//年份按钮点击事件
			AlertDialog.Builder dialog_year=new AlertDialog.Builder(MainActivity.this);
			View view_year=LayoutInflater.from(MainActivity.this).inflate(R.layout.year_list,null);
			ArrayAdapter<String> yearAdapter=new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1, year);
			ListView listView_year=(ListView) view_year.findViewById(R.id.year_ListView);
			listView_year.setAdapter(yearAdapter);
			dialog_year.setView(view_year);
			dialog_year.show();
			break;
			
		
		default:
			break;
		
		}
	}
	
}
