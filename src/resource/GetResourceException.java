package resource;

@SuppressWarnings("serial")
public class GetResourceException extends RuntimeException {
	public static final int ANIMATION = 0;
	public static final int AUDIO = 1;
	private int errorType;
	private String key;
	
	public GetResourceException(int errorType,String key){
		this.errorType = errorType;
		this.key = key;
	}
	
	@Override
	public String getMessage(){
		String txt = "";
		if(errorType==ANIMATION){
			txt = "Animation Key is incorrect : " + key;
		}else{
			txt = "AudioClip Key is incorrect : " + key;
		}
		return txt;
	}

}
