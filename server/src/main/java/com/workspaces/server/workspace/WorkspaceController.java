package com.workspaces.server.workspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public List<Workspace> getWorkspaces() {
       return workspaceService.getWorkspaces();
    }

    @PostMapping
    public void addWorkspace(@RequestBody Workspace workspace) {
        workspaceService.addWorkspace(workspace);
    }

    @PutMapping("/{id}")
    public void updateWorkspace(@RequestBody Workspace newWorkspace, @PathVariable int id) {
        workspaceService.updateWorkspace(id, newWorkspace);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable int id) {
        workspaceService.deleteWorkspace(id);
    }
}
