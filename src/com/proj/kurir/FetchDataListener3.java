package com.proj.kurir;

import java.util.List;

public interface FetchDataListener3 {
	public void onFetchComplete(List<Application3> data);
	public void onFetchFailure(String msg);

}
