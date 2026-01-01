package de.hsos.prog3.swingolfapp.adapter;

import java.io.Serializable;
import java.util.ArrayList;

public record GameInfoHolder(String gameName, Integer holeCount, ArrayList<String> players) implements Serializable { }
