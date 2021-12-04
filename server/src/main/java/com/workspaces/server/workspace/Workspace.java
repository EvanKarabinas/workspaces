package com.workspaces.server.workspace;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table
public class Workspace {

    @Id
    @SequenceGenerator(
            name = "workspace_sequence",
            sequenceName = "workspace_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workspace_sequence"
    )
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    public Workspace() {
    }

    public Workspace(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
