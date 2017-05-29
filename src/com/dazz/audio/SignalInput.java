package com.dazz.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;

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
	private double[] freqs = new double[25];
	private double uFreq;
	private double[] noteFreqs = {125,140.625,171.875,203.125,218.75,250.0};
	private ArrayList<Integer> eventQueue = new ArrayList<>();
	
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
		audioInput.close();
	}
	
	//Actual method that takes sound input
	public void record(){
		int secCounter = 0;
		if (recordFlag) audioInput.start();
		while (recordFlag){
			audioInput.read(sound, 0, sound.length);
			
			int bits = audioFormat.getSampleSizeInBits();
			double max = Math.pow(2, bits - 1);
			//create double array with power of 2 length for fft
			int ls = (int) Math.pow(2, Math.ceil(Math.log(sound.length * 8 / bits) / Math.log(2)));
			double[] samples = new double[ls];
			//Create ByteBuffer to parse from byte to double
			ByteBuffer bb = ByteBuffer.wrap(sound);
			bb.order(audioFormat.isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
   
			//El buffer tiene menos datos que la longitud del array entonces
			//solo traemos del buffer los datos del sonido a los samples.
			for(int j = 0; j < sound.length * 8 / bits ; j++) {
				//System.out.println(j);
				samples[j] = ( bb.getShort()  / max  );
				//System.out.println(samples[j]);
			}

			//fft over the samples with the sound
			double max_magnitude = -1 * Double.POSITIVE_INFINITY;
			double max_index = -1;
			double[] result = Fft.fft(samples,new double[samples.length],true);
			double[] magnitude = new double[result.length / 2];
			for( int j = 0 ; j < result.length / 2 - 1 ; j++){
				double re = result[2*j];
				double im = result[2*j+1];
				magnitude[j] = Math.sqrt(re*re+im*im);
				//Apply magniute threshold
				if (magnitude[j] < 0.1)
					magnitude[j] = 0;
				//Apply band-pass filter to magnitude array
				if (j * 8000 / result.length < lowCutOff || j * sampleFrequency / result.length > highCutOff){
					magnitude[j] /= filterWeight;
				}
				for (int k = 0; k < result.length / 2 - 1 ; k++){
					if (magnitude[k] > max_magnitude){
						max_magnitude = magnitude[k];
						max_index = k;
					}
				}
				//System.out.println("Max mag: " + max_magnitude);
			}
			
			if(secCounter < 25){
				freqs[secCounter++] = max_index * sampleFrequency / result.length;	
			}
			else{
				uFreq = mode(freqs);
				//System.out.println(uFreq);
				//Depending on frequency, send integer as event.
				if (uFreq == noteFreqs[0]){
					eventQueue.add(0);
					System.out.println("Añadí Evento 0!");
				}
				else if (uFreq == noteFreqs[1]){
					eventQueue.add(1);
					System.out.println("Añadí Evento 1!");
				}
				else if (uFreq == noteFreqs[2]){
					eventQueue.add(2);
					System.out.println("Añadí Evento 2!");
				}
				else if (uFreq == noteFreqs[3]){
					eventQueue.add(3);
					System.out.println("Añadí Evento 3!");
				}
				else if (uFreq == noteFreqs[4]){
					eventQueue.add(4);
					System.out.println("Añadí Evento 4!");
				}
				else if (uFreq == noteFreqs[5]){
					eventQueue.add(5);
					System.out.println("Añadí Evento 5!");
				}
				
				//System.out.println(eventQueue);
				
				secCounter = 0;
				freqs = new double[25];
				freqs[secCounter++] = max_index * sampleFrequency / result.length;
			}
		}
	}
	
	//Query for notes processed in queue
	public int getEvent(){
		return eventQueue.size() == 0 ? -1 : eventQueue.remove(0);
	}
	
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
