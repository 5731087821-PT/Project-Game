package render;
import java.util.*;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();;
	private ArrayList<IRenderable> entities;
	
	public static RenderableHolder getInstance(){
		return instance;
	}
	
	public RenderableHolder(){
		entities = new ArrayList<IRenderable>();
	}
	
	public void add (IRenderable object){
		this.entities.add(object);
		Collections.sort(entities, new Comparator<IRenderable>() {

			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				// TODO Auto-generated method stub
				if(o1.getZ()>o2.getZ()) return 1;
				else return -1;
			}
		});
		
		
	}
	
	public ArrayList<IRenderable> getRenderableList(){
		return entities;
	}
	
}