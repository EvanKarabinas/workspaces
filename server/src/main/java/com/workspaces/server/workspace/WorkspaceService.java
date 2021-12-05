package com.workspaces.server.workspace;

import com.workspaces.server.exception.InvalidInputException;
import com.workspaces.server.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

    private final WorkspaceDao workspaceRepository;

    public WorkspaceService(WorkspaceDao workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public List<Workspace> getWorkspaces() {

        return workspaceRepository.findAll();
    }

    public Optional<Workspace> getWorkspace(int workspaceId){
        return workspaceRepository.findById(workspaceId);
    }

    public void addWorkspace(Workspace workspace) throws InvalidInputException {

        String workspaceName = workspace.getName();

        if (workspaceName == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }
        if (workspaceRepository.findByName(workspaceName).isPresent()) {
            throw new InvalidInputException("Workspace with name (" + workspaceName + ") already exists.");
        }
        workspaceRepository.save(workspace);
    }

    public void updateWorkspace(int id, Workspace newWorkspace) throws InvalidInputException,NotFoundException{
//        if (id == null) {
//            throw new InvalidInputException("Field 'id' is required.");
//        }
        if (newWorkspace.getName() == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }

        workspaceRepository.findById(id)
                .map(workspace -> {
                    workspace.setName(newWorkspace.getName());
                    return workspaceRepository.update(workspace);
                })
                .orElseThrow(() -> new NotFoundException("Can't find Workspace with id:(" + id + ")"));
    }

    public void deleteWorkspace(int id) throws NotFoundException {

        workspaceRepository.findById(id)
                .map(workspace -> {
                    return workspaceRepository.delete(id);
                })
                .orElseThrow(() -> new NotFoundException("Can't find Workspace with id:(" + id + ")"));
    }
}
