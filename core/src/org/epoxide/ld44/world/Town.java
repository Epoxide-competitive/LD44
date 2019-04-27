package org.epoxide.ld44.world;

import org.epoxide.ld44.bounty.Bounties;
import org.epoxide.ld44.bounty.IBounty;
import org.epoxide.ld44.tile.TileMap;
import org.epoxide.ld44.tile.Tiles;

public class Town extends World {

    private IBounty[] bounties;

    public Town() {
        super(1, 5, 5);
        this.generateBounties();
        this.generate();
    }

    public void generateBounties() {
        this.bounties = Bounties.generateBounties(5);
    }

    @Override
    public void generate() {
        //TODO layout premade area

        TileMap tileMap = this.getTileMaps()[0];
        for (int x = 0; x < tileMap.getWidth(); x++)
            for (int y = 0; y < tileMap.getHeight(); y++)
                tileMap.setTile(x, y, Tiles.STONE);
    }
}
