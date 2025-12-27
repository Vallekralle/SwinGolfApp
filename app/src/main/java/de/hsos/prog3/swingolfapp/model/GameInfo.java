package de.hsos.prog3.swingolfapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public record GameInfo(String gameName, Integer holeCount, ArrayList<String> players) implements Serializable { }
