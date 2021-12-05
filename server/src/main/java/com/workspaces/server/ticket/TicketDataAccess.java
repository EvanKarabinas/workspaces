package com.workspaces.server.ticket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketDataAccess implements TicketDao{

    private final JdbcTemplate jdbcTemplate;

    public TicketDataAccess(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ticket> findAll(int workspaceId) {
        String sql = "SELECT id,workspace_id,name,type FROM tickets WHERE workspace_id=?;";
        List<Ticket> tickets = jdbcTemplate.query(sql,(resultSet,i)->{
            return new Ticket(
                    resultSet.getInt("id"),
                    resultSet.getInt("workspace_id"),
                    resultSet.getString("name"),
                    resultSet.getString("type"));
        },workspaceId);
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        String sql = "SELECT id,workspace_id,name,type FROM tickets WHERE id = ? ;" ;
        List<Ticket> tickets = jdbcTemplate.query(sql,(resultSet, i)->{
            return new Ticket(
                    resultSet.getInt("id"),
                    resultSet.getInt("workspace_id"),
                    resultSet.getString("name"),
                    resultSet.getString("type"));
        },id);
        return tickets.stream().findFirst();
    }

    @Override
    public int save(Ticket ticket, int workspaceId) {
        String sql = "INSERT INTO tickets(workspace_id,name,type) VALUES (?,?,?);";
        return jdbcTemplate.update(sql,workspaceId,ticket.getName(),ticket.getType());
    }

    @Override
    public int update(Ticket ticket) {
        String sql = "UPDATE tickets SET name=?,type=? WHERE id=?;";
        return jdbcTemplate.update(sql,ticket.getName(),ticket.getType(),ticket.getId());
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM tickets WHERE id=?;";
        return jdbcTemplate.update(sql,id);
    }
}
