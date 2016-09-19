package audio;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound{
	
	public static Sound sound1 = new Sound("/music/gameloop.wav");
	
	private AudioClip clip;
	
	public Sound(String filename){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(filename));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void loop(){
		try{
			new Thread(){
				public void run(){
					clip.loop();
				}
			}.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void stop(){
		clip.stop();
	}
}