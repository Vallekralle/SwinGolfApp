package de.hsos.prog3.swingolfapp.model;

public class PlayerInfoHolder {
    private String name;
    private boolean isChecked;

    public PlayerInfoHolder(String name) {
        this.name = name;
        isChecked = false;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked() {
        isChecked = !isChecked;
    }
}
