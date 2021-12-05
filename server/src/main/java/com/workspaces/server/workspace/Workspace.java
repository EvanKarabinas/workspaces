package com.workspaces.server.workspace;

public class Workspace {


    private int id;
    private String name;

    public Workspace(String name) {
        this.name = name;
    }

    public Workspace(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
