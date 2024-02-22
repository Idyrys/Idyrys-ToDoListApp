package services;

import enums.TaskStatus;
import models.Tasks;
import repository.TasksRepo;

import java.sql.*;
import java.util.ArrayList;

public class TaskService {
    TasksRepo tasksRepo = new TasksRepo();
    public void crateTask(String answer){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into tb_tasks (status, description) values ('"+ TaskStatus.IN_PROGRESS.name()+"', '"+answer+"')");
            ArrayList<Tasks> tasks = getAllTasks();
            for (Tasks task : tasks) {
                if (task.getDescription().equals(answer)) {
                    System.out.println("Задача успешно добавлена! Идентификатор задачи: " + task.getId()+"\n");
                    connection.close();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Tasks> getAllTasks(){
        try {
            PreparedStatement preparedStatement = tasksRepo.connectToDataBase().prepareStatement("select id, \n" +
                    "case \n" +
                    "when status ='IN_PROGRESS' then '[Не выполнено]'\n" +
                    "when status = 'DONE' then '[Выполнено]'\n" +
                    "when status = 'DELETED' then '[Удалено]'\n" +
                    "end,description\n" +
                    "from tb_tasks;");
            ArrayList<Tasks> allTasks = new ArrayList<>();
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                Tasks tasks = new Tasks();
                tasks.setId(res.getInt(1));
                tasks.setStatus(res.getString(2));
                tasks.setDescription(res.getString(3));
                allTasks.add(tasks);
            } return allTasks;
        } catch (SQLException e) {
            System.out.println("Ошибка23"+e.getMessage());
            return null;
        }
    }

    public void updateTask(int id, String newDescription){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update tb_tasks set description = '"+newDescription+"' where id = '"+id+"'");
            System.out.println("Задача успешно отредактирована!\n");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTask(int id){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update tb_tasks set status = '"+TaskStatus.DELETED+"' where id = '"+id+"'");
            System.out.println("Задача успешно удалено!\n");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tasks showAllTask(){
        return null;
    }
}
