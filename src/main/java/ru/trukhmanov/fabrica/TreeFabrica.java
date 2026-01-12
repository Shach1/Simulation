package ru.trukhmanov.fabrica;

import ru.trukhmanov.entity.Entity;
import ru.trukhmanov.entity.Tree;

public class TreeFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Tree();
    }
}
