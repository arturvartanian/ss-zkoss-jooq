package authorizationzkjooq.model.dao;

import authorizationzkjooq.model.dao.interfaces.UserDAO;
import authorizationzkjooq.model.domain.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import static org.jooq.util.maven.example.tables.User.USER;

public class UserDaoImpl implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private Connection getCon() throws NamingException {
        Connection connection = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource)envContext.lookup("jdbc/authzk");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Can't connection to source", e);
        }
        return connection;
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        try(Connection connection = getCon()) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Result<Record> result = create.select().from(USER).fetch();
            for (Record r : result) {
                User user = new User();
                user.setId(r.getValue(USER.ID));
                user.setEmail(r.getValue(USER.EMAIL));
                user.setPassword(r.getValue(USER.PSWRD));
                user.setSalt(r.getValue(USER.SALT));
                users.add(user);
            }
        } catch (NamingException | SQLException e) {
            LOGGER.error("Can't find context", e);
        }

        return users;
    }

    public User getUserByEmail(String email) {
        User user = new User();

        try(Connection connection = getCon()) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Record record = create.select().from(USER).where(USER.EMAIL.eq(email)).fetchOne();
            if (record != null) {
                user.setId(record.getValue(USER.ID));
                user.setEmail(record.getValue(USER.EMAIL));
                user.setPassword(record.getValue(USER.PSWRD));
                user.setSalt(record.getValue(USER.SALT));
            }
        } catch (NamingException | SQLException e) {
            LOGGER.error("Can't find context", e);
        }

        return user;
    }

    public void addUser(User user) {
        try(Connection connection = getCon()) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            create.insertInto(USER)
                    .columns(USER.EMAIL, USER.PSWRD, USER.SALT)
                    .values(user.getEmail(), user.getPassword(), user.getSalt()).execute();
        } catch (NamingException | SQLException e) {
            LOGGER.error("Can't find context", e);
        }
    }
}
