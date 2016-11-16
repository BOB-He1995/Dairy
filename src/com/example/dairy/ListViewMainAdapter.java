package com.example.dairy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ListViewMainAdapter extends ArrayAdapter {
	
	private int resourceId;
	
	public ListViewMainAdapter(Context context, int textViewResourceId, List<ListViewMain> objects){
		
		super(context, textViewResourceId, objects);
		resourceId=textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		ListViewMain listviewmain=(ListViewMain) getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.pointImage=(ImageView) view.findViewById(R.id.point);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.pointImage.setImageResource(listviewmain.getImageId());
		return view;
	}
	
	class ViewHolder{
		ImageView pointImage;
	}
}
