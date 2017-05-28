package fcsynec.core;


import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.*;
import com.dazz.audio.*;

public class DummyInput implements Input{

	
	private ConcurrentLinkedDeque<Integer> data;
	private Thread thread;
	
	public DummyInput() {
		data = new ConcurrentLinkedDeque<>();
		si = new SignalInput();
		si.initInput();
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public int next() {
		return (data.peek() == null)? 0 : data.poll();
	}
	
	@Override
	public void run() {
		while(true){
			int tmp = si.getEvent();
			if (tmp != -1)
				data.offer(tmp);
			//data.offer(new Random().nextInt(7)+1);
			try{
				Thread.sleep(200);
			}catch(Exception ex){}
		}
	}

}
