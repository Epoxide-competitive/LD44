package org.epoxide.ld44.world.locations;

import com.mursaat.dungeongenerator.*;
import org.epoxide.ld44.tile.Tile;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;
import org.epoxide.ld44.world.World;

import java.util.List;

public class Location extends World {

    private final int minRooms;
    private final int maxRooms;
    private TileMap tileMap;

    public Location(int floorCount, int minRooms, int maxRooms) {
        super(floorCount);
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
    }

    @Override
    public TileMap getTileMap(int floor) {
        return this.tileMap;
    }

    @Override
    public void generate() {
        DungeonParams dungeonParams = new DungeonParams();
        dungeonParams.setHallwaysWidth(3);
        dungeonParams.setMinSpaceBetweenRooms(2);
        dungeonParams.setMinRoomCount(this.minRooms);
        dungeonParams.setMaxRoomCount(this.maxRooms);

        RoomParams roomParams = new RoomParams();
        roomParams.setMinWidth(5);
        roomParams.setMaxWidth(10);
        roomParams.setMinHeight(5);
        roomParams.setMaxHeight(10);


        DungeonGenerator myGenerator = new DungeonGenerator(dungeonParams, roomParams);
        Dungeon dungeon = myGenerator.generateDungeon();


        this.tileMap = new TileMap(dungeon.getWidth(), dungeon.getHeight());

//        TODO Generate room content
        List<DungeonRoom> roomList = dungeon.getRooms();
        for (DungeonRoom room : roomList) {
            for (int x = 0; x < room.getWidth(); x++) {
                for (int y = 0; y < room.getHeight(); y++) {
                    if (x == 0 || y == 0 || x == room.getWidth() - 1 || y == room.getHeight() - 1)
                        tileMap.setTile(x + room.getX(), y + room.getY(), Tiles.STONE_BRICKS);
                    else
                        tileMap.setTile(x + room.getX(), y + room.getY(), Tiles.STONE);
                }
            }
        }
        DungeonStructure[][] structures = dungeon.getTiles();
        System.out.println(structures.length);
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                Tile tile = null;
                DungeonStructure structure = structures[y][x];

                if (structure instanceof DungeonHallway)
                    tile = Tiles.PATH;
                else if (tileMap.getTile(x, y) == null)
                    tile = Tiles.GRASS;
                if (tile != null)
                    this.tileMap.setTile(x, y, tile);
            }
        }
    }
}
