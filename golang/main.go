package main

import (
	"log"
	"os/exec"
	"sync"
)

type Process struct {
	Cmd    *exec.Cmd
	Status string
}

func NewProcess(command string) *Process {
	cmd := exec.Command(command)
	return &Process{
		Cmd:    cmd,
		Status: "stopped",
	}
}

func (p *Process) Start() error {
	err := p.Cmd.Start()
	if err != nil {
		return err
	}
	p.Status = "running"
	return nil
}

func (p *Process) Stop() error {
	err := p.Cmd.Process.Kill()
	if err != nil {
		return err
	}
	p.Status = "stopped"
	return nil
}

type ProcessManager struct {
	Processes map[string]*Process
	mu        sync.Mutex
}

func NewProcessManager() *ProcessManager {
	return &ProcessManager{
		Processes: make(map[string]*Process),
	}
}

func (pm *ProcessManager) AddProcess(name string, process *Process) {
	pm.mu.Lock()
	defer pm.mu.Unlock()
	pm.Processes[name] = process
}

func (pm *ProcessManager) RemoveProcess(name string) {
	pm.mu.Lock()
	defer pm.mu.Unlock()
	delete(pm.Processes, name)
}

func main() {
	pm := NewProcessManager()

	// Add a process
	process := NewProcess("dir")
	pm.AddProcess("list", process)

	// Start the process
	err := process.Start()
	if err != nil {
		log.Fatalf("Failed to start process: %v", err)
	}

	// Here you can add more logic to interact with your processes
}
