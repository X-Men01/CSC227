# CPU Scheduling Algorithms

This repository implements various CPU scheduling algorithms as part of a project to simulate process scheduling in an operating system. The project includes common scheduling techniques such as **First-Come-First-Serve (FCFS)**, **Shortest Job First (SJF)**, and **Round-Robin (RR)**, along with a Gantt chart for visualizing the scheduling process.

## Table of Contents
- [Overview](#overview)
- [Algorithms Implemented](#algorithms-implemented)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

CPU scheduling is crucial for managing processes in an operating system. This project simulates scheduling algorithms to allocate CPU time to processes efficiently. Each algorithm is designed with a specific scheduling logic, and a Gantt chart visually represents the scheduling sequence.

## Algorithms Implemented

### 1. First-Come-First-Serve (FCFS)
Processes are scheduled in the order they arrive, without preemption.

### 2. Shortest Job First (SJF)
Schedules processes with the shortest burst time next. Both non-preemptive and preemptive (SRTF) versions can be added if needed.

### 3. Round Robin (RR)
Each process receives a fixed time slot in a cyclic order, suitable for time-sharing systems.

