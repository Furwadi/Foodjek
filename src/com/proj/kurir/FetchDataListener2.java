package com.proj.kurir;

import java.util.List;

public interface FetchDataListener2 {
	public void onFetchComplete(List<Application2> data);
	public void onFetchFailure(String msg);
}
