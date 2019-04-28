package org.epoxide.ld44.tile;

import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.epoxide.ld44.client.tile.Quad;
import org.epoxide.ld44.client.tile.ScaledQuad;

import java.util.LinkedList;
import java.util.List;

public class TileCT extends Tile {

    private Quad[] quadMap = new Quad[20];

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

        List<Quad> quadList = new LinkedList<Quad>();


        boolean[] connected = new boolean[4];
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    connected[0] = tileMap.getTile(x, y + 1) == this;
                    break;
                case 1:
                    connected[1] = tileMap.getTile(x, y - 1) == this;
                    break;
                case 2:
                    connected[2] = tileMap.getTile(x - 1, y) == this;
                    break;
                case 3:
                    connected[3] = tileMap.getTile(x + 1, y) == this;
                    break;
            }
        }

        boolean all = true;
        for (boolean b : connected) {
            if (!b) {
                all = false;
                break;
            }
        }

        int[] ret = new int[]{18, 19, 17, 16};

        int[] submapOffsets = {4, 5, 1, 0};

        for (int i = 0; i < 4; i++) {
            if (all) {
                quadList.add(this.quadMap[submapOffsets[i]]);
            } else {
                switch (i) {
                    case 0:
                        if (connected[0] || connected[2])
                            quadList.add(this.quadMap[submapOffsets[i] + (connected[0] ? 2 : 0) + (connected[2] ? 8 : 0)]);
                        else
                            quadList.add(this.quadMap[ret[i]]);
                        break;
                    case 1:
                        if (connected[0] || connected[3])
                            quadList.add(this.quadMap[submapOffsets[i] + (connected[0] ? 2 : 0) + (connected[3] ? 8 : 0)]);
                        else
                            quadList.add(this.quadMap[ret[i]]);
                        break;
                    case 2:
                        if (connected[1] || connected[3])
                            quadList.add(this.quadMap[submapOffsets[i] + (connected[1] ? 2 : 0) + (connected[3] ? 8 : 0)]);
                        else
                            quadList.add(this.quadMap[ret[i]]);
                        break;
                    case 3:
                        if (connected[1] || connected[2])
                            quadList.add(this.quadMap[submapOffsets[i] + (connected[1] ? 2 : 0) + (connected[2] ? 8 : 0)]);
                        else
                            quadList.add(this.quadMap[ret[i]]);
                        break;
                }
            }
        }

        return quadList;
    }

}