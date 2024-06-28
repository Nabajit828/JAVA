import java.util.ArrayList;
import java.util.Scanner;

public class PersonalTaskManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    addNote();
                    break;
                case 3:
                    addReminder();
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
                    deleteTask();
                    break;
                case 8:
                    deleteNote();
                    break;
                case 9:
                    deleteReminder();
                    break;
                case 0:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
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
    }

    private static int getUserChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return choice;
    }

    private static void addTask() {
        System.out.print("Task Name: ");
        String taskName = scanner.nextLine();
        System.out.print("Due Date: ");
        String dueDate = scanner.nextLine();
        int priority;
        while (true) {
            try {
                System.out.print("Priority (1-5): ");
                priority = Integer.parseInt(scanner.nextLine());
                if (priority < 1 || priority > 5) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter a number between 1 and 5.");
            }
        }
        taskManager.addTask(new Task(taskName, dueDate, priority));
    }

    private static void addNote() {
        System.out.print("Note Title: ");
        String noteTitle = scanner.nextLine();
        System.out.print("Note Content: ");
        String noteContent = scanner.nextLine();
        taskManager.addNote(new Note(noteTitle, noteContent));
    }

    private static void addReminder() {
        System.out.print("Reminder Text: ");
        String reminderText = scanner.nextLine();
        System.out.print("Reminder Date: ");
        String reminderDate = scanner.nextLine();
        System.out.print("Reminder Time: ");
        String reminderTime = scanner.nextLine();
        taskManager.addReminder(new Reminder(reminderText, reminderDate, reminderTime));
    }

    private static void deleteTask() {
        System.out.print("Enter the task name to delete: ");
        String taskToDelete = scanner.nextLine();
        if (taskManager.deleteTask(taskToDelete)) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteNote() {
        System.out.print("Enter the note title to delete: ");
        String noteToDelete = scanner.nextLine();
        if (taskManager.deleteNote(noteToDelete)) {
            System.out.println("Note deleted.");
        } else {
            System.out.println("Note not found.");
        }
    }

    private static void deleteReminder() {
        System.out.print("Enter the reminder text to delete: ");
        String reminderToDelete = scanner.nextLine();
        if (taskManager.deleteReminder(reminderToDelete)) {
            System.out.println("Reminder deleted.");
        } else {
            System.out.println("Reminder not found.");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting Personal Task Manager. Goodbye!");
        System.exit(0);
    }
}

class Task {
    private String taskName;
    private String dueDate;
    private int priority;

    public Task(String taskName, String dueDate, int priority) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }
}

class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

class Reminder {
    private String reminderText;
    private String reminderDate;
    private String reminderTime;

    public Reminder(String reminderText, String reminderDate, String reminderTime) {
        this.reminderText = reminderText;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
    }

    public String getReminderText() {
        return reminderText;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public String getReminderTime() {
        return reminderTime;
    }
}

class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();
    private ArrayList<Reminder> reminders = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public void viewTasks() {
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("Task: " + task.getTaskName() + " (Due Date: " + task.getDueDate() + ")");
        }
    }

    public void viewNotes() {
        System.out.println("Notes:");
        for (Note note : notes) {
            System.out.println("Note: " + note.getTitle() + " (Content: " + note.getContent() + ")");
        }
    }

    public void viewReminders() {
        System.out.println("Reminders:");
        for (Reminder reminder : reminders) {
            System.out.println("Reminder: " + reminder.getReminderText() + " (Date: " + reminder.getReminderDate() + ", Time: " + reminder.getReminderTime() + ")");
        }
    }

    public boolean deleteTask(String taskName) {
        return tasks.removeIf(task -> task.getTaskName().equals(taskName));
    }

    public boolean deleteNote(String noteTitle) {
        return notes.removeIf(note -> note.getTitle().equals(noteTitle));
    }

    public boolean deleteReminder(String reminderText) {
        return reminders.removeIf(reminder -> reminder.getReminderText().equals(reminderText));
    }
}
