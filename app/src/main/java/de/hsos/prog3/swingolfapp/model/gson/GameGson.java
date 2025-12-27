package de.hsos.prog3.swingolfapp.model.gson;

import java.util.List;

public record GameGson(String courseName, int holeCount, float averageShots, List<PlayerGson> players) { }
