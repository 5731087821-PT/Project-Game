package resource;

@SuppressWarnings("serial")
public class FethResourceException extends Exception {
	public static final int ANIMATION = 0;
	public static final int AUDIO = 1;
	private int errorType;
	private String url;
	
	public FethResourceException(int errorType,String url){
		this.errorType = errorType;
		this.url = url;
	}
	
	@Override
	public String getMessage(){
		String txt = "";
		if(errorType==ANIMATION){
			txt = "Animation file is not found : " + url;
		}else{
			txt = "AudioClip file is not found : " + url;
		}
		return txt;
	}

}
