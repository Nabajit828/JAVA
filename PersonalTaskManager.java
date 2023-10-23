import java.util.ArrayList;
import java.util.Scanner;

public class PersonalTaskManager {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Personal Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Add Note");
            System.out.println("3. Add Reminder");
            System.out.println("4. View Tasks");
            System.out.println("5. View Notes");
            System.out.println("6. View Reminders");
            System.out.println("7. Delete Task");
            System.out.println("8. Delete Note");
            System.out.println("9. Delete Reminder");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Task Name: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Due Date: ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Priority (1-5): ");
                    int priority = scanner.nextInt();
                    taskManager.addTask(new Task(taskName, dueDate, priority));
                    break;
                case 2:
                    System.out.print("Note Title: ");
                    String noteTitle = scanner.nextLine();
                    System.out.print("Note Content: ");
                    String noteContent = scanner.nextLine();
                    taskManager.addNote(new Note(noteTitle, noteContent));
                    break;
                case 3:
                    System.out.print("Reminder Text: ");
                    String reminderText = scanner.nextLine();
                    System.out.print("Reminder Date: ");
                    String reminderDate = scanner.nextLine();
                    System.out.print("Reminder Time: ");
                    String reminderTime = scanner.nextLine();
                    taskManager.addReminder(new Reminder(reminderText, reminderDate, reminderTime));
                    break;
                case 4:
                    taskManager.viewTasks();
                    break;
                case 5:
                    taskManager.viewNotes();
                    break;
                case 6:
                    taskManager.viewReminders();
                    break;
                case 7:
                    System.out.print("Enter the task name to delete: ");
                    String taskToDelete = scanner.nextLine();
                    taskManager.deleteTask(taskToDelete);
                    break;
                case 8:
                    System.out.print("Enter the note title to delete: ");
                    String noteToDelete = scanner.nextLine();
                    taskManager.deleteNote(noteToDelete);
                    break;
                case 9:
                    System.out.print("Enter the reminder text to delete: ");
                    String reminderToDelete = scanner.nextLine();
                    taskManager.deleteReminder(reminderToDelete);
                    break;
                case 0:
                    System.out.println("Exiting Personal Task Manager. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Task {
    public String taskName;
    public String dueDate;
    public int priority;
    public boolean status;

    public Task(String taskName, String dueDate, int priority) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = false;
    }
}

class Note {
    public String title;
    public String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

class Reminder {
    public String reminderText;
    public String reminderDate;
    public String reminderTime;

    public Reminder(String reminderText, String reminderDate, String reminderTime) {
        this.reminderText = reminderText;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
    }
}

class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();
    private ArrayList<Reminder> reminders = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added.");
    }

    public void addNote(Note note) {
        notes.add(note);
        System.out.println("Note added.");
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
        System.out.println("Reminder added.");
    }

    public void viewTasks() {
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("Task: " + task.taskName + " (Due Date: " + task.dueDate + ")");
        }
    }

    public void viewNotes() {
        System.out.println("Notes:");
        for (Note note : notes) {
            System.out.println("Note: " + note.title + " (Content: " + note.content + ")");
        }
    }

    public void viewReminders() {
        System.out.println("Reminders:");
        for (Reminder reminder : reminders) {
            System.out.println("Reminder: " + reminder.reminderText + " (Date: " + reminder.reminderDate + ", Time: " + reminder.reminderTime + ")");
        }
    }

    public void deleteTask(String taskName) {
        tasks.removeIf(task -> task.taskName.equals(taskName));
        System.out.println("Task deleted.");
    }

    public void deleteNote(String noteTitle) {
        notes.removeIf(note -> note.title.equals(noteTitle));
        System.out.println("Note deleted.");
    }

    public void deleteReminder(String reminderText) {
        reminders.removeIf(reminder -> reminder.reminderText.equals(reminderText));
        System.out.println("Reminder deleted.");
    }
}
