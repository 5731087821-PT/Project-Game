package score;

@SuppressWarnings("serial")
public class ScoreParsingException extends Exception{

	private int errorType;
	
	public ScoreParsingException(int errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		String txt = "";
		if(errorType==0){
			txt = "No record score";
		}else{
			txt = "Wrong record format";
		}
		return txt;
	}
}
