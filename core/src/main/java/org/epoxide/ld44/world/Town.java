package org.epoxide.ld44.world;

import com.mursaat.dungeongenerator.*;
import org.epoxide.ld44.LD44;
import org.epoxide.ld44.bounty.Bounties;
import org.epoxide.ld44.bounty.IBounty;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;

import java.util.Random;

public class Town extends World {

    private IBounty[] bounties;
    private TileMap tileMap;

    public Town() {
        super(1);
        this.tileMap = new TileMap(150, 150);

        this.generateBounties();
        this.generate();
    }

    public void generateBounties() {
        this.bounties = Bounties.generateBounties(5);
    }

    @Override
    public TileMap getTileMap(int floor) {
        return this.tileMap;
    }

    @Override
    public void generate() {
        //TODO layout premade area
        DungeonGenerator dg = new DungeonGenerator();
        Dungeon dungeon = dg.generateDungeon();
        this.tileMap = new TileMap(dungeon.getWidth(), dungeon.getHeight());
        DungeonStructure[][] tiles = dungeon.getTiles();
    
        for(int y = 0; y < dungeon.getHeight(); y++) {
            for(int x = 0; x < dungeon.getWidth(); x++) {
                if (tiles[y][x] instanceof DungeonRoom) {
                    tileMap.setTile(x,y,Tiles.STONE);
                } else if (tiles[y][x] instanceof DungeonHallway) {
                    tileMap.setTile(x,y,Tiles.PATH);
                } else {
                    //TODO replace this with a void tile or something
                    tileMap.setTile(x,y,Tiles.GRASS);
                }
            }
        
        }
    
    
        DungeonRoom randRoom = dungeon.getRooms().get(new Random().nextInt(dungeon.getRooms().size()));
        LD44.getInstance().getClientPlayer().setX(randRoom.getX() + randRoom.getWidth()/2);
        LD44.getInstance().getClientPlayer().setY(randRoom.getY() + randRoom.getHeight()/2);
    
        //        for (int x = 0; x < this.tileMap.getWidth(); x++) {
//            for (int y = 0; y < this.tileMap.getHeight(); y++) {
//                tileMap.setTile(x, y, Tiles.GRASS);
//            }
//        }

////        CUBES
//        tileMap.setTile(1, 1, Tiles.LAVA);
//        tileMap.setTile(2, 2, Tiles.LAVA);
//        tileMap.setTile(1, 2, Tiles.LAVA);
//        tileMap.setTile(2, 1, Tiles.LAVA);
//
//        tileMap.setTile(10, 1, Tiles.LAVA);
//        tileMap.setTile(11, 2, Tiles.LAVA);
//        tileMap.setTile(10, 2, Tiles.LAVA);
//        tileMap.setTile(11, 1, Tiles.LAVA);
//
//        tileMap.setTile(1, 10, Tiles.LAVA);
//        tileMap.setTile(2, 11, Tiles.LAVA);
//        tileMap.setTile(2, 10, Tiles.LAVA);
//        tileMap.setTile(1, 11, Tiles.LAVA);
//
//        tileMap.setTile(10, 10, Tiles.LAVA);
//        tileMap.setTile(11, 11, Tiles.LAVA);
//        tileMap.setTile(10, 11, Tiles.LAVA);
//        tileMap.setTile(11, 10, Tiles.LAVA);
//
//
////        Triangle
//        tileMap.setTile(5, 2, Tiles.LAVA);
//        tileMap.setTile(6, 2, Tiles.LAVA);
//        tileMap.setTile(7, 2, Tiles.LAVA);
//        tileMap.setTile(6, 1, Tiles.LAVA);
//
//
//        tileMap.setTile(2, 5, Tiles.LAVA);
//        tileMap.setTile(2, 6, Tiles.LAVA);
//        tileMap.setTile(2, 7, Tiles.LAVA);
//        tileMap.setTile(1, 6, Tiles.LAVA);
//
//        tileMap.setTile(5, 10, Tiles.LAVA);
//        tileMap.setTile(6, 10, Tiles.LAVA);
//        tileMap.setTile(7, 10, Tiles.LAVA);
//        tileMap.setTile(6, 11, Tiles.LAVA);
//
//        tileMap.setTile(10, 5, Tiles.LAVA);
//        tileMap.setTile(10, 6, Tiles.LAVA);
//        tileMap.setTile(10, 7, Tiles.LAVA);
//        tileMap.setTile(11, 6, Tiles.LAVA);
//
////        CROSS
//
//        tileMap.setTile(14, 5, Tiles.LAVA);
//        tileMap.setTile(14, 6, Tiles.LAVA);
//        tileMap.setTile(14, 7, Tiles.LAVA);
//        tileMap.setTile(13, 6, Tiles.LAVA);
//        tileMap.setTile(15, 6, Tiles.LAVA);
//
//
//        // INNER
//        tileMap.setTile(6, 6, Tiles.LAVA);
//
////      OUTER
//        tileMap.setTile(4, 4, Tiles.LAVA);
//        tileMap.setTile(5, 4, Tiles.LAVA);
//        tileMap.setTile(6, 4, Tiles.LAVA);
//        tileMap.setTile(7, 4, Tiles.LAVA);
//        tileMap.setTile(8, 4, Tiles.LAVA);
//
//        tileMap.setTile(4, 8, Tiles.LAVA);
//        tileMap.setTile(5, 8, Tiles.LAVA);
//        tileMap.setTile(6, 8, Tiles.LAVA);
//        tileMap.setTile(7, 8, Tiles.LAVA);
//        tileMap.setTile(8, 8, Tiles.LAVA);
//
//        tileMap.setTile(4, 5, Tiles.LAVA);
//        tileMap.setTile(4, 6, Tiles.LAVA);
//        tileMap.setTile(4, 7, Tiles.LAVA);
//
//        tileMap.setTile(8, 5, Tiles.LAVA);
//        tileMap.setTile(8, 6, Tiles.LAVA);
//        tileMap.setTile(8, 7, Tiles.LAVA);
    }
}
