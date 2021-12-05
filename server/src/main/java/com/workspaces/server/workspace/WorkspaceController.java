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
        System.out.println(workspace);
        //workspaceService.addWorkspace(workspace);
    }

    @PutMapping("/{id}")
    public void updateWorkspace(@RequestBody Workspace newWorkspace, @PathVariable Long id) {
        workspaceService.updateWorkspace(id, newWorkspace);
    }
}
