package teacher.repo;

import common.ComonRepo;
import common.MyDataBase;
import teacher.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepo implements ComonRepo<Teacher> {
    @Override
    public List<Teacher> getList() {
        List<Teacher> result = new ArrayList<>();
        try (Connection connection = MyDataBase.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from teacher");
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));
                teacher.setSalary(resultSet.getDouble("salary"));

                result.add(teacher);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Teacher obj) {
        try (Connection connection = MyDataBase.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update teacher set name =?, surname=?, email=?,age=?,salary=? where id=?");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.setString(3, obj.getEmail());
            preparedStatement.setInt(4, obj.getAge());
            preparedStatement.setDouble(5, obj.getSalary());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(Teacher obj) {
        try (Connection connection = MyDataBase.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from teacher where id =?");
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insert(Teacher obj) {
        try (Connection connection = MyDataBase.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into teacher (name, surname, email ,age ,salary) values (?,?,?,?,?) ");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.setString(3, obj.getEmail());
            preparedStatement.setInt(4, obj.getAge());
            preparedStatement.setDouble(5, obj.getSalary());

            preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Teacher findById(int id) {
        try (Connection connection = MyDataBase.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));
                teacher.setSalary(resultSet.getDouble("salary"));

                return teacher;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> getList(String name, String surname) {
        List<Teacher> result = new ArrayList<>();
        try (Connection connection = MyDataBase.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from teacher where name =? or surname=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));
                teacher.setSalary(resultSet.getDouble("salary"));

                result.add(teacher);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
