package de.hsos.prog3.swingolfapp.model.gson;

import java.util.ArrayList;

public record CourseGson(String courseName, int holeCount, float averageShots, ArrayList<PlayerGson> players) { }
