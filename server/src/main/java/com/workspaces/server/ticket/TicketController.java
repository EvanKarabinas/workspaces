package com.workspaces.server.ticket;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/workspace"})
public class TicketController {

    @GetMapping(value = "/{workspaceId}/tickets")
    public void getTickets(@PathVariable Long workspaceId) {
        System.out.println("This is all the tickets for Workspace: " + workspaceId);
    }

    @PostMapping(value = "/{workspaceId}/tickets")
    public void addTicket(@RequestBody Ticket ticket, @PathVariable Long workspaceId) {
        System.out.println("Adding: " + ticket + " to Workspace: " + workspaceId);
    }
}
