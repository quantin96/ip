# Dorito User Guide

![Screenshot of Dorito chatbot](https://quantin96.github.io/ip/Ui.png)

Dorito is a desktop app for managing tasks via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Dorito supports 10 different commands:
- `todo`
- `deadline`
- `event`
- `list`
- `find`
- `mark`
- `unmark`
- `delete`
- `priority`
- `bye`

Dorito keeps tasks in the following format:

```
<task number>.[<type>][<mark>][<priority>] <task description> (<task date>)
```

Task types:

- `T` ToDo
- `D` Deadline
- `E` Event

Priority types:

- `H` High
- `M` Medium
- `L` Low

## Adding todos : `todo`

Adds a ToDo task to the task list.

Format `todo <task>`

Example: `todo buy doritos`

```
Got it. I've added this task:  >.<
  [T][ ][ ] buy doritos
Now you have 1 tasks in the list.  >.<
```

## Adding deadlines : `deadline`

Adds a Deadline task to the task list.

Format `deadline <task> /by <YYYY-MM-DD>`

Example: `deadline eat doritos /by 2025-02-21`

```
Got it. I've added this task:  >.<
  [D][ ][ ] eat doritos (by: Feb 21 2025)
Now you have 2 tasks in the list.  >.<
```

## Adding events : `event`

Adds a Event task to the task list.

Format `event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Example: `event dorito party /from 2025-02-22 /to 2025-02-23`

```
Got it. I've added this task:  >.<
  [E][ ][ ] dorito party (from: Feb 22 2025 to: Feb 23 2025)
Now you have 3 tasks in the list.  >.<
```

## Listing tasks : `list`

Lists all tasks in the task list.

Example: `list`

```
Here are the tasks in your list:  0.0
1.[T][ ][ ] buy doritos
2.[D][ ][ ] eat doritos (by: Feb 21 2025)
3.[E][ ][ ] dorito party (from: Feb 22 2025 to: Feb 23 2025)
```

## Finding tasks : `find`

Finds all tasks that contains the key in the task description.

Format: `find <key>`

Example: `find eat`

```
Here are the relevant tasks:  0.0
2.[D][ ][ ] eat doritos (by: Feb 21 2025)
```

## Finding tasks : `mark`

Marks the specified task as done.

Format: `mark <task number>`

Example: `mark 1`

```
Nice! I've marked this task as done:  >.<
  [T][X][ ] buy doritos
```

## Finding tasks : `unmark`

Unmarks the specified task.

Format: `unmark <task number>`

Example: `unmark 1`

```
OK! I've marked this task as not done:  0.0
  [T][ ][ ] buy doritos
```

## Deleting tasks : `delete`

Deletes the specified task.

Format: `delete <task number>`

Example: `delete 1`

```
OK! I've removed this task:  0.0
  [T][ ][ ] buy doritos
Now you have 2 tasks in the list.  >.<
```

## Setting task priority : `priority`

Sets the specified task's priority.

Format: `priority <task number> <priority>`

Priority values : `high`, `medium`, `low`, `default`

Example: `priority 2 high`

```
OK! I've marked this task as HIGH priority 0.0
```

## Exiting Dorito : `bye`

Exits the Dorito chatbot.

Example: `bye`

```
Bye. Remember to stay hydrated!  >.<
```
