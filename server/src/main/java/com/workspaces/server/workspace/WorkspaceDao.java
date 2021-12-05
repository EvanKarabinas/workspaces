package com.workspaces.server.workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceDao {
    List<Workspace> findAll();
    Optional<Workspace> findById(int id);
    Optional<Workspace> findByName(String name);
    int save(Workspace workspace);
    int update(Workspace workspace);
    int delete(int id);

}
