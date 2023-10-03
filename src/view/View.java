package view;

import models.Maze;

import java.awt.*;

public interface View {
    public void generateDisplay(Maze maze, String message);
}
