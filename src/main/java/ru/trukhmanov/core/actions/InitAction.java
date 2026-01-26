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
        int entityCounter = 0;
        while (entityCounter < entityPerWorld){
            placeOneEntity(entityFabrica);
            entityCounter++;
        }
        System.out.printf("count of %s: %d\n", entityFabrica.getEntity().getClass().getSimpleName(), entityCounter);
    }

    private void placeOneEntity(EntityFabrica entityFabrica) {
        boolean isPlacedEntity = false;
        while (!isPlacedEntity){
            if (worldMap.setEntityOnMap(
                    new Coordinates(
                            random.nextInt(worldMap.maxHeight) + 1,
                            random.nextInt(worldMap.maxWidth) + 1
                    ),
                    entityFabrica.getEntity())){
                isPlacedEntity = true;
            }
        }
    }
}
