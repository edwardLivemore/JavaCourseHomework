package com.example.jdbc.service.impl;

import com.example.jdbc.model.Student;
import com.example.jdbc.service.IStudentDAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Data
public class StudentDAOImpl implements IStudentDAO {
    private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;
    private BeanPropertyRowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);

    public void run() {
        // 初始化表
        initTable();

        // 查询数据
        query();

        // 创建学生张三, 李四
        Student zhangsan = new Student(null, "张三", 14);
        Student lisi = new Student(null, "李四", 15);

        // 插入数据
        add(zhangsan);
        add(lisi);

        // 查询数据
        query();

        // 更新张三年龄
        zhangsan.setAge(16);
        update(zhangsan);

        // 查询数据
        query();

        // 删除学生
        delete(zhangsan);

        // 查询数据
        query();

        // 批量插入
        Student wangwu = new Student(null, "王五", 17);
        Student zhaoliu = new Student(null, "赵六", 18);
        batchAdd(Arrays.asList(wangwu, zhaoliu));

        // 查询数据
        query();

        // 测试事务回滚
        try {
            transactionExec();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询数据
        query();
    }

    private void batchAdd(List<Student> students) {
        String sql = "insert into student (name, age) values (?, ?) ";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, students.get(i).getName());
                ps.setInt(2, students.get(i).getAge());
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        });
    }

    private void transactionExec() {
        Student jia = new Student(null, "甲", 14);
        Student yi = new Student(null, "乙", 17);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 添加甲, 正常
                String sql = "insert into student (name, age) values (?, ?) ";
                jdbcTemplate.update(sql, jia.getName(), jia.getAge());
                System.out.println("添加王五成功");

                // 添加乙, 故意打错字段名
                sql = "insert into student(name1, age1) values (?, ?)";
                jdbcTemplate.update(sql, yi.getName(), yi.getAge());
                System.out.println("添加赵六成功");
            }
        });
    }

    private void query() {
        String sql = "select * from student";
        List<Student> list = jdbcTemplate.query(sql, mapper);
        System.out.println("共有 " + list.size() + " 个学生");
        list.forEach(student -> {
            System.out.println("姓名: " + student.getName() + ", 年龄: " + student.getAge());
        });
    }

    private void initTable() {
        String sql = "DROP TABLE IF EXISTS `student`";
        jdbcTemplate.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS `student` ( " +
                "`id` int(0) NOT NULL AUTO_INCREMENT, " +
                "`name` varchar(50), " +
                "`age` int(0), " +
                "PRIMARY KEY (`id`) USING BTREE)";
        jdbcTemplate.execute(sql);
        System.out.println("初始化表完成");
    }

    @Override
    public int add(Student student) {
        String sql = "insert into student(name, age) value (?, ?)";
        return jdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    @Override
    public int update(Student student) {
        String sql = "update student set age = ? where name = ?";
        Object[] args = {student.getAge(), student.getName()};
        return jdbcTemplate.update(sql, student.getAge(), student.getName());
    }

    @Override
    public int delete(Student student) {
        String sql = "delete from student where name = ?";
        return jdbcTemplate.update(sql, student.getName());
    }
}
