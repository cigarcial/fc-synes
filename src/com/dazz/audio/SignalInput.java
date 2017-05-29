package com.dazz.audio;

//Felipe Villarreal Daza: Universidad Nacional de Colombia - 2017

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class SignalInput {
	
	private AudioFormat audioFormat;
	private TargetDataLine audioInput;
	private SourceDataLine audioOutput;
	private int bufferSize = 512,filterWeight = 100, lowCutOff = 85, highCutOff = 1500;
	private float sampleFrequency = 8000.0f;
	private byte[] sound;
	private boolean recordFlag = false;
	private double[] freqs = new double[6];
	private double uFreq;
	private double[] noteFreqs = {125,140.625,171.875,203.125,218.75,250.0};
	private ConcurrentLinkedDeque<Integer> eventQueue = new ConcurrentLinkedDeque<>();
	
	public SignalInput(){
		//Define sample frequency, bits/sample, channels.
		audioFormat = new AudioFormat(sampleFrequency, 16, 1, true, true);
		sound = new byte[bufferSize];
	}
	
	//Prepare microphone interface
	public void initInput(){
		try{
			audioInput = (TargetDataLine) AudioSystem.getTargetDataLine(audioFormat);
			if (!AudioSystem.isLineSupported(audioInput.getLineInfo())) {
				  System.out.println("No audio input available");	//Handle error
			}
			audioInput.open(audioFormat,bufferSize);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//Prepare speaker interface
	public void initOutput(){
		try{
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
		  	audioOutput = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			if (!AudioSystem.isLineSupported(audioInput.getLineInfo())) {
				  System.out.println("No audio output available");	//Handle error
			}
			audioOutput.open(audioFormat,bufferSize);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//Start recording audio
	public void startRecording(){
		recordFlag = true;
		record();
		
	}
	
	//Stop recording audio
	public void stopRecording(){
		recordFlag = false;
		audioInput.stop();
		audioInput.drain();
		audioInput.close();
	}
	
	//Actual method that takes sound input
	public void record(){
		int secCounter = 0;
		if (recordFlag) audioInput.start();
		while (recordFlag){
			//Read data from audioInput buffer to Byte array
			audioInput.read(sound, 0, sound.length);
			
			//Get bits/sample in AudioFormat.
			int bits = audioFormat.getSampleSizeInBits();
			double max = Math.pow(2, bits - 1);
			//create double array with power of 2 length for fft
			int ls = (int) Math.pow(2, Math.ceil(Math.log(sound.length * 8 / bits) / Math.log(2)));
			double[] samples = new double[ls];
			//Create ByteBuffer to parse from byte to double
			ByteBuffer bb = ByteBuffer.wrap(sound);
			bb.order(audioFormat.isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
   
			//Buffer has less samples than samples.length (Power of 2).
			//We only take the # of Bytes in the buffer and place them in samples array to void BufferUnderFlow
			for(int j = 0; j < sound.length * 8 / bits ; j++) {
				samples[j] = ( bb.getShort()  / max  );
			}

			//fft over the samples with the sound
			double max_magnitude = -1 * Double.POSITIVE_INFINITY;
			double max_index = -1;
			double[] result = Fft.fft(samples,new double[samples.length],true);
			double[] magnitude = new double[result.length / 2];
			for( int j = 0 ; j < result.length / 2 - 1 ; j++){
				double re = result[2*j];
				double im = result[2*j+1];
				magnitude[j] = Math.sqrt(re*re+im*im);	//Calculate index magnitude
				//Apply magniute threshold
				if (magnitude[j] < 0.1)
					magnitude[j] = 0;
				//Apply band-pass filter to magnitude array
				if (j * sampleFrequency / result.length < lowCutOff || j * sampleFrequency / result.length > highCutOff){
					magnitude[j] /= filterWeight;
				}
				for (int k = 0; k < result.length / 2 - 1 ; k++){
					if (magnitude[k] > max_magnitude){
						max_magnitude = magnitude[k];
						max_index = k;
					}
				}
			}
			
			
			if(secCounter < 6){
				freqs[secCounter++] = max_index * sampleFrequency / result.length;	//Get frequency of the index of greater magnitude.
			}
			else{
				//Get the mode of the 6 samples with the most magnitude each 6*512 Bytes of audio. (200ms)
				uFreq = mode(freqs);
				//Depending on frequency, send integer as event.
				//eventQueue.offer(new Random().nextInt(7)+1);
				
				if (uFreq == noteFreqs[0]){
					eventQueue.offer(1);
					System.out.println("Note: C");
				}
				else if (uFreq == noteFreqs[1]){
					eventQueue.offer(2);
					System.out.println("Note: D");
				}
				else if (uFreq == noteFreqs[2]){
					eventQueue.offer(3);
					System.out.println("Note: E");
				}
				else if (uFreq == noteFreqs[3]){
					eventQueue.offer(4);
					System.out.println("Note: F");
				}
				else if (uFreq == noteFreqs[4]){
					eventQueue.offer(5);
					System.out.println("Note: G");
				}
				else if (uFreq == noteFreqs[5]){
					eventQueue.offer(6);
					System.out.println("Note: A");
				}
				else if (uFreq == noteFreqs[5]){
					eventQueue.offer(6);
					System.out.println("Note: S");
				}
				

				secCounter = 0;
				freqs = new double[6];
				freqs[secCounter++] = max_index * sampleFrequency / result.length;
			}
		}
	}
	
	//Query for notes processed in queue
	public int getEvent(){
		return eventQueue.size() == 0 ? -1 : eventQueue.poll();
	}
	
	//Get the Mode of an array in O(n) time.
	public static double mode(double []array){
		HashMap<Double,Integer> hm=new HashMap<Double,Integer>();
		int max=1;
		double temp = 0;
		for(int i=0;i<array.length;i++){
	        if(hm.get(array[i])!=null){
	        	int count=hm.get(array[i]);
	        	count=count+1;
	        	hm.put(array[i],count);
		        if(count>max){
		        	max=count;
		            temp=array[i];
		        }
	        }
	        else{
	        	hm.put(array[i],1);
	        }
	    }
		return temp;
	}


}