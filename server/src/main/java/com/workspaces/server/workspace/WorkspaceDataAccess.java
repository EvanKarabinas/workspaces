package com.workspaces.server.workspace;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkspaceDataAccess implements WorkspaceDao{

    private final JdbcTemplate jdbcTemplate;

    public WorkspaceDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Workspace> findAll() {
        String sql = "SELECT id,name FROM workspaces;";
        List<Workspace> workspacesList = jdbcTemplate.query(sql,(resultSet,i)->{
            return new Workspace(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
        });
        return workspacesList;
    }

    @Override
    public Optional<Workspace> findById(int id) {
        String sql = "SELECT id,name FROM workspaces WHERE id = ? ;" ;
        List<Workspace> workspaces = jdbcTemplate.query(sql,(resultSet,i)->{
            return new Workspace(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
        },id);
        return workspaces.stream().findFirst();
    }

    @Override
    public Optional<Workspace> findByName(String name) {
        String sql = "SELECT id,name FROM workspaces WHERE name = ? ;" ;
        List<Workspace> workspaces = jdbcTemplate.query(sql,(resultSet,i)->{
            return new Workspace(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
        },name);
        return workspaces.stream().findFirst();
    }

    @Override
    public int save(Workspace workspace) {
        String sql = "INSERT INTO workspaces(name) VALUES (?);";
        return jdbcTemplate.update(sql,workspace.getName());
    }

    @Override
    public int update(Workspace workspace) {
        String sql = "UPDATE workspaces SET name=? WHERE id=?;";
        return jdbcTemplate.update(sql,workspace.getName(),workspace.getId());
    }

    @Override
    public int delete(int id){
        String sql = "DELETE FROM workspaces WHERE id=?;";
        return jdbcTemplate.update(sql,id);
    }
}
