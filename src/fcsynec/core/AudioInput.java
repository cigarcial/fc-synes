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
		if (data.peek() != null){
			int tmp = data.poll();
			System.out.println("Get from concurrent Queue to agent event #: " + tmp);
			return tmp;
		}
		return -1;
	}
	
	@Override
	public void run() {
		while(true){
			int tmp = si.getEvent();
			if (tmp != -1){
				System.out.println("Get from EventQueue to ConcurrentQueue event #: " + tmp);
				data.offer(tmp);
			}
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
	
	public void stopRec(){
		si.stopRecording();
	}
}
