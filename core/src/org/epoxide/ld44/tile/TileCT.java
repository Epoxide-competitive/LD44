package org.epoxide.ld44.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import javafx.util.Pair;
import org.epoxide.ld44.client.tile.*;

import java.util.*;

public class TileCT extends Tile {
    
    
    public TileCT(String id) {
        super(id);
    }
    
    
    private Map<Pair<Integer, Integer>, LinkedList<Quad>> quads;
    
    public void retrieveQuads(TextureAtlas atlas) {
        quads = new HashMap<Pair<Integer, Integer>, LinkedList<Quad>>();
        
        TextureRegion[][] subTiles = atlas.findRegion(this.getIdentifier().toString()).split(16, 16);
        Quad base = new Quad(subTiles[0][0]);
        for(int x = -1; x < 2; x++) {
            for(int y = -1; y < 2; y++) {
                LinkedList<Quad> quadss = new LinkedList<Quad>();
                quadss.add(base);
                quads.put(new Pair<Integer, Integer>(x, y), quadss);
            }
        }
        
        //        quads.get(new Pair<Integer, Integer>(-1,-1)).add(new Quad(subTiles[1][1].split(8,8)[0][0],8,8));
        //        quads.get(new Pair<Integer, Integer>(-1,-1)).add(new Quad(subTiles[1][0].split(8,16)[0][0]));
        
        quads.get(new Pair<Integer, Integer>(0, 0)).add(new ScaledQuad(subTiles[1][0].split(16, 8)[0][0],1,0.5f));
//        quads.get(new Pair<Integer, Integer>(0, 0)).add(new ScaledQuad(subTiles[1][0].split(16, 8)[1][0],1,0.5f));
//        quads.get(new Pair<Integer, Integer>(0, 0)).add(new ScaledQuad(subTiles[0][1].split(8, 16)[0][0],0.5f,1));
//        quads.get(new Pair<Integer, Integer>(0, 0)).add(new ScaledQuad(subTiles[0][1].split(8, 16)[0][1],0.5f,1));
        
    }
    
    public List<Quad> getQuads(TileMap tileMap, int x, int y) {
        List<Quad> quads = new LinkedList<Quad>();
        
        for(int xx = -1; xx < 2; xx++) {
            for(int yy = -1; yy < 2; yy++) {
                try {
                    if(tileMap.getTile(x + xx, y + yy) == this) {
                        quads.addAll(this.quads.get(new Pair<Integer, Integer>(xx, yy)));
                    }
                } catch(Exception e) {
                    Gdx.app.log("tyler", "pls make this not crash when x || y ==0 and it does -1 on that and it throws an AIOOBE");
                }
            }
        }
        
        
        return quads;
    }
    
}