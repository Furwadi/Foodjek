package com.proj.kurir;

import java.util.List;

public interface FetchDataListener4 {
	public void onFetchComplete(List<Application4> data);
	public void onFetchFailure(String msg);
}
