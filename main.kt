data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)

val tasks = mutableListOf<Task>()
var nextId = 1

fun addTask(title: String, description: String) {
    val task = Task(nextId, title, description)
    tasks.add(task)
    println("Task added with ID ${task.id}")
    nextId++
}

fun listTasks() {
    if (tasks.isEmpty()) {
        println("No tasks found.")
        return
    }
    for (task in tasks) {
        val status = if (task.isCompleted) "Done" else "Pending"
        println("[${task.id}] ${task.title} - $status")
        println("   ${task.description}")
    }
}

fun completeTask(id: Int) {
    val task = tasks.find { it.id == id }
    if (task != null) {
        task.isCompleted = true
        println("Task $id marked as completed.")
    } else {
        println("Task not found.")
    }
}

fun removeTask(id: Int) {
    val removed = tasks.removeIf { it.id == id }
    if (removed) {
        println("Task $id removed.")
    } else {
        println("Task not found.")
    }
}

fun main() {
    while (true) {
        println("\n--- Task Manager ---")
        println("1. Add task")
        println("2. List tasks")
        println("3. Complete task")
        println("4. Remove task")
        println("5. Exit")
        print("Choose an option: ")

        when (readLine()) {
            "1" -> {
                print("Title: ")
                val title = readLine() ?: ""
                print("Description: ")
                val desc = readLine() ?: ""
                addTask(title, desc)
            }
            "2" -> listTasks()
            "3" -> {
                print("Task ID: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) completeTask(id) else println("Invalid ID.")
            }
            "4" -> {
                print("Task ID: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) removeTask(id) else println("Invalid ID.")
            }
            "5" -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid option.")
        }
    }
}
