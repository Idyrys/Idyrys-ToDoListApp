

import models.Tasks;
import services.TaskService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        while (true){
            System.out.println("""
                    Выберите номер действие :\s
                     1. Создать
                     2. Редактировать
                     3. Удалить
                     4. Показать задачи""");
            System.out.print("Выберите номер действия (1-4):");
            int answer = scanner.nextInt();
            switch (answer){
                case 1:
                    System.out.println("Введите текстовое описание задачи :");
                    taskService.crateTask(scanner.next());

                    break;
                case 2:
                    System.out.println("Введите идентификатор задачи для редактирования:");
                    int answerUpdate = scanner.nextInt();
                    System.out.println("Введите новое текстовое описание задачи:");
                    taskService.updateTask(answerUpdate, scanner.next());
                    break;
                case 3:
                    System.out.println("Введите идентификатор задачи, который хотите удалить:");
                    taskService.deleteTask(scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Список задач:");
                   ArrayList<Tasks>  tasks = taskService.getAllTasks();
                    for (Tasks task : tasks) {
                        System.out.print(task.toString());
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Ошибка !");

            }
        }
    }
}