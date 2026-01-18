package ru.trukhmanov.actions;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.fabrica.*;

import java.util.Random;

public class InitActions implements ActionsCommand{
    Random random = new Random();
    private final WorldMap worldMap;
    boolean isPlacedEntity = false;


    public InitActions(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {
        int worldSize =  worldMap.size();
        int rocksPerWorld = (int) (worldSize * 0.08);
        int treesPerWorld = (int) (worldSize * 0.05);
        int grassPerWorld = (int) (worldSize * 0.25);
        int herbivorePerWorld = (int) (worldSize * 0.058);
        int predatorPerWorld = (int) (worldSize * 0.025);

        placeEntity(rocksPerWorld, new RockFabrica());
        placeEntity(treesPerWorld, new TreeFabrica());
        placeEntity(grassPerWorld, new GrassFabrica());
        placeEntity(herbivorePerWorld, new HerbivoreFabrica());
        placeEntity(predatorPerWorld, new PredatorFabrica());

    }

    private void placeEntity(int entityPerWorld, EntityFabrica entityFabrica){
        int tempConuter = 0;
        for (int i = 0; i < entityPerWorld; i++) {
            while (!isPlacedEntity){
                if (worldMap.setEntityOnMap(
                        new Coordinates(
                                random.nextInt(worldMap.maximumHeight) + 1,
                                random.nextInt(worldMap.maximumWeight) + 1
                        ),
                        entityFabrica.getEntity())){
                    tempConuter++;
                    isPlacedEntity = true;
                }
            }
            isPlacedEntity = false;
        }
        System.out.printf("count of %s: %d\n", entityFabrica.getEntity().getClass().getSimpleName(), tempConuter);
    }
}
