package ru.trukhmanov.actions;

import ru.trukhmanov.WorldMap;

public abstract class ActionCommand {
    protected final WorldMap worldMap;

    public ActionCommand(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract void execute();
}
