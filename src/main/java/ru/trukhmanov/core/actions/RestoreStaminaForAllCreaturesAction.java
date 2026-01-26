package ru.trukhmanov.core.actions;

import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.entities.Creature;

public class RestoreStaminaForAllCreaturesAction extends ActionCommand{

    public RestoreStaminaForAllCreaturesAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    public void execute() {
        for(var coordinates : worldMap.getCoordinatesOfCreatures()) {
            ((Creature) worldMap.getEntityByCoordinates(coordinates)).restoreStamina();
        }
    }
}
