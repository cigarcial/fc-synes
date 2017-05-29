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
		thread = new Thread(this);
		thread.start();
	}
	
	
	public void stopRecording(){
		si.stopRecording();
	}

	@Override
	public int next() {
		try{
					Thread.sleep(200);
		}catch(Exception ex){}
		int tmp = si.getEvent();
		if (tmp != -1){
			//System.out.println("Get from EventQueue to Agent event #: " + tmp);
			//data.offer(tmp);
			return tmp;
		}
		return 0;
	
	}
	
	@Override
	public void run() {
		si.startRecording();
	}
	
}
