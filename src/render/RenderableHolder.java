package render;

import java.util.*;

public class RenderableHolder {

	//Singleton pattern with eager initialization
	private static final RenderableHolder instance = new RenderableHolder();
	public static RenderableHolder getInstance(){
		return instance;
	}
	
	private List<IRenderable> northEntities;
	private List<IRenderable> southEntities;
	
	public RenderableHolder(){
		northEntities = new ArrayList<IRenderable>();
		southEntities = new ArrayList<IRenderable>();
	}

	public synchronized void addNorthEntity(IRenderable entity){
		northEntities.add(entity);
		//Sort our list by Z -- we don't sort during "image drawing" as it's not efficient
		Collections.sort(northEntities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				if(o1.getZ() > o2.getZ()) return 1;
				return -1;
			}
		});
	}
	
	public synchronized void addSouthEntity(IRenderable entity){
		southEntities.add(entity);
		//Sort our list by Z -- we don't sort during "image drawing" as it's not efficient
		Collections.sort(southEntities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable o1, IRenderable o2) {
				if(o1.getZ() > o2.getZ()) return 1;
				return -1;
			}
		});
	}
	
	public synchronized List<IRenderable> getNorthRenderableList(){
		return northEntities;
	}
	
	public synchronized List<IRenderable> getSouthRenderableList(){
		return southEntities;
	}
}
