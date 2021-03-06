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

        checkWorkspacePayload(workspace);


        workspaceRepository.save(workspace);
    }

    public void updateWorkspace(int id, Workspace newWorkspace) throws InvalidInputException,NotFoundException{

        checkWorkspacePayload(newWorkspace);

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

    private void checkWorkspacePayload(Workspace workspace) {
        String workspaceName = workspace.getName();
        if (workspaceName == null || workspace.getName().trim().length() == 0) {
            throw new InvalidInputException("Field 'name' is required.");
        }
        if (workspaceRepository.findByName(workspaceName).isPresent()) {
            throw new InvalidInputException("Workspace with name (" + workspaceName + ") already exists.");
        }
    }
}
