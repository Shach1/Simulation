package ru.trukhmanov.core.actions.turn;

import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.core.actions.ActionCommand;
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
