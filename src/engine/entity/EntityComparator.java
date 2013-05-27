package engine.entity;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity>{

	@Override
	public int compare(Entity entity1, Entity entity2) {
		
		int result = 0;
		if(entity1.getzRenderIndex() < entity2.getzRenderIndex()) {
			result = -1;
		} else if(entity1.getzRenderIndex() > entity2.getzRenderIndex()) {
			result = 1;
		}
		
		return result;
	}

}
