package ru.trukhmanov.actions;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.fabrica.EntityFabrica;
import ru.trukhmanov.fabrica.GrassFabrica;
import ru.trukhmanov.fabrica.HerbivoreFabrica;
import ru.trukhmanov.fabrica.PredatorFabrica;
import ru.trukhmanov.searchAlgorithms.BreadthFirstSearchAlgorithm;

// TODO: УДАЛИТЬ ИЗ ФИНАЛЬНОГО КОММИТА
public class TestInitActions implements ActionsCommand {
    private WorldMap worldMap;
    public TestInitActions(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {
        var pathfindingAlgorithm = new BreadthFirstSearchAlgorithm();

        EntityFabrica herbivoreFabrica = new HerbivoreFabrica();
        EntityFabrica predatorFabrica = new PredatorFabrica();
        EntityFabrica grassFabrica = new GrassFabrica();

        var herbivoreCoordinates1 = new Coordinates(1, 1);
        var predatorCoordinates1 = new Coordinates(5, 5);
        var grassCoordinates1 = new Coordinates(2, 4);


        worldMap.setEntityOnMap(herbivoreCoordinates1, herbivoreFabrica.getEntity());
        worldMap.setEntityOnMap(predatorCoordinates1, predatorFabrica.getEntity());
        worldMap.setEntityOnMap(grassCoordinates1, grassFabrica.getEntity());

        var resultOfSearchGrass = pathfindingAlgorithm.searchGrassCoordinates(worldMap, herbivoreCoordinates1);
        var resultOfSearchHerbivore = pathfindingAlgorithm.searchHerbivoreCoordinates(worldMap, predatorCoordinates1);
        System.out.println("resultOfSearchHerbivore = " + resultOfSearchHerbivore);
        System.out.println("resultOfSearchGrass = " + resultOfSearchGrass);
        int sf = 123;
    }
}
