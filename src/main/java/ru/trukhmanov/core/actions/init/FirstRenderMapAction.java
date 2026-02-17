package ru.trukhmanov.core.actions.init;

import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.core.actions.ActionCommand;
import ru.trukhmanov.util.Renderer;

public class FirstRenderMapAction extends ActionCommand{
    public FirstRenderMapAction(WorldMap worldMap){
        super(worldMap);
    }

    @Override
    public void execute(){
        System.out.println("Simulation started!");
        new Renderer(worldMap).renderMap();
        try{
            Thread.sleep(1500);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
