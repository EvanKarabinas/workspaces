package com.workspaces.server.ticket;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/workspaces"})
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/{workspaceId}/tickets")
    public List<Ticket> getTickets(@PathVariable int workspaceId) {
        return ticketService.getTickets(workspaceId);
    }

    @PostMapping(value = "/{workspaceId}/tickets")
    public void addTicket(@RequestBody Ticket ticket, @PathVariable int workspaceId) {
        ticketService.addTicket(ticket, workspaceId);
    }

    @PutMapping(value = "/{workspaceId}/tickets/{ticketId}")
    public void updateTicket(@RequestBody Ticket newTicket, @PathVariable int ticketId) {
        ticketService.updateTicket(newTicket, ticketId);
    }

    @DeleteMapping(value = "/{workspaceId}/tickets/{ticketId}")
    public void deleteTicket(@PathVariable int ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
