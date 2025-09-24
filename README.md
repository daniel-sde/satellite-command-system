# satellite-command-system
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/daniel-sde/satellite-command-system)

A simple Java application simulating a command system for a satellite. This project demonstrates the use of the Command design pattern to manage satellite operations such as orientation changes, solar panel activation, and data collection.

## Features

*   **State Management**: Tracks the satellite's orientation, solar panel status, and the amount of data collected.
*   **Command Pattern**: Decouples the command invoker (`CommandExecutor`) from the receiver (`Satellite`) using a set of command objects.
*   **Sequential Execution**: Processes a list of commands in order.
*   **Error Handling**: Implements custom exceptions to manage operational failures, such as attempting to collect data with inactive solar panels.
*   **Logging**: Provides clear logging for executed commands, state changes, and errors.

## Project Structure

The core logic is organized into several key components:

*   `Satellite.java`: The primary model class that holds the satellite's state (orientation, panel status, data) and defines the core operations (`rotate`, `activatePanels`, `collectData`).
*   `commands/`: This package contains classes that implement the `Command` interface. Each class encapsulates a specific request to the satellite.
    *   `Command.java`: The common interface for all commands, with a single `execute` method.
    *   `RotateCommand.java`: Rotates the satellite to a specified orientation.
    *   `ActivatePanelsCommand.java`: Activates the satellite's solar panels.
    *   `DeactivatePanelsCommand.java`: Deactivates the solar panels.
    *   `CollectDataCommand.java`: Instructs the satellite to collect data.
*   `executor/CommandExecutor.java`: This class takes a list of `Command` objects and executes them sequentially on the `Satellite` instance. It also handles logging and exception management during execution.
*   `exceptions/`: Contains custom exceptions for specific satellite operation failures.
    *   `SatelliteException.java`: A base exception for any satellite-related error.
    *   `InactivePanelsException.java`: A specific exception thrown when data collection is attempted while solar panels are inactive.
*   `Main.java`: The application's entry point, which demonstrates a typical command sequence.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 21 or later
*   Apache Maven

### Building the Project

1.  Clone the repository:
    ```sh
    git clone https://github.com/daniel-sde/satellite-command-system.git
    cd satellite-command-system
    ```

2.  Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

You can run the simulation directly through Maven:

```sh
mvn exec:java -Dexec.mainClass="com.example.satellite.Main"
```

## Example Usage

The `Main.java` class provides a pre-configured sequence of commands to demonstrate the system's functionality.

```java
// From src/main/java/com/example/satellite/Main.java

Satellite satellite = new Satellite();
CommandExecutor executor = new CommandExecutor(satellite);

List<Command> commands = new ArrayList<>();
commands.add(new RotateCommand(Orientation.SOUTH));
commands.add(new ActivatePanelsCommand());
commands.add(new CollectDataCommand());

commands.add(new RotateCommand(Orientation.EAST));
commands.add(new CollectDataCommand());

commands.add(new RotateCommand(Orientation.NORTH));
commands.add(new ActivatePanelsCommand());
commands.add(new CollectDataCommand());

commands.add(new RotateCommand(Orientation.WEST));
commands.add(new DeactivatePanelsCommand());
commands.add(new CollectDataCommand()); // This will throw an error

executor.executeAll(commands);
```

### Expected Output

When you run the application, you will see logs detailing a sequence of successful operations followed by a failure when `collectData()` is called with inactive panels.

```
INFO: Executing command: rotate(SOUTH)
INFO: Rotated to: SOUTH
INFO: Executing command: activatePanels()
INFO: Solar panels activated
INFO: Executing command: collectData()
INFO: Collected data: +10, total = 10
INFO: Executing command: rotate(EAST)
INFO: Rotated to: EAST
INFO: Executing command: collectData()
INFO: Collected data: +10, total = 20
INFO: Executing command: rotate(NORTH)
INFO: Rotated to: NORTH
INFO: Executing command: activatePanels()
WARNING: Solar panels already activated!!
INFO: Executing command: collectData()
INFO: Collected data: +10, total = 30
INFO: Executing command: rotate(WEST)
INFO: Rotated to: WEST
INFO: Executing command: deactivatePanels()
INFO: Solar panels deactivated
INFO: Executing command: collectData()
SEVERE: Command failed: com.example.satellite.exceptions.InactivePanelsException: Cannot collect data when panels are inactive
INFO: Final Satellite State: Orientation: WEST, Solar Panels: INACTIVE, Data Collected: 30
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
