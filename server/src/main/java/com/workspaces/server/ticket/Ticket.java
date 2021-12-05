package com.workspaces.server.ticket;

public class Ticket {
    private Long id;
    private String name;
    private String type;

    public Ticket(){}
    public Ticket(String name, String type) {
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
