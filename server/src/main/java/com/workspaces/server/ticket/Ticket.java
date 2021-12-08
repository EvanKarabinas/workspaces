package com.workspaces.server.ticket;

import java.util.Set;

public class Ticket {
    private int id;
    private int workspaceId;
    private String name;
    private String type;
    private String status;
    private static final Set<String> STATUSES = Set.of(
            "to-do","in-progress","completed"
    );


    public Ticket(int id, int workspaceId, String name, String type,String status) {
        this.id= id;
        this.workspaceId=workspaceId;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public static Set<String> getValidStatuses() {
        return STATUSES;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
