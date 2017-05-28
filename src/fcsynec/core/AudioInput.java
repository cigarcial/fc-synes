package fcsynec.core;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.*;
import com.dazz.audio.*;

public class AudioInput implements Input{
	
	private ConcurrentLinkedDeque<Integer> data;
	private Thread thread,thread2;
	private SignalInput si;
	
	public AudioInput() {
		data = new ConcurrentLinkedDeque<>();
		si = new SignalInput();
		si.initInput();
		thread2 = new Thread(new StartRec());
		thread2.start();
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
			try{
				Thread.sleep(200);
			}catch(Exception ex){}
		}
	}
	
	private class StartRec implements Runnable{
		@Override
		public void run() {
			si.startRecording();
		}
		
	}
}
