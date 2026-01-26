package ru.trukhmanov.core.actions;

import ru.trukhmanov.core.WorldMap;

public abstract class ActionCommand {
    protected final WorldMap worldMap;

    public ActionCommand(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract void execute();
}
