package com.iconnic.worktemplate.fragments;

import java.util.ArrayList;
import java.util.List;

import com.futuremind.recyclerviewfastscroll.FastScroller;
import com.iconnic.worktemplate.MainActivity;
import com.iconnic.worktemplate.R;
import com.iconnic.worktemplate.adapters.CardContacts1Adapter;
import com.iconnic.worktemplate.adapters.CardPost1Adapter;
import com.iconnic.worktemplate.others.NpaGridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Posts extends Fragment {
	private static Activity activity; private static Context context; private static View view;
	
	private static List<String> text=new ArrayList<String>(); 
	private static List<String> pic=new ArrayList<String>();
	
	private static RecyclerView recycler; public static RecyclerView.Adapter adapter; 
	private static SwipeRefreshLayout swiperefresh;
	final static int gridnum=1;
	
	@Override
	public View onCreateView(LayoutInflater inflator,ViewGroup container,Bundle savedInstanceState){
		view = inflator.inflate(R.layout.posts,container,false);
		activity=MainActivity.activity; context=MainActivity.context; 
		
		clear();
		fakeData();
		initOthers();
		initcontent();
		initRecycler();
		
		return view;
	}
	
private void clear(){text.clear(); pic.clear();}
	
	private void initcontent(){}
	
	private void initOthers(){
		swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);  
        swiperefresh.setColorSchemeResources(R.color.black,R.color.view);        
        swiperefresh.setOnRefreshListener(new OnRefreshListener(){@Override public void onRefresh(){refreshByPull();}});
	}
	
	private void refreshByPull(){clear(); fakeData(); refreshRecyclerAll(); swiperefresh.setRefreshing(false);}
	
	public void initRecycler(){	
		double viewwidth=((float)(MainActivity.thewidth))/gridnum; int thewidth=(int) viewwidth;
		double viewheight=((float)(MainActivity.theheight))/3; int theheight=(int) viewheight;
			
		recycler=(RecyclerView)view.findViewById(R.id.recycler); 
        final NpaGridLayoutManager mLayoutManager = new NpaGridLayoutManager(getActivity(),gridnum);
        recycler.setLayoutManager(mLayoutManager); recycler.setHasFixedSize(true);
                
        adapter = new CardPost1Adapter(pic,text,text,text,text,thewidth,theheight,"Third",gridnum,context); recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener(){
        	@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState){super.onScrollStateChanged(recyclerView, newState);}
        	
        	@Override
        	public void onScrolled(RecyclerView recyclerView, int dx, int dy){super.onScrolled(recyclerView, dx, dy);
        		//if(!loading && mLayoutManager.getItemCount()-visibleThreshold<=mLayoutManager.findLastVisibleItemPosition()){
        			//loading=true; if(done==true){return;} currentposition=mLayoutManager.findFirstCompletelyVisibleItemPosition();
        			//dontload=0; loadMoreData();
        			//}
        	}
        });
	}
	
	private static void refreshRecyclerAll(){
		double viewwidth=((float)(MainActivity.thewidth))/gridnum; int thewidth=(int) viewwidth;
		double viewheight=((float)(MainActivity.theheight))/3; int theheight=(int) viewheight;
		
		adapter = new CardPost1Adapter(pic,text,text,text,text,thewidth,theheight,"Third",gridnum,context);
        recycler.setAdapter(adapter); adapter.notifyDataSetChanged();
    }
    
    private void refreshRecycler(){adapter.notifyItemRangeInserted(adapter.getItemCount(), text.size() - 1);}
	
	private void fakeData(){
		for(int i=0;i<MainActivity.temptext.length/2;i++){
			text.add("Link "+MainActivity.temptext[i]); pic.add(MainActivity.temppic[i]); 
		}
		for(int i=0;i<MainActivity.temptext.length/2;i++){
			text.add("Text "+MainActivity.temptext[i]); pic.add(MainActivity.temppic[i]); 
		}
		for(int i=0;i<MainActivity.temptext.length/2;i++){
			text.add("Gif "+MainActivity.temptext[i]); pic.add(MainActivity.temppic[i]); 
		}
		for(int i=0;i<MainActivity.temptext.length/2;i++){
			text.add("Audio "+MainActivity.temptext[i]); pic.add(MainActivity.temppic[i]); 
		}
	}

}