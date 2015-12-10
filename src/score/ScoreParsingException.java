package score;

public class ScoreParsingException extends Exception{

	private int errorType;
	
	public ScoreParsingException(int errorType){
		/* fill code */
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		/* fill code */
		String txt = "";
		if(errorType==0){
			txt = "No record score";
		}else{
			txt = "Wrong record format";
		}
		return txt;
	}
}
