package com.workspaces.server.ticket;

public class Ticket {
    private int id;
    private String name;
    private String type;

    public Ticket(int id, String name, String type) {
        this.id= id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
