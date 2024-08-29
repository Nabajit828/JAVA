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
                case 1:  addTask();       break;
                case 2:  addNote();       break;
                case 3:  addReminder();   break;
                case 4:  taskManager.viewTasks();       break;
                case 5:  taskManager.viewNotes();       break;
                case 6:  taskManager.viewReminders();   break;
                case 7:  deleteTask();    break;
                case 8:  deleteNote();    break;
                case 9:  deleteReminder();break;
                case 10: editTask();      break;
                case 11: editNote();      break;
                case 12: editReminder();  break;
                case 0:  exitProgram();   break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=======================");
        System.out.println("  WELCOME TO YOUR TASK MANAGER");
        System.out.println("=======================");
        System.out.println("  Choose an action below:");
        System.out.println("-----------------------");

        System.out.println("  [1] ➜ Add a New Task");
        System.out.println("  [2] ➜ Add a New Note");
        System.out.println("  [3] ➜ Add a New Reminder");

        System.out.println("\n-- View Existing --");
        System.out.println("  [4] ➜ View All Tasks");
        System.out.println("  [5] ➜ View All Notes");
        System.out.println("  [6] ➜ View All Reminders");

        System.out.println("\n-- Modify Existing --");
        System.out.println("  [7] ➜ Delete a Task");
        System.out.println("  [8] ➜ Delete a Note");
        System.out.println("  [9] ➜ Delete a Reminder");

        System.out.println("\n-- Update Entries --");
        System.out.println("  [10] ➜ Edit a Task");
        System.out.println("  [11] ➜ Edit a Note");
        System.out.println("  [12] ➜ Edit a Reminder");

        System.out.println("\n[0] ➜ Exit the Program");
        System.out.print("\nYour choice: ");
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

    private static void editTask() {
        System.out.print("Enter the task name to edit: ");
        String taskToEdit = scanner.nextLine();
        Task task = taskManager.findTask(taskToEdit);
        if (task != null) {
            System.out.print("New Task Name (leave empty to keep current): ");
            String newTaskName = scanner.nextLine();
            if (!newTaskName.isEmpty()) {
                task.setTaskName(newTaskName);
            }
            System.out.print("New Due Date (leave empty to keep current): ");
            String newDueDate = scanner.nextLine();
            if (!newDueDate.isEmpty()) {
                task.setDueDate(newDueDate);
            }
            System.out.print("New Priority (1-5, leave empty to keep current): ");
            String newPriority = scanner.nextLine();
            if (!newPriority.isEmpty()) {
                try {
                    int priority = Integer.parseInt(newPriority);
                    if (priority >= 1 && priority <= 5) {
                        task.setPriority(priority);
                    } else {
                        System.out.println("Invalid priority. Keeping current priority.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Keeping current priority.");
                }
            }
            System.out.println("Task updated.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void editNote() {
        System.out.print("Enter the note title to edit: ");
        String noteToEdit = scanner.nextLine();
        Note note = taskManager.findNote(noteToEdit);
        if (note != null) {
            System.out.print("New Note Title (leave empty to keep current): ");
            String newNoteTitle = scanner.nextLine();
            if (!newNoteTitle.isEmpty()) {
                note.setTitle(newNoteTitle);
            }
            System.out.print("New Note Content (leave empty to keep current): ");
            String newNoteContent = scanner.nextLine();
            if (!newNoteContent.isEmpty()) {
                note.setContent(newNoteContent);
            }
            System.out.println("Note updated.");
        } else {
            System.out.println("Note not found.");
        }
    }

    private static void editReminder() {
        System.out.print("Enter the reminder text to edit: ");
        String reminderToEdit = scanner.nextLine();
        Reminder reminder = taskManager.findReminder(reminderToEdit);
        if (reminder != null) {
            System.out.print("New Reminder Text (leave empty to keep current): ");
            String newReminderText = scanner.nextLine();
            if (!newReminderText.isEmpty()) {
                reminder.setReminderText(newReminderText);
            }
            System.out.print("New Reminder Date (leave empty to keep current): ");
            String newReminderDate = scanner.nextLine();
            if (!newReminderDate.isEmpty()) {
                reminder.setReminderDate(newReminderDate);
            }
            System.out.print("New Reminder Time (leave empty to keep current): ");
            String newReminderTime = scanner.nextLine();
            if (!newReminderTime.isEmpty()) {
                reminder.setReminderTime(newReminderTime);
            }
            System.out.println("Reminder updated.");
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
}

class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final ArrayList<Note> notes = new ArrayList<>();
    private final ArrayList<Reminder> reminders = new ArrayList<>();

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
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (Task task : tasks) {
                System.out.println("Task: " + task.getTaskName() + " | Due: " + task.getDueDate() + " | Priority: " + task.getPriority());
            }
        }
    }

    public void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes to display.");
        } else {
            for (Note note : notes) {
                System.out.println("Note: " + note.getTitle() + " | Content: " + note.getContent());
            }
        }
    }

    public void viewReminders() {
        if (reminders.isEmpty()) {
            System.out.println("No reminders to display.");
        } else {
            for (Reminder reminder : reminders) {
                System.out.println("Reminder: " + reminder.getReminderText() + " | Date: " + reminder.getReminderDate() + " | Time: " + reminder.getReminderTime());
            }
        }
    }

    public boolean deleteTask(String taskName) {
        return tasks.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskName));
    }

    public boolean deleteNote(String noteTitle) {
        return notes.removeIf(note -> note.getTitle().equalsIgnoreCase(noteTitle));
    }

    public boolean deleteReminder(String reminderText) {
        return reminders.removeIf(reminder -> reminder.getReminderText().equalsIgnoreCase(reminderText));
    }

    public Task findTask(String taskName) {
        return tasks.stream().filter(task -> task.getTaskName().equalsIgnoreCase(taskName)).findFirst().orElse(null);
    }

    public Note findNote(String noteTitle) {
        return notes.stream().filter(note -> note.getTitle().equalsIgnoreCase(noteTitle)).findFirst().orElse(null);
    }

    public Reminder findReminder(String reminderText) {
        return reminders.stream().filter(reminder -> reminder.getReminderText().equalsIgnoreCase(reminderText)).findFirst().orElse(null);
    }
}
