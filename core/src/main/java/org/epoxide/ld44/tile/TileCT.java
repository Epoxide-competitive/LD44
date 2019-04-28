package org.epoxide.ld44.tile;

import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.epoxide.ld44.utilities.Direction;
import org.epoxide.ld44.client.tile.Quad;
import org.epoxide.ld44.client.tile.ScaledQuad;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TileCT extends Tile {

    private final int[] ret = new int[]{18, 19, 17, 16};
    private final int[] submapOffsets = {4, 5, 1, 0};
    private Quad[] quadMap = new Quad[20];
    private Map<Direction, Boolean> connected = new HashMap<Direction, Boolean>();
    private List<Quad> quadList = new LinkedList<Quad>();

    public TileCT(String id) {
        super(id);
    }

    @Override
    public void registerTextures(PixmapPacker packer) {
        super.registerTextures(packer);

        packer.pack(this.getIdentifier().toString() + "_ctm", getTileSpriteData(this.getIdentifier().getName() + "_ctm"));
    }

    public void retrieveQuads(TextureAtlas atlas) {
        TextureRegion[][] baseTile = atlas.findRegion(this.getIdentifier().toString()).split(8, 8);
        TextureRegion[][] subTiles = atlas.findRegion(this.getIdentifier().toString() + "_ctm").split(8, 8);

        for (int i = 0; i < 16; i++) {
            int x = i % 4;
            int y = (int) Math.floor(i / 4);
            float offsetX = i % 2 == 0 ? 0 : 0.5f;
            float offsetY = (y == 1 || y == 3) ? 0.5f : 0f;
            quadMap[i] = new ScaledQuad(subTiles[y][x], offsetX, offsetY, 0.5f, 0.5f);
        }
        quadMap[16] = new ScaledQuad(baseTile[0][0], 0, 0, 0.5f, 0.5f);
        quadMap[17] = new ScaledQuad(baseTile[0][1], 0.5f, 0f, 0.5f, 0.5f);
        quadMap[18] = new ScaledQuad(baseTile[1][0], 0, 0.5f, 0.5f, 0.5f);
        quadMap[19] = new ScaledQuad(baseTile[1][1], 0.5f, 0.5f, 0.5f, 0.5f);


    }

    public List<Quad> getQuads(TileMap tileMap, int x, int y) {
        quadList.clear();
        connected.clear();
        boolean all = true;

        for (Direction direction : Direction.mainDirections()) {
            boolean b = tileMap.getTile(x + direction.getX(), y + direction.getY()) == this;
            connected.put(direction, b);
            if (!b) {
                all = false;
            }
        }
        Direction[][] submap = new Direction[][]{
                {Direction.SOUTH, Direction.WEST},
                {Direction.SOUTH, Direction.EAST},
                {Direction.NORTH, Direction.EAST},
                {Direction.NORTH, Direction.WEST}
        };

        for (int i = 0; i < 4; i++) {
            int idx = submapOffsets[i];

            if (all) {
                quadList.add(this.quadMap[idx]);
            } else {
                Direction[] dir = submap[i];
                if (connected.get(dir[0]) || connected.get(dir[1]))
                    quadList.add(this.quadMap[idx + (connected.get(dir[0]) ? 2 : 0) + (connected.get(dir[1]) ? 8 : 0)]);
                else
                    quadList.add(this.quadMap[ret[i]]);

            }
        }

        return quadList;
    }

}