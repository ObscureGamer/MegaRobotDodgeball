package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creatures.Jock;
import entities.creatures.Player;
import game.Handler;

public class EntityManager {

	public ArrayList<Jock> jocks;
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		public int compare(Entity a, Entity b){
			if(a.getY() +a.getHeight()<b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		jocks = new ArrayList<Jock>();
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive())
				entities.remove(e);
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.render(g);
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addJock(Jock e){
		jocks.add(e);
	}
	
	public ArrayList<Jock> getJock(){
		return jocks;
	}
	
	public void removeJock(Jock e){
		jocks.remove(e);
	}
	
}
