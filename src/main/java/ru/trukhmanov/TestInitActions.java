package ru.trukhmanov;

import ru.trukhmanov.actions.ActionsCommand;
import ru.trukhmanov.fabrica.EntityFabrica;
import ru.trukhmanov.fabrica.GrassFabrica;
import ru.trukhmanov.fabrica.HerbivoreFabrica;
import ru.trukhmanov.fabrica.PredatorFabrica;
import ru.trukhmanov.searchAlgorithms.BreadthFirstSearch;

// TODO: УДАЛИТЬ ИЗ ФИНАЛЬНОГО КОММИТА
public class TestInitActions implements ActionsCommand {
    private WorldMap worldMap;
    public TestInitActions(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {
        Renderer renderer = new Renderer(worldMap);
        var pathfindingAlgorithm = new BreadthFirstSearch();


        EntityFabrica herbivoreFabrica = new HerbivoreFabrica();
        EntityFabrica predatorFabrica = new PredatorFabrica();
        EntityFabrica grassFabrica = new GrassFabrica();


        var herbivoreCoordinates1 = new Coordinates(1, 1);
        var predatorCoordinates1 = new Coordinates(5, 5);

        var blockCoordinates1 = new Coordinates(1, 2);
        var blockCoordinates2 = new Coordinates(2, 2);
        var blockCoordinates3 = new Coordinates(2, 1);
        var blockCoordinates4 = new Coordinates(2, 4);


        worldMap.setEntityOnMap(herbivoreCoordinates1, herbivoreFabrica.getEntity());
        worldMap.setEntityOnMap(predatorCoordinates1, predatorFabrica.getEntity());

        worldMap.setEntityOnMap(blockCoordinates1, grassFabrica.getEntity());
        worldMap.setEntityOnMap(blockCoordinates2, grassFabrica.getEntity());
        worldMap.setEntityOnMap(blockCoordinates3, grassFabrica.getEntity());
        worldMap.setEntityOnMap(blockCoordinates4, grassFabrica.getEntity());



        var temp = pathfindingAlgorithm.searchHerbivoreCoordinates(worldMap, predatorCoordinates1);
        System.out.println(temp);
        int sf = 123;
    }
}
