package ru.trukhmanov.core.actions;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.entities.fabrica.*;

import java.util.Random;

public class InitAction extends ActionCommand {
    private final Random random = new Random();

    public InitAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    public void execute() {
        int worldSize =  worldMap.size();
        int rocksPerWorld = (int) (worldSize * 0.08);
        int treesPerWorld = (int) (worldSize * 0.05);
        int grassPerWorld = (int) (worldSize * 0.25);
        int herbivorePerWorld = (int) (worldSize * 0.058);
        int predatorPerWorld = (int) (worldSize * 0.025);

        placeEntities(rocksPerWorld, new RockFabrica());
        placeEntities(treesPerWorld, new TreeFabrica());
        placeEntities(grassPerWorld, new GrassFabrica());
        placeEntities(herbivorePerWorld, new HerbivoreFabrica());
        placeEntities(predatorPerWorld, new PredatorFabrica());
    }

    private void placeEntities(int entityPerWorld, EntityFabrica entityFabrica){
        int tempConuter = 0;
        boolean isPlacedEntity = false;
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
