package fcsynec.core;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.*;
public class DummyInput implements Input{

	
	private ConcurrentLinkedDeque<Integer> data;
	
	public DummyInput() {
		data = new ConcurrentLinkedDeque<>();
	}

	@Override
	public int next() {
		return (data.peek() == null)? 0 : data.poll();
	}
	
	@Override
	public void run() {
		while(true){
			data.offer(new Random().nextInt(7)+1);
			try{
				Thread.sleep(200);
			}catch(Exception ex){}
		}
	}

}
