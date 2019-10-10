package skyWatch;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import com.sun.media.controls.BooleanControl;

public class Music4 {
	
	public static Clip clip;
	
	public static boolean pauseSong;
	
	public static synchronized void play(final String fileName){
		
		new Thread(new Runnable() { 
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start(); 
                    
                    if(pauseSong) {
                    	
                    		clip.stop();
                    }
                 
                    
                    
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();

}
	

}
