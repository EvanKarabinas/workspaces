package com.workspaces.server.ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDao {
    List<Ticket> findAll(int workspaceId);
    Optional<Ticket> findById(int id);
    int save(Ticket ticket,int workspaceId);
    int update(Ticket ticket);
    int delete(int id);
}
