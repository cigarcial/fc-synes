package com.dazz.audio;

import java.io.*;

public class SoundProcessing implements Runnable{

 static double i = 0.0;
 static SignalInput si;
 
 
 public static void main (String[] args) throws IOException{
	 
	 si = new SignalInput();
	 si.initInput();	
	 //si.startRecording();
	 
	 new Thread(new SoundProcessing()).start();

	 while(true){
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String s = br.readLine();
		 if (s.equals("e")){
			 int tmp = si.getEvent();
			 if (tmp != -1)
				 System.out.println(tmp);
		 }
	 }


 }
 
	@Override
	public void run() {
		si.startRecording();
	}

}