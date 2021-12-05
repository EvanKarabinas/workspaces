package com.workspaces.server.workspace;

import com.workspaces.server.exception.InvalidInputException;
import com.workspaces.server.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public List<Workspace> getWorkspaces() {

        return workspaceRepository.findAll();
    }

    public void addWorkspace(Workspace workspace) throws InvalidInputException {

        String workspaceName = workspace.getName();

        if (workspaceName == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }
        if (workspaceRepository.findByName(workspaceName).isPresent()) {
            throw new InvalidInputException("Workspace with name (" + workspaceName + ") already exists.");
        }

        System.out.println(workspace);
        workspaceRepository.save(workspace);
    }

    public void updateWorkspace(Long id, Workspace newWorkspace) throws InvalidInputException,NotFoundException{
//        if (id == null) {
//            throw new InvalidInputException("Field 'id' is required.");
//        }
        if (newWorkspace.getName() == null) {
            throw new InvalidInputException("Field 'name' is required.");
        }

        workspaceRepository.findById(id)
                .map(workspace -> {
                    workspace.setName(newWorkspace.getName());
                    return workspaceRepository.save(workspace);
                })
                .orElseThrow(() -> new NotFoundException("Can't find Workspace with id:(" + id + ")"));
    }
}
