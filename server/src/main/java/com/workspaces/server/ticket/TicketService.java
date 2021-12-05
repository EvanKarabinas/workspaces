package com.workspaces.server.ticket;

import com.workspaces.server.exception.InvalidInputException;
import com.workspaces.server.exception.NotFoundException;
import com.workspaces.server.workspace.WorkspaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private TicketDao ticketRepository;
    private WorkspaceService workspaceService;

    public TicketService(TicketDao ticketRepository,WorkspaceService workspaceService){
        this.ticketRepository = ticketRepository;
        this.workspaceService = workspaceService;
    }

    public List<Ticket> getTickets(int workspaceId) {

        List<Ticket> tickets = workspaceService.getWorkspace(workspaceId)
                .map(workspace -> {return ticketRepository.findAll(workspaceId);})
                .orElseThrow(()->new NotFoundException("Workspace with id:"+ workspaceId +" doesn't exist."));
        return tickets;
    }

    public void addTicket(Ticket ticket, int workspaceId) {
        if (ticket.getName() == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }
        workspaceService.getWorkspace(workspaceId)
                .map(workspace -> {return ticketRepository.save(ticket,workspaceId);})
                .orElseThrow(()->new NotFoundException("Workspace with id:"+ workspaceId +" doesn't exist."));
    }

    public void updateTicket(Ticket newTicket,int id){
        if (newTicket.getName() == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }
        ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setName(newTicket.getName());
                    ticket.setType(newTicket.getType());
                    return ticketRepository.update(ticket);})
                .orElseThrow(()->new NotFoundException("Ticket with id:"+ id +" doesn't exist."));

    }

    public void deleteTicket(int id){
        
    }
}
