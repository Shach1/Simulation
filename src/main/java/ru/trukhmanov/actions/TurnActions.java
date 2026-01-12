package ru.trukhmanov.actions;

import ru.trukhmanov.WorldMap;

public class TurnActions implements ActionsCommand{
    private final WorldMap worldMap;

    public TurnActions(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {

    }
}
