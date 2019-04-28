package org.epoxide.ld44.world;

import org.epoxide.ld44.bounty.Bounties;
import org.epoxide.ld44.bounty.IBounty;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;

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

        for (int x = 0; x < this.tileMap.getWidth(); x++)
            for (int y = 0; y < this.tileMap.getHeight(); y++)
                this.tileMap.setTile(x, y, Tiles.GRASS);
    }
}
